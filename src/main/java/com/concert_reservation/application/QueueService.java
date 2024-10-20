package com.concert_reservation.application;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class QueueService {

	private final ConcurrentHashMap<String, QueueEntry> userQueueMap = new ConcurrentHashMap<>();
	private final AtomicInteger nextPosition = new AtomicInteger(1);
	private final int defaultExpirationMinutes = 5; // 기본 대기열 유효 시간 (5분)
	private final int expirationMinutes; // 설정된 대기열 유효 시간

	// 기본 생성자 (운영 환경에서는 5분 만료)
	public QueueService() {
		this.expirationMinutes = defaultExpirationMinutes;
	}

	// 테스트용 생성자 (테스트 환경에서 원하는 만료 시간을 설정 가능)
	public QueueService(int expirationMinutes) {
		this.expirationMinutes = expirationMinutes;
	}

	/**
	 * 대기열에 사용자 추가
	 * @param userId 사용자 ID
	 * @return 대기 순번
	 */
	public int addUserToQueue(String userId) {
		// 대기열에서 만료된 사용자 제거
		removeExpiredEntries();

		// 이미 대기 중인 사용자는 다시 대기열에 추가되지 않도록 처리
		QueueEntry existingEntry = userQueueMap.get(userId);
		if (existingEntry != null) {
			return existingEntry.getPosition();
		}

		// 대기열에 새 사용자 추가
		int position = nextPosition.getAndIncrement();
		userQueueMap.put(userId, new QueueEntry(position, LocalDateTime.now()));
		return position;
	}

	/**
	 * 사용자의 대기 순번 조회
	 * @param userId 사용자 ID
	 * @return 대기 순번 또는 null
	 */
	public Integer getUserPosition(String userId) {
		// 대기열에서 만료된 사용자 제거
		removeExpiredEntries();

		QueueEntry entry = userQueueMap.get(userId);
		return entry != null ? entry.getPosition() : null;
	}

	/**
	 * 만료된 대기열 항목 제거
	 */
	private void removeExpiredEntries() {
		userQueueMap.entrySet().removeIf(entry -> entry.getValue().isExpired(expirationMinutes));
	}

	/**
	 * 대기열 항목 클래스
	 */
	private static class QueueEntry {
		private final int position; // 대기열에서의 위치
		private final LocalDateTime timestamp; // 사용자가 대기열에 추가된 시간

		public QueueEntry(int position, LocalDateTime timestamp) {
			this.position = position;
			this.timestamp = timestamp;
		}

		/**
		 * 대기열에서의 사용자 위치를 반환
		 * @return 사용자 대기 순번
		 */
		public int getPosition() {
			return position;
		}

		/**
		 * 대기열 항목이 만료되었는지 확인
		 * @param expirationMinutes 대기열 항목의 만료 시간(분 단위)
		 * @return 항목이 만료되었는지 여부 (true: 만료됨, false: 만료되지 않음)
		 */
		public boolean isExpired(int expirationMinutes) {
			return timestamp.plusMinutes(expirationMinutes).isBefore(LocalDateTime.now());
		}
	}
}
