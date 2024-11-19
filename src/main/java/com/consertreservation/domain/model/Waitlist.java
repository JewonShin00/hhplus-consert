package com.consertreservation.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Waitlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 대기열 항목의 고유 ID
    private Long concertId; // 대기 중인 콘서트 ID
    private String userId; // 대기 중인 사용자 ID
    private Integer position; // 대기열 순번
    private LocalDateTime reservationAttemptTime; // 예매 시도 시간
    private String seatNumber;  // 좌석 번호

    // 기본 생성자
    public Waitlist() {

    }

    // 생성자
    public Waitlist(Long concertId, String userId, Integer position, LocalDateTime reservationAttemptTime, String seatNumber) {
        this.concertId = concertId;
        this.userId = userId;
        this.position = position;
        this.reservationAttemptTime = reservationAttemptTime;
        this.seatNumber = seatNumber;
    }

    // Getter와 Setter
    public Long getId() {
        return id;
    }

    public Long getConcertId() {
        return concertId;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getPosition() {
        return position;
    }

    public LocalDateTime getReservationAttemptTime() {
        return reservationAttemptTime;
    }

    public String getSeatNumber() { return seatNumber;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setReservationAttemptTime(LocalDateTime reservationAttemptTime) {this.reservationAttemptTime = reservationAttemptTime;}

    public void setSeatNumber(String seatNumber) {this.seatNumber = seatNumber;}
}
