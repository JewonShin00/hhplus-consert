package com.concert_reservation.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest {

	@Test
	@DisplayName("정상적인 결제 처리")
	public void testProcessPayment_Success() {
		PaymentService paymentService = new PaymentService();
		String userId = "user1";
		String reservationId = "reservation1";

		// 잔액 충전 후 결제
		paymentService.rechargeBalance(userId, 1000);
		boolean result = paymentService.processPayment(userId, reservationId, 500);

		assertTrue(result, "결제 성공");
		assertEquals(500, paymentService.getUserBalance(userId), "잔액은 500이 남아야 합니다.");
	}

	@Test
	@DisplayName("잔액 부족으로 결제 실패")
	public void testProcessPayment_InsufficientBalance() {
		PaymentService paymentService = new PaymentService();
		String userId = "user2";
		String reservationId = "reservation2";

		// 잔액이 부족한 상태에서 결제 시도
		paymentService.rechargeBalance(userId, 200);
		boolean result = paymentService.processPayment(userId, reservationId, 500);

		assertFalse(result, "잔액이 부족하면 결제 실패");
	}

	@Test
	@DisplayName("결제 금액이 0 이하일 때 예외 발생")
	public void testProcessPayment_InvalidAmount() {
		PaymentService paymentService = new PaymentService();
		String userId = "user3";
		String reservationId = "reservation3";

		paymentService.rechargeBalance(userId, 1000);

		// 결제 금액이 0 이하일 때 예외가 발생해야 함
		assertThrows(IllegalArgumentException.class, () -> {
			paymentService.processPayment(userId, reservationId, 0);
		}, "결제 금액이 0이하일 때 예외 발생");
	}

	@Test
	@DisplayName("정상적인 잔액 충전")
	public void testRechargeBalance_Success() {
		PaymentService paymentService = new PaymentService();
		String userId = "user4";

		// 잔액을 1000 충전
		paymentService.rechargeBalance(userId, 1000);
		assertEquals(1000, paymentService.getUserBalance(userId), "잔액이 1000이어야 합니다.");
	}

	@Test
	@DisplayName("충전 금액이 0 이하일 때 예외 발생")
	public void testRechargeBalance_InvalidAmount() {
		PaymentService paymentService = new PaymentService();
		String userId = "user5";

		// 충전 금액이 0 이하일 때 예외가 발생해야 함
		assertThrows(IllegalArgumentException.class, () -> {
			paymentService.rechargeBalance(userId, -100);
		}, "충전 금액이 0이하일 때 예외 발생");
	}
}
