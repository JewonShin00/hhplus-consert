package com.consertreservation.presentation.dto;

public class ReserveSeatDTO {
    private Long concertId;
    private Long seatId;
    private String reservedBy; // 예약자

    // 생성자, Getter, Setter
    public ReserveSeatDTO(String reservedBy) {
        this.reservedBy = reservedBy;
    }

    public ReserveSeatDTO(Long concertId, Long seatId, String reservedBy) {
        this.concertId = concertId;
        this.seatId = seatId;
        this.reservedBy = reservedBy;
    }

    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(Long seatId) {
        this.reservedBy = reservedBy;
    }
}
