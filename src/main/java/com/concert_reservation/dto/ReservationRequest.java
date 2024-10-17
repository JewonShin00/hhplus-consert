package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ReservationRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T21:38:13.756543200+09:00[Asia/Seoul]")
public class ReservationRequest {

  private String userId;

  private String concertId;

  private String seatId;

  public ReservationRequest userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  
  @Schema(name = "userId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userId")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public ReservationRequest concertId(String concertId) {
    this.concertId = concertId;
    return this;
  }

  /**
   * Get concertId
   * @return concertId
  */
  
  @Schema(name = "concertId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("concertId")
  public String getConcertId() {
    return concertId;
  }

  public void setConcertId(String concertId) {
    this.concertId = concertId;
  }

  public ReservationRequest seatId(String seatId) {
    this.seatId = seatId;
    return this;
  }

  /**
   * Get seatId
   * @return seatId
  */
  
  @Schema(name = "seatId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("seatId")
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

