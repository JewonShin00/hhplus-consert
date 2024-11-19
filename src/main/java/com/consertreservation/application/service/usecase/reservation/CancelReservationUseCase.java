package com.consertreservation.application.service.usecase.reservation;

import com.consertreservation.domain.model.Reservation;
import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.domain.repository.ReservationRepository;
import com.consertreservation.domain.repository.SeatRepository;
import com.consertreservation.domain.repository.WaitlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//예약취소
@Service
public class CancelReservationUseCase {
    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final WaitlistRepository waitlistRepository;
    private final ReserveSeatUseCase reserveSeatUseCase;

    @Autowired
    public CancelReservationUseCase(ReservationRepository reservationRepository, SeatRepository seatRepository, WaitlistRepository waitlistRepository, ReserveSeatUseCase reserveSeatUseCase) {
        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
        this.waitlistRepository = waitlistRepository;
        this.reserveSeatUseCase = reserveSeatUseCase;
    }

    public void execute(Long concertId, String seatNumber) {
        Seat seat = reserveSeatUseCase.findSeat(concertId, seatNumber);

        // 상태가 RESERVED 또는 CONFIRMED인지 확인
        if (seat.getSeatStatus() != Seat.SeatStatus.RESERVED && seat.getSeatStatus() != Seat.SeatStatus.CONFIRMED) {
            throw new IllegalStateException("This seat is not reserved.");
        }

        seat.setSeatStatus(Seat.SeatStatus.CANCELLED);
        seat.setAvailable(true);
        seat.setReservedBy(null);
        seatRepository.save(seat);

        processWaitlist(concertId, seatNumber); // 대기열 처리
    }

    public void processWaitlist(Long concertId, String seatNumber) {
        // 대기열에서 첫 번째 사용자 조회
        Optional<Waitlist> nextWaitlistEntry = waitlistRepository.findFirstByConcertIdAndSeatNumberOrderByReservationAttemptTime(concertId, seatNumber);

        if (nextWaitlistEntry.isPresent()) {
            Waitlist waitlist = nextWaitlistEntry.get();
            Seat seat = reserveSeatUseCase.findSeat(concertId, seatNumber);
            reserveSeatUseCase.reserveSeat(seat, waitlist.getUserId()); // 대기열 사용자에게 좌석 예약
            waitlistRepository.delete(waitlist); // 대기열에서 제거
        } else {
            // 대기열이 비어 있으면 좌석을 AVAILABLE로 설정
            Seat seat = reserveSeatUseCase.findSeat(concertId, seatNumber);
            seat.setSeatStatus(Seat.SeatStatus.AVAILABLE);
            seatRepository.save(seat);
        }
    }
}
