package com.concert_reservation.presentation.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 콘서트 목록 응답 DTO
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T21:38:13.756543200+09:00[Asia/Seoul]")
public class ConcertListResponse {

  @Valid
  @Schema(name = "concerts", description = "콘서트 목록", required = true)
  private List<@Valid ConcertDTO> concerts;

  public ConcertListResponse concerts(List<@Valid ConcertDTO> concerts) {
    this.concerts = concerts;
    return this;
  }

  public ConcertListResponse addConcertsItem(ConcertDTO concertsItem) {
    if (this.concerts == null) {
      this.concerts = new ArrayList<>();
    }
    this.concerts.add(concertsItem);
    return this;
  }

  @Valid
  @Schema(name = "concerts", description = "콘서트 목록", required = true)
  @JsonProperty("concerts")
  public List<@Valid ConcertDTO> getConcerts() {
    return concerts;
  }

  public void setConcerts(List<@Valid ConcertDTO> concerts) {
    this.concerts = concerts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConcertListResponse concertListResponse = (ConcertListResponse) o;
    return Objects.equals(this.concerts, concertListResponse.concerts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(concerts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConcertListResponse {\n");
    sb.append("    concerts: ").append(toIndentedString(concerts)).append("\n");
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
