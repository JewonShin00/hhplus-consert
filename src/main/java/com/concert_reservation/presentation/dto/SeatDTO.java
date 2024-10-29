package com.concert_reservation.presentation.dto;

import java.time.OffsetDateTime;
import java.util.Objects;

import jakarta.annotation.Generated;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 좌석 정보 DTO
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T21:38:13.756543200+09:00[Asia/Seoul]")
public class SeatDTO {

  @Schema(name = "seatId", description = "좌석 ID", required = true)
  private String seatId;

  @Schema(name = "seatNumber", description = "좌석 번호", required = true)
  private Integer seatNumber;

  @Schema(name = "isReserved", description = "예약 여부", required = true)
  private Boolean isReserved;

  @Schema(name = "isTempReserved", description = "임시 예약 여부", required = true)
  private Boolean isTempReserved;

  @Schema(name = "reservedUntil", description = "예약 만료 시간")
  private OffsetDateTime reservedUntil;

  // Getters and Setters

  public String getSeatId() {
    return seatId;
  }

  public void setSeatId(String seatId) {
    this.seatId = seatId;
  }

  public Integer getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(Integer seatNumber) {
    this.seatNumber = seatNumber;
  }

  public Boolean getIsReserved() {
    return isReserved;
  }

  public void setIsReserved(Boolean isReserved) {
    this.isReserved = isReserved;
  }

  public Boolean getIsTempReserved() {
    return isTempReserved;
  }

  public void setIsTempReserved(Boolean isTempReserved) {
    this.isTempReserved = isTempReserved;
  }

  public OffsetDateTime getReservedUntil() {
    return reservedUntil;
  }

  public void setReservedUntil(OffsetDateTime reservedUntil) {
    this.reservedUntil = reservedUntil;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SeatDTO seatDTO = (SeatDTO) o;
    return Objects.equals(this.seatId, seatDTO.seatId) &&
        Objects.equals(this.seatNumber, seatDTO.seatNumber) &&
        Objects.equals(this.isReserved, seatDTO.isReserved) &&
        Objects.equals(this.isTempReserved, seatDTO.isTempReserved) &&
        Objects.equals(this.reservedUntil, seatDTO.reservedUntil);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seatId, seatNumber, isReserved, isTempReserved, reservedUntil);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SeatDTO {\n");
    sb.append("    seatId: ").append(toIndentedString(seatId)).append("\n");
    sb.append("    seatNumber: ").append(toIndentedString(seatNumber)).append("\n");
    sb.append("    isReserved: ").append(toIndentedString(isReserved)).append("\n");
    sb.append("    isTempReserved: ").append(toIndentedString(isTempReserved)).append("\n");
    sb.append("    reservedUntil: ").append(toIndentedString(reservedUntil)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
