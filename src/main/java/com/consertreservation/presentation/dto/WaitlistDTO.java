package com.consertreservation.presentation.dto;

import java.time.LocalDateTime;

public class WaitlistDTO {
    private Long id;          // 대기열 ID
    private Long concertId;   // 대기 중인 콘서트 ID
    private String userId;    // 대기 중인 사용자 ID
    private LocalDateTime timestamp; // 대기열에 추가된 시간
    private Integer position; // 대기열 순번

    // 기본 생성자
    public WaitlistDTO() {
        this.timestamp = LocalDateTime.now(); // 기본적으로 현재 시간을 설정
    }

    // 모든 필드를 초기화하는 생성자
    public WaitlistDTO(Long id, Long concertId, String userId, LocalDateTime timestamp, Integer position) {
        this.id = id;
        this.concertId = concertId;
        this.userId = userId;
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now(); // null 체크 후 현재 시간 설정
        this.position = position;
    }

    // 간단한 생성자
    public WaitlistDTO(String userId, Long concertId, Integer position) {
        this.userId = userId;
        this.concertId = concertId;
        this.position = position;
        this.timestamp = LocalDateTime.now(); // 현재 시간을 대기열 추가 시간으로 설정
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
