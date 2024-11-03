package com.consertreservation.presentation.dto;

public class ReserveSeatRequest {
    private Long concertId;
    private Long seatId;

    // 생성자, Getter, Setter
    public ReserveSeatRequest() {}

    public ReserveSeatRequest(Long concertId, Long seatId) {
        this.concertId = concertId;
        this.seatId = seatId;
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
}
