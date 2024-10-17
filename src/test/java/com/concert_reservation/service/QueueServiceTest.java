package com.concert_reservation.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueueServiceTest {

	@Test
	@DisplayName("사용자를 대기열에 추가")
	public void testAddUserToQueue() {
		QueueService queueService = new QueueService(1); // 1분 만료로 설정
		String userId = "user1";
		int position = queueService.addUserToQueue(userId);
		assertEquals(1, position); // 첫 번째 사용자는 1번 대기
	}

	@Test
	@DisplayName("대기 순번 조회")
	public void testGetUserPosition() {
		QueueService queueService = new QueueService(1); // 1분 만료로 설정
		String userId = "user1";
		queueService.addUserToQueue(userId);

		Integer position = queueService.getUserPosition(userId);
		assertNotNull(position);
		assertEquals(1, position); // 첫 번째 사용자는 1번 대기
	}

	@Test
	@DisplayName("대기열 항목이 만료되면 대기열에서 제거")
	public void testQueueExpiration() throws InterruptedException {
		QueueService queueService = new QueueService(0); // 만료 시간을 0분으로 설정 (즉시 만료)

		String userId = "user1";
		queueService.addUserToQueue(userId);

		Thread.sleep(1000); // 1초 대기
		assertNull(queueService.getUserPosition(userId)); // 만료되었으므로 null이어야 함
	}
}
