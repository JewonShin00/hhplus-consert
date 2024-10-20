package com.concert_reservation.presentation.dto;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.annotation.Generated;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 예약 응답 DTO
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T21:38:13.756543200+09:00[Asia/Seoul]")
public class ReservationResponse {

  @Schema(name = "reservationId", description = "예약 ID", required = true)
  private String reservationId;

  @Schema(name = "status", description = "예약 상태", required = true)
  private String status;

  @Schema(name = "expiresAt", description = "예약 만료 시간", required = true)
  private OffsetDateTime expiresAt;

  // Getters and Setters

  public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public OffsetDateTime getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(OffsetDateTime expiresAt) {
    this.expiresAt = expiresAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReservationResponse reservationResponse = (ReservationResponse) o;
    return Objects.equals(this.reservationId, reservationResponse.reservationId) &&
        Objects.equals(this.status, reservationResponse.status) &&
        Objects.equals(this.expiresAt, reservationResponse.expiresAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reservationId, status, expiresAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReservationResponse {\n");
    sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    expiresAt: ").append(toIndentedString(expiresAt)).append("\n");
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
