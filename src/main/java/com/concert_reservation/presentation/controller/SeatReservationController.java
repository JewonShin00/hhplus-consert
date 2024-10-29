package com.concert_reservation.presentation.controller;

import com.concert_reservation.application.SeatReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatReservationController {

	private final SeatReservationService seatReservationService;

	@Autowired
	public SeatReservationController(SeatReservationService seatReservationService) {
		this.seatReservationService = seatReservationService;
	}

	@Operation(summary = "좌석 예약", description = "사용자가 좌석을 예약합니다.")
	@PostMapping("/reserve")
	public ResponseEntity<String> reserveSeat(
		@Parameter(description = "사용자 ID") @RequestParam String userId,
		@Parameter(description = "콘서트 ID") @RequestParam String concertId,
		@Parameter(description = "좌석 ID") @RequestParam String seatId) {

		boolean success = seatReservationService.reserveSeat(userId, concertId, seatId);
		if (success) {
			return ResponseEntity.ok("좌석 예약 성공");
		} else {
			return ResponseEntity.status(400).body("이미 예약된 좌석입니다."); // 400 Bad Request
		}
	}
}
