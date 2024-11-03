package com.consertreservation.presentation.controller;

import com.consertreservation.application.service.facade.ReservationFacade;
import com.consertreservation.domain.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationFacade reservationFacade;

    @Autowired
    public ReservationController(ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable String userId) {
        List<Reservation> reservations = reservationFacade.findReservationsByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/{reservationId}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) {
        reservationFacade.cancelReservation(reservationId);
        return ResponseEntity.ok().build();
    }

}
