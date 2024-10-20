package com.concert_reservation.presentation.dto;

import java.util.Objects;

import javax.annotation.Generated;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 예약 요청 DTO
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T21:38:13.756543200+09:00[Asia/Seoul]")
public class ReservationRequest {

  @Schema(name = "userId", description = "사용자 ID", required = true)
  private String userId;

  @Schema(name = "concertId", description = "콘서트 ID", required = true)
  private String concertId;

  @Schema(name = "seatId", description = "좌석 ID", required = true)
  private String seatId;

  // Getters and Setters

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getConcertId() {
    return concertId;
  }

  public void setConcertId(String concertId) {
    this.concertId = concertId;
  }

  public String getSeatId() {
    return seatId;
  }

  public void setSeatId(String seatId) {
    this.seatId = seatId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReservationRequest reservationRequest = (ReservationRequest) o;
    return Objects.equals(this.userId, reservationRequest.userId) &&
        Objects.equals(this.concertId, reservationRequest.concertId) &&
        Objects.equals(this.seatId, reservationRequest.seatId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, concertId, seatId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReservationRequest {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    concertId: ").append(toIndentedString(concertId)).append("\n");
    sb.append("    seatId: ").append(toIndentedString(seatId)).append("\n");
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
