package com.concert_reservation.service;

import com.concert_reservation.service.QueueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QueuePositionInquiryIntegrationTest {

	@Autowired
	private QueueService queueService;

	@Test
	public void testGetUserPosition() {
		// 대기열에 사용자 추가
		queueService.addUserToQueue("testUser");

		// 사용자의 대기 순번을 조회
		Integer position = queueService.getUserPosition("testUser");

		// 대기 순번이 예상한 값과 일치하는지 확인
		assertEquals(1, position, "대기 순번이 정확하게 조회되었습니다.");
	}
}
