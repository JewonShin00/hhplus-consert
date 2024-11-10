package com.consertreservation.application.service.facade;

import com.consertreservation.application.service.usecase.reservation.FindReservationsByUserIdUseCase;
import com.consertreservation.application.service.usecase.reservation.CancelReservationUseCase;
import com.consertreservation.application.service.usecase.reservation.ReserveSeatUseCase;
import com.consertreservation.domain.model.Reservation;
import com.consertreservation.domain.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationFacade {
    private final FindReservationsByUserIdUseCase findReservationsByUserIdUseCase;
    private final CancelReservationUseCase cancelReservationUseCase;
    private final ReserveSeatUseCase reserveSeatUseCase;
    @Autowired
    public ReservationFacade(FindReservationsByUserIdUseCase findReservationsByUserIdUseCase,
                             CancelReservationUseCase cancelReservationUseCase, ReserveSeatUseCase reserveSeatUseCase) {
        this.findReservationsByUserIdUseCase = findReservationsByUserIdUseCase;
        this.cancelReservationUseCase = cancelReservationUseCase;
        this.reserveSeatUseCase = reserveSeatUseCase;
    }

    public List<Reservation> findReservationsByUserId(String userId) {
        return findReservationsByUserIdUseCase.execute(userId);
    }

    public void cancelReservation(Long reservationId) {
        cancelReservationUseCase.execute(reservationId);
    }

    //좌석예약
    public void reserveSeat(Long concertId, String seatNumber, String reservedBy) {
        reserveSeatUseCase.execute(concertId, seatNumber, reservedBy);
    }
}
