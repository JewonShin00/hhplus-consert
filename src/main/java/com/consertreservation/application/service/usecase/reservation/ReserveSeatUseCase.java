package com.consertreservation.application.service.usecase.reservation;

import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

//좌석예약
@Service
public class ReserveSeatUseCase {

    private final SeatRepository seatRepository;

    public ReserveSeatUseCase(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void execute(Long seatId, String reservedBy) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new NoSuchElementException("Seat not found with id: " + seatId));

        if (!seat.isAvailable()) {
            throw new IllegalStateException("Seat is not available"); // 예약 불가 시 예외 발생
        }

        seat.setAvailable(false); // 좌석 예약 상태로 변경
        seat.setReservedBy(reservedBy); // 예약자 정보 추가
        seatRepository.save(seat); // 변경 사항 저장
    }
}
