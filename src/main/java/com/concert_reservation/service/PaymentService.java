package com.concert_reservation.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	private final ConcurrentHashMap<String, Integer> userBalances = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<String, String> reservationPayments = new ConcurrentHashMap<>();

	/**
	 * 결제처리
	 * 사용자 잔액을 차감하고 결제
	 * @param userId 사용자 ID
	 * @param reservationId 예약 ID
	 * @param amount 결제 금액
	 * @return 결제 성공 여부 (잔액 부족 시 false 반환)
	 */
	public boolean processPayment(String userId, String reservationId, int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("결제 금액은 0보다 커야 합니다.");
		}

		Integer balance = userBalances.get(userId);

		if (balance == null || balance < amount) {
			// 잔액 부족 시 결제 실패
			return false;
		}

		// 잔액 차감 및 결제 기록 추가
		userBalances.put(userId, balance - amount);
		reservationPayments.put(reservationId, userId);
		return true;
	}

	/**
	 * 사용자의 잔액을 조회
	 * @param userId 사용자 ID
	 * @return 사용자 잔액 (없을 경우 0 반환)
	 */
	public Integer getUserBalance(String userId) {
		return userBalances.getOrDefault(userId, 0);
	}

	/**
	 * 사용자의 잔액을 충전
	 * @param userId 사용자 ID
	 * @param amount 충전할 금액
	 */
	public void rechargeBalance(String userId, int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("충전 금액은 0보다 커야 합니다.");
		}
		userBalances.put(userId, getUserBalance(userId) + amount);
	}
}
