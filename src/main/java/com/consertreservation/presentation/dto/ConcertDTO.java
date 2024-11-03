package com.consertreservation.presentation.dto;

import java.time.LocalDate;
import java.util.List;

public class ConcertDTO {

    private Long concertId;    // 콘서트의 고유 ID
    private String name;       // 콘서트 이름
    private LocalDate date;    // 콘서트 날짜 (예: 2024-11-01)
    private String location;   // 콘서트 장소
    private String seatStatus; // 좌석 상태 (예: 'Available', 'Sold Out')
    private String description; // 콘서트에 대한 간단한 설명 또는 소개
    private Double price;      // 콘서트 티켓 가격

    private List<SeatDTO> availableSeats; // 좌석 DTO 리스트

    // 기본 생성자
    public ConcertDTO() {}
    // 모든 필드를 초기화하는 생성자
    public ConcertDTO(Long concertId, String name, LocalDate date, String location, String seatStatus, List<SeatDTO> availableSeats) {
        this.concertId = concertId;
        this.name = name;
        this.date = date;
        this.location = location;
        this.seatStatus = seatStatus;
        this.availableSeats = availableSeats; // 좌석 정보 설정
    }

    // Getters and Setters
    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(String seatStatus) {
        this.seatStatus = seatStatus;
    }

    public List<SeatDTO> getAvailableSeats() {
        return availableSeats;
    }
}
