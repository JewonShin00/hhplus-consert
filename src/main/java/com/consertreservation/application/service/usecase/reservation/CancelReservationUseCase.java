package com.consertreservation.application.service.usecase;

import com.consertreservation.domain.model.Reservation;
import com.consertreservation.domain.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//예약취소
@Service
public class CancelReservationUseCase {
    private final ReservationRepository reservationRepository;

    @Autowired
    public CancelReservationUseCase(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void execute(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus("Cancelled");
        reservationRepository.save(reservation);
    }
}
