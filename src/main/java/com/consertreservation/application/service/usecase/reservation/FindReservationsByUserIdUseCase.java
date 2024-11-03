package com.consertreservation.application.service.usecase;

import com.consertreservation.domain.model.Reservation;
import com.consertreservation.domain.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//예약조회
@Service
public class FindReservationsByUserIdUseCase {
    private ReservationRepository reservationRepository;

    @Autowired
    public FindReservationsByUserIdUseCase(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> execute(String userId) {
        return reservationRepository.findByUserId(userId);
    }
}