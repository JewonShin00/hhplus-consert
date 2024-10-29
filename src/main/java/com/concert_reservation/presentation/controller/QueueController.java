package com.concert_reservation.presentation.controller;

import com.concert_reservation.presentation.dto.QueueStatusResponse;
import com.concert_reservation.application.QueueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class QueueController {

	private final QueueService queueService;

	@Autowired
	public QueueController(QueueService queueService) {
		this.queueService = queueService;
	}

	@Operation(summary = "사용자 대기열 추가", description = "사용자를 대기열에 추가합니다.")
	@PostMapping("/add")
	public ResponseEntity<Integer> addUserToQueue(@Parameter(description = "사용자 ID") @RequestParam String userId) {
		int position = queueService.addUserToQueue(userId);
		return ResponseEntity.ok(position);
	}

	@Operation(summary = "대기 순번 조회", description = "사용자의 대기 순번을 조회합니다.")
	@GetMapping("/status")
	public ResponseEntity<Integer> getUserPosition(@Parameter(description = "사용자 ID") @RequestParam String userId) {
		Integer position = queueService.getUserPosition(userId);
		if (position == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(position);
	}

	@Operation(summary = "대기열 상태 조회", description = "사용자의 대기열 상태를 조회합니다.")
	@GetMapping("/queue/status")
	public ResponseEntity<QueueStatusResponse> getQueueStatus(
		@Parameter(description = "사용자 ID") @RequestParam String userId,
		@Parameter(description = "콘서트 ID") @RequestParam String concertId) {

		Integer position = queueService.getUserPosition(userId);
		QueueStatusResponse response = new QueueStatusResponse();
		response.setUserId(userId);
		response.setConcertId(concertId);
		response.setPosition(position);
		response.setStatus(position != null ? QueueStatusResponse.StatusEnum.WAITING : QueueStatusResponse.StatusEnum.COMPLETED); // 상태 설정

		return ResponseEntity.ok(response);
	}
}
