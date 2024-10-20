package com.concert_reservation.presentation.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.annotation.Generated;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 콘서트 정보 DTO
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T21:38:13.756543200+09:00[Asia/Seoul]")
public class ConcertDTO {

  @Schema(name = "concertId", description = "콘서트 ID", required = true)
  private String concertId;

  @Schema(name = "title", description = "콘서트 제목", required = true)
  private String title;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Schema(name = "date", description = "콘서트 날짜", required = true)
  private LocalDate date;

  @Schema(name = "location", description = "콘서트 장소", required = true)
  private String location;

  // Getters and Setters

  public String getConcertId() {
    return concertId;
  }

  public void setConcertId(String concertId) {
    this.concertId = concertId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

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

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
