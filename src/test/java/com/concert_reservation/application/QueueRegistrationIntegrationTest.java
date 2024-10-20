package com.concert_reservation.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QueueRegistrationIntegrationTest {

	@Autowired
	private QueueService queueService;

	@Test
	public void testAddUserToQueue() {
		// 실제 대기열 발급 로직 테스트
		int position = queueService.addUserToQueue("testUser");

		// 대기열 위치가 null이 아닌지 확인 (정상 발급 여부 확인)
		assertNotNull(position, "대기열에 사용자가 성공적으로 추가되었습니다.");
	}
}
