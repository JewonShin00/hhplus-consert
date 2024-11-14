package com.consertreservation.application.service.usecase.reservation;

import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
//인덱스적용에 따른 DB 조회 성능를 위해 추가
@Service
public class CheckSeatAvailabilityUseCase {
    private final SeatRepository seatRepository;

    public CheckSeatAvailabilityUseCase(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Optional<Seat> execute(Long concertId, String seatNumber) {
        return seatRepository.findByConcertIdAndSeatNumber(concertId, seatNumber);
    }
}

