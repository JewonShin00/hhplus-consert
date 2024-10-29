package com.concert_reservation.presentation.dto;

import java.util.Objects;

import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 대기열 상태 응답 DTO
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T21:38:13.756543200+09:00[Asia/Seoul]")
public class QueueStatusResponse {

  @Schema(name = "userId", description = "사용자 ID", required = true)
  private String userId;

  @Schema(name = "concertId", description = "콘서트 ID", required = true)
  private String concertId;

  @Schema(name = "position", description = "대기 순번", required = true)
  private Integer position;

  /**
   * 대기 상태
   */
  public enum StatusEnum {
    WAITING("WAITING"),
    PROCESSING("PROCESSING"),
    COMPLETED("COMPLETED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @Schema(name = "status", description = "대기 상태", required = true)
  private StatusEnum status;

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

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueueStatusResponse queueStatusResponse = (QueueStatusResponse) o;
    return Objects.equals(this.userId, queueStatusResponse.userId) &&
        Objects.equals(this.concertId, queueStatusResponse.concertId) &&
        Objects.equals(this.position, queueStatusResponse.position) &&
        Objects.equals(this.status, queueStatusResponse.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, concertId, position, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueueStatusResponse {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    concertId: ").append(toIndentedString(concertId)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
