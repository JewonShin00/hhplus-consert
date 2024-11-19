package com.consertreservation.presentation.controller;

import com.consertreservation.application.service.facade.ReservationFacade;
import com.consertreservation.domain.model.Reservation;
import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationFacade reservationFacade;


    @Autowired
    public ReservationController(ReservationFacade reservationFacade, SeatRepository seatRepository) {
        this.reservationFacade = reservationFacade;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable String userId) {
        List<Reservation> reservations = reservationFacade.findReservationsByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/{reservationId}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable Long concertId, @PathVariable String seatNumber) {
        reservationFacade.cancelReservation(concertId, seatNumber);
        return ResponseEntity.ok().build();
    }

    // 좌석 예약 엔드포인트
    @PostMapping("/concerts/{concertId}/seats/{seatNumber}/reserve")
    public ResponseEntity<String> reserveSeat(
            @PathVariable Long concertId,
            @PathVariable String seatNumber,
            @RequestParam String reservedBy) {
        System.out.println("Request received for reserving seat: " + seatNumber + " by user: " + reservedBy);
        reservationFacade.reserveSeat(concertId, seatNumber, reservedBy);
        return ResponseEntity.ok("Seat reserved successfully");
    }

    //인덱스적용에 따른 DB 조회 성능를 위해 추가
    @GetMapping("/concerts/{concertId}/seats/{seatNumber}/check-availability")
    public ResponseEntity<String> checkSeatAvailability(
            @PathVariable Long concertId,
            @PathVariable String seatNumber) {
        System.out.println("Request received for checking seat availability: " + seatNumber + " for concertId: " + concertId);
        Optional<Seat> seat = reservationFacade.checkSeatAvailability(concertId, seatNumber);
        if (seat.isPresent()) {
            return ResponseEntity.ok("Seat is available: " + seat.get().toString());
        } else {
            return ResponseEntity.status(404).body("Seat not found");
        }
    }


}
