package com.concert_reservation.presentation.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 좌석 목록 응답 DTO
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T21:38:13.756543200+09:00[Asia/Seoul]")
public class SeatListResponse {

  @Valid
  @Schema(name = "seats", description = "좌석 목록", required = true)
  private List<@Valid SeatDTO> seats;

  public SeatListResponse seats(List<@Valid SeatDTO> seats) {
    this.seats = seats;
    return this;
  }

  public SeatListResponse addSeatsItem(SeatDTO seatsItem) {
    if (this.seats == null) {
      this.seats = new ArrayList<>();
    }
    this.seats.add(seatsItem);
    return this;
  }

  @Valid
  @Schema(name = "seats", description = "좌석 목록", required = true)
  @JsonProperty("seats")
  public List<@Valid SeatDTO> getSeats() {
    return seats;
  }

  public void setSeats(List<@Valid SeatDTO> seats) {
    this.seats = seats;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SeatListResponse seatListResponse = (SeatListResponse) o;
    return Objects.equals(this.seats, seatListResponse.seats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seats);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SeatListResponse {\n");
    sb.append("    seats: ").append(toIndentedString(seats)).append("\n");
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
