package com.consertreservation.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    private String seatNumber;  // 좌석 번호
    private boolean isAvailable; // 예약 가능 여부

    @ManyToOne
    private Concert concert; // Concert와의 다대일 관계

    // 기본 생성자
    public Seat() {}

    private String reservedBy; // 예약자

    // 생성자
    public Seat(String seatNumber, boolean isAvailable, Concert concert, String reservedBy) {
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
        this.concert = concert;
        this.reservedBy = reservedBy; // 예약자
    }

    // Getter와 Setter
    public Long getSeatId() {
        return seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Concert getConcert() {
        return concert;
    }

    public String getReservedBy() {
        return reservedBy; // 예약자 정보 반환
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy; // 예약자
    }
}
