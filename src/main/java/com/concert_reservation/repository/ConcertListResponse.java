package com.concert_reservation.repository;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;

/**
 * ConcertListResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-10T23:17:48.086265+09:00[Asia/Seoul]")
public class ConcertListResponse {

  @Valid
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

  /**
   * Get concerts
   * @return concerts
  */
  @Valid
  @Schema(name = "concerts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("concerts")
  public List<@Valid ConcertDTO> getConcerts() {
    return concerts;
  }

  public void setConcerts(List<com.concert_reservation.repository.ConcertDTO> concerts) {
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

