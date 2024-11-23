package com.consertreservation.application.service.usecase.reservation;

import com.consertreservation.domain.model.OutboxEvent;
import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.domain.repository.OutboxRepository;
import com.consertreservation.domain.repository.SeatRepository;
import com.consertreservation.domain.repository.WaitlistRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

//좌석예약
@Service
public class ReserveSeatUseCase {

    private final SeatRepository seatRepository;
    private final WaitlistRepository waitlistRepository;
    private final OutboxRepository outboxRepository;

    public ReserveSeatUseCase(SeatRepository seatRepository, WaitlistRepository waitlistRepository, KafkaTemplate<String, String> kafkaTemplate, OutboxRepository outboxRepository) {
        this.seatRepository = seatRepository;
        this.waitlistRepository = waitlistRepository;
        this.outboxRepository = outboxRepository;
    }
    @Transactional
    public void execute(Long concertId, String seatNumber, String reservedBy) {
        
        Seat seat = findSeat(concertId, seatNumber); // 좌석 조회
        // 기 예약 체크(상태가 AVAILABLE인지 확인)
        if (seat.getSeatStatus() != Seat.SeatStatus.AVAILABLE) {
            addToWaitlist(concertId, seatNumber, reservedBy); // 대기열에 추가
            // Outbox에 대기열 추가 이벤트 저장
            saveToOutbox(
                    "Waitlist",
                    seatNumber,
                    "WAITLIST_ADDED",
                    String.format("{\"userId\": \"%s\", \"concertId\": \"%d\", \"seatNumber\": \"%s\"}", reservedBy, concertId, seatNumber)
            );
            return;
        }
        reserveSeat(seat, reservedBy); // 변경 사항 저장
        // Outbox에 예약 성공 이벤트 저장
        saveToOutbox(
                "Reservation",
                seatNumber,
                "SEAT_RESERVED",
                String.format("{\"userId\": \"%s\", \"concertId\": \"%d\", \"seatNumber\": \"%s\"}", reservedBy, concertId, seatNumber)
        );
    }

    Seat findSeat(Long concertId, String seatNumber) {
        return seatRepository.findByConcertIdAndSeatNumber(concertId, seatNumber)
                .orElseThrow(() -> new NoSuchElementException("Seat not found with concert_id: " + concertId + " and seat_number: " + seatNumber));
    }

    private void addToWaitlist(Long concertId, String seatNumber, String userId) {
        Waitlist waitlist = new Waitlist();
        waitlist.setConcertId(concertId);
        waitlist.setSeatNumber(seatNumber);
        waitlist.setUserId(userId);
        waitlist.setReservationAttemptTime(LocalDateTime.now());

        waitlistRepository.save(waitlist);
    }

    void reserveSeat(Seat seat, String reservedBy) {
        seat.setSeatStatus(Seat.SeatStatus.RESERVED); // 좌석 상태(상세) 추가
        seat.setAvailable(false);
        seat.setReservedBy(reservedBy);
        seatRepository.save(seat);
    }

    private void saveToOutbox(String aggregateType, String aggregateId, String eventType, String payload) {
        OutboxEvent event = new OutboxEvent();
        event.setAggregateType(aggregateType); // 예: "Reservation"
        event.setAggregateId(aggregateId);     // 예: 좌석 ID
        event.setEventType(eventType);         // 예: "SEAT_RESERVED"
        event.setPayload(payload);             // 예: 예약 상세 정보(JSON)
        event.setStatus("PENDING");            // 초기 상태
        event.setCreatedAt(LocalDateTime.now());
        outboxRepository.save(event);
    }
}
