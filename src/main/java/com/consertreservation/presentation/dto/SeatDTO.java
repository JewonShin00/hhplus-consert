package com.consertreservation.presentation.dto;

public class SeatDTO {
    private Long seatId;      // 좌석 ID
    private String seatNumber; // 좌석 번호
    private boolean isAvailable; // 예약 가능 여부

    // 생성자와 Getter, Setter

    public SeatDTO(Long seatId, String seatNumber, boolean isAvailable) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
    }

    public Long getSeatId() {
        return seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
