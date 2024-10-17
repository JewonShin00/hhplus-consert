package com.concert_reservation.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SeatReservationServiceTest {
	private final SeatReservationService seatReservationService = new SeatReservationService();

	@Test
	@DisplayName("사용자가 좌석을 성공적으로 예약")
	public void testReserveSeat_Success() {
		String userId = "user1";
		String concertId = "concert1";
		String seatId = "seat1";

		boolean result = seatReservationService.reserveSeat(userId, concertId, seatId);
		assertTrue(result, "좌석 예약에 성공했습니다."); // 예약 성공 여부 확인
	}

	@Test
	@DisplayName("이미 예약된 좌석에 대해 예약 시도 시 실패")
	public void testReserveSeat_Failure_AlreadyReserved() {
		String userId1 = "user1";
		String userId2 = "user2";
		String concertId = "concert1";
		String seatId = "seat1";

		// 첫 번째 사용자 예약
		seatReservationService.reserveSeat(userId1, concertId, seatId);

		// 두 번째 사용자 예약 시도
		boolean result = seatReservationService.reserveSeat(userId2, concertId, seatId);
		assertFalse(result, "이미 예약된 좌석입니다."); // 예약 실패 확인
	}

	@Test
	@DisplayName("동시성 예약 시도")
	public void testConcurrentReservations() throws InterruptedException {
		String userId1 = "user1";
		String userId2 = "user2";
		String concertId = "concert1";
		String seatId = "seat1";

		// 첫 번째 사용자가 예약 시도
		Runnable user1Reservation = () -> {
			boolean result = seatReservationService.reserveSeat(userId1, concertId, seatId);
			assertTrue(result, "사용자1이 좌석 예약에 실패했습니다."); // 사용자1이 예약 성공해야 함
		};

		// 두 번째 사용자가 예약 시도
		Runnable user2Reservation = () -> {
			boolean result = seatReservationService.reserveSeat(userId2, concertId, seatId);
			assertFalse(result, "사용자2는 이미 예약된 좌석에 대해 예약 시도했습니다."); // 사용자2는 예약 실패해야 함
		};

		// 두 스레드 시작
		Thread thread1 = new Thread(user1Reservation);
		Thread thread2 = new Thread(user2Reservation);
		thread1.start();
		thread2.start();

		// 스레드 완료 대기
		thread1.join();
		thread2.join();
	}

}
