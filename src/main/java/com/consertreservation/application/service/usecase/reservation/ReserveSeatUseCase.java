package com.consertreservation.application.service.usecase.reservation;

import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.domain.repository.SeatRepository;
import com.consertreservation.domain.repository.WaitlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

//좌석예약
@Service
public class ReserveSeatUseCase {

    private final SeatRepository seatRepository;
    private final WaitlistRepository waitlistRepository;

    public ReserveSeatUseCase(SeatRepository seatRepository, WaitlistRepository waitlistRepository) {
        this.seatRepository = seatRepository;
        this.waitlistRepository = waitlistRepository;
    }
    @Transactional
    public void execute(Long concertId, String seatNumber, String reservedBy) {
        
        Seat seat = findSeat(concertId, seatNumber); // 좌석 조회
        // 기 예약 체크(상태가 AVAILABLE인지 확인)
        if (seat.getSeatStatus() != Seat.SeatStatus.AVAILABLE) {
            addToWaitlist(concertId, seatNumber, reservedBy); // 대기열에 추가
            return;
        }
        reserveSeat(seat, reservedBy); // 변경 사항 저장
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
        throw new IllegalStateException("Seat is not available, added to waitlist");
    }

    void reserveSeat(Seat seat, String reservedBy) {
        seat.setSeatStatus(Seat.SeatStatus.RESERVED); // 좌석 상태(상세) 추가
        seat.setAvailable(false);
        seat.setReservedBy(reservedBy);
        seatRepository.save(seat);
    }

}
