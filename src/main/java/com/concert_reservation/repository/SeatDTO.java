package com.concert_reservation.repository;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * SeatDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-10T23:17:48.086265+09:00[Asia/Seoul]")
public class SeatDTO {

  private String seatId;

  private Integer seatNumber;

  private Boolean isReserved;

  private Boolean isTempReserved;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime reservedUntil;

  public SeatDTO seatId(String seatId) {
    this.seatId = seatId;
    return this;
  }

  /**
   * Get seatId
   * @return seatId
  */

  @Schema(name = "seatId", example = "seat1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("seatId")
  public String getSeatId() {
    return seatId;
  }

  public void setSeatId(String seatId) {
    this.seatId = seatId;
  }

  public SeatDTO seatNumber(Integer seatNumber) {
    this.seatNumber = seatNumber;
    return this;
  }

  /**
   * Get seatNumber
   * @return seatNumber
  */

  @Schema(name = "seatNumber", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("seatNumber")
  public Integer getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(Integer seatNumber) {
    this.seatNumber = seatNumber;
  }

  public SeatDTO isReserved(Boolean isReserved) {
    this.isReserved = isReserved;
    return this;
  }

  /**
   * Get isReserved
   * @return isReserved
  */

  @Schema(name = "isReserved", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isReserved")
  public Boolean getIsReserved() {
    return isReserved;
  }

  public void setIsReserved(Boolean isReserved) {
    this.isReserved = isReserved;
  }

  public SeatDTO isTempReserved(Boolean isTempReserved) {
    this.isTempReserved = isTempReserved;
    return this;
  }

  /**
   * Get isTempReserved
   * @return isTempReserved
  */

  @Schema(name = "isTempReserved", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isTempReserved")
  public Boolean getIsTempReserved() {
    return isTempReserved;
  }

  public void setIsTempReserved(Boolean isTempReserved) {
    this.isTempReserved = isTempReserved;
  }

  public SeatDTO reservedUntil(OffsetDateTime reservedUntil) {
    this.reservedUntil = reservedUntil;
    return this;
  }

  /**
   * Get reservedUntil
   * @return reservedUntil
  */
  @Valid
  @Schema(name = "reservedUntil", example = "2024-11-01T10:00Z", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reservedUntil")
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

