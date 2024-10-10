package com.concert_reservation.repository;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ConcertDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-10T23:17:48.086265+09:00[Asia/Seoul]")
public class ConcertDTO {

  private String concertId;

  private String title;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private String date;

  private String location;

	public ConcertDTO(String number, String summerNightConcert, String date, String seoul) {
      this.concertId = concertId;
      this.title = title;
      this.date = date;
      this.location = location;
    }

	public ConcertDTO concertId(String concertId) {
    this.concertId = concertId;
    return this;
  }

  /**
   * Get concertId
   * @return concertId
  */

  @Schema(name = "concertId", example = "123", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("concertId")
  public String getConcertId() {
    return concertId;
  }

  public void setConcertId(String concertId) {
    this.concertId = concertId;
  }

  public ConcertDTO title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */

  @Schema(name = "title", example = "Summer Night Concert", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ConcertDTO date(String date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @Valid
  @Schema(name = "date", example = "Fri Nov 01 09:00:00 KST 2024", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date")
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public ConcertDTO location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  */

  @Schema(name = "location", example = "Seoul", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("location")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConcertDTO concertDTO = (ConcertDTO) o;
    return Objects.equals(this.concertId, concertDTO.concertId) &&
        Objects.equals(this.title, concertDTO.title) &&
        Objects.equals(this.date, concertDTO.date) &&
        Objects.equals(this.location, concertDTO.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(concertId, title, date, location);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConcertDTO {\n");
    sb.append("    concertId: ").append(toIndentedString(concertId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
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

