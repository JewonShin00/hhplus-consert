package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.model.SeatDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * SeatListResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T21:38:13.756543200+09:00[Asia/Seoul]")
public class SeatListResponse {

  @Valid
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

  /**
   * Get seats
   * @return seats
  */
  @Valid 
  @Schema(name = "seats", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

