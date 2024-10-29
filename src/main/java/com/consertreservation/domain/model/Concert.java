package com.consertreservation.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Concert {

    @Id
    private Long concertId;

    private String name;
    private LocalDate date;
    private String location;

    // 예시로 좌석 상태를 전체 상태로 관리하는 경우 (추가 논의 가능)
    private String seatStatus;

    // 기본 생성자
    public Concert() {}

    // 생성자 (필요한 필드만 선택 가능)
    public Concert(Long concertId, String name, LocalDate date, String location) {
        this.concertId = concertId;
        this.name = name;
        this.date = date;
        this.location = location;
    }

    // Getter와 Setter 메서드들 추가 (필요시 Lombok으로 자동 생성 가능)
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
}