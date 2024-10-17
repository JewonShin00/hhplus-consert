package com.concert_reservation.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class SeatReservationService {
	private final ConcurrentHashMap<String, String> seatReservationMap = new ConcurrentHashMap<>();
	private final ReentrantLock lock = new ReentrantLock();

	/**
	 * 좌석을 예약
	 * @param userId 사용자 ID
	 * @param concertId 콘서트 ID
	 * @param seatId 좌석 ID
	 * @return 예약 성공 여부
	 */
	public boolean reserveSeat(String userId, String concertId, String seatId) {
		lock.lock(); // Lock 적용
		try {
			String key = concertId + "-" + seatId;
			if (seatReservationMap.containsKey(key)) {
				return false; // 이미 예약된 좌석이면 예약 실패
			}
			seatReservationMap.put(key, userId);
			return true; // 예약 성공
		} finally {
			lock.unlock(); // Lock 해제
		}
	}
}
