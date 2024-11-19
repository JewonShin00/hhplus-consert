package com.consertreservation.domain.model;

import jakarta.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    private String seatNumber;  // 좌석 번호
    private boolean isAvailable; // 예약 가능 여부

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus; // 좌석 상태 (Enum 타입)

    public enum SeatStatus {
        AVAILABLE,   // 예약 가능
        RESERVED,    // 예약 완료
        CONFIRMED,   // 결제 완료
        PENDING,     // 결제 대기
        CANCELLED,   // 예약 취소
        WAITLISTED   // 대기열에 포함된 상태
    }

    @ManyToOne
    private Concert concert; // Concert와의 다대일 관계


    private String reservedBy; // 예약자

    // 기본 생성자
    public Seat() {}

    // 생성자
    public Seat(String seatNumber, boolean isAvailable, Concert concert, String reservedBy, SeatStatus seatStatus) {
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
        this.concert = concert;
        this.reservedBy = reservedBy; // 예약자
        this.seatStatus = seatStatus;
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

    public SeatStatus getSeatStatus() {return seatStatus;}

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

    public void setReservedBy(String reservedBy) {this.reservedBy = reservedBy;} // 예약자

    public void setSeatStatus(SeatStatus seatStatus) {this.seatStatus = seatStatus;}
}
