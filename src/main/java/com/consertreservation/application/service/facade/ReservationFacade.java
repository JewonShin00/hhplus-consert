package com.consertreservation.application.service.facade;

import com.consertreservation.application.service.usecase.reservation.*;
import com.consertreservation.application.service.usecase.reservation.ReserveSeatUseCase;
import com.consertreservation.domain.model.Reservation;
import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.repository.ReservationRepository;
import com.consertreservation.domain.repository.SeatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;


import java.util.List;
import java.util.Optional;

@Service
public class ReservationFacade {
    private final FindReservationsByUserIdUseCase findReservationsByUserIdUseCase;
    private final CancelReservationUseCase cancelReservationUseCase;
    private final ReserveSeatUseCase reserveSeatUseCase;
    private final CheckSeatAvailabilityUseCase checkSeatAvailabilityUseCase;


    @Autowired
    public ReservationFacade(FindReservationsByUserIdUseCase findReservationsByUserIdUseCase,
                             CancelReservationUseCase cancelReservationUseCase, ReserveSeatUseCase reserveSeatUseCase, SeatRepository seatRepository, CheckSeatAvailabilityUseCase checkSeatAvailabilityUseCase) {
        this.findReservationsByUserIdUseCase = findReservationsByUserIdUseCase;
        this.cancelReservationUseCase = cancelReservationUseCase;
        this.reserveSeatUseCase = reserveSeatUseCase;
        this.checkSeatAvailabilityUseCase = checkSeatAvailabilityUseCase;
    }

    public List<Reservation> findReservationsByUserId(String userId) {
        return findReservationsByUserIdUseCase.execute(userId);
    }

    public void cancelReservation(Long concertId, String seatNumber) {
        cancelReservationUseCase.execute(concertId, seatNumber);
    }

    //좌석예약
    public void reserveSeat(Long concertId, String seatNumber, String reservedBy) {
        reserveSeatUseCase.execute(concertId, seatNumber, reservedBy);
    }

    //인덱스적용에 따른 DB 조회 성능를 위해 추가
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Optional<Seat> checkSeatAvailability(Long concertId, String seatNumber) {
        return checkSeatAvailabilityUseCase.execute(concertId, seatNumber);
    }

}
