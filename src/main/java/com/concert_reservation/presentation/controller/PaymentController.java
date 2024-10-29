package com.concert_reservation.presentation.controller;

import com.concert_reservation.application.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	private final PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Operation(summary = "결제 처리", description = "사용자의 잔액을 차감하고 예약 기록을 추가합니다.")
	@PostMapping("/process")
	public ResponseEntity<String> processPayment(
		@Parameter(description = "사용자 ID") @RequestParam String userId,
		@Parameter(description = "예약 ID") @RequestParam String reservationId,
		@Parameter(description = "결제 금액") @RequestParam int amount) {

		try {
			boolean success = paymentService.processPayment(userId, reservationId, amount);
			if (success) {
				return ResponseEntity.ok("결제 성공");
			} else {
				return ResponseEntity.status(400).body("잔액이 부족합니다.");
			}
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
	}

	@Operation(summary = "잔액 조회", description = "사용자의 잔액을 조회합니다.")
	@GetMapping("/balance")
	public ResponseEntity<Integer> getUserBalance(@Parameter(description = "사용자 ID") @RequestParam String userId) {
		return ResponseEntity.ok(paymentService.getUserBalance(userId));
	}

	@Operation(summary = "잔액 충전", description = "사용자가 잔액을 충전합니다.")
	@PostMapping("/recharge")
	public ResponseEntity<String> rechargeBalance(
		@Parameter(description = "사용자 ID") @RequestParam String userId,
		@Parameter(description = "충전할 금액") @RequestParam int amount) {
		try {
			paymentService.rechargeBalance(userId, amount);
			return ResponseEntity.ok("잔액 충전 완료");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
	}
}
