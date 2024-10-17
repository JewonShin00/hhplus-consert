package com.concert_reservation.service;

import com.concert_reservation.service.SeatReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SeatReservationIntegrationTest {

	@Autowired
	private SeatReservationService seatReservationService;

	@Test
	public void testReserveSeat() {
		// 좌석 예약 테스트
		boolean success = seatReservationService.reserveSeat("testUser", "concert1", "seat1");

		// 예약 성공 여부 확인
		assertTrue(success, "좌석이 성공적으로 예약되었습니다.");
	}
}
