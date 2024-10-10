/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.concert_reservation.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;

import java.util.Optional;
import javax.annotation.Generated;

import com.concert_reservation.repository.ReservationRequest;
import com.concert_reservation.repository.ReservationResponse;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-10T23:17:48.086265+09:00[Asia/Seoul]")
@Validated
@Tag(name = "ReservationController", description = "the ReservationController API")
public interface ReservationsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /reservations : Reserve a seat
     * 사용자가 특정 좌석을 예약합니다.
     *
     * @param reservationRequest  (optional)
     * @return Seat reservation response. (status code 200)
     */
    @Operation(
        operationId = "createReservation",
        summary = "Reserve a seat",
        description = "사용자가 특정 좌석을 예약합니다.",
        tags = { "ReservationController" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Seat reservation response.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReservationResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/reservations",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<ReservationResponse> createReservation(
        @Parameter(name = "ReservationRequest", description = "") @Valid @RequestBody(required = false) ReservationRequest reservationRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"reservationId\" : \"res123\", \"expiresAt\" : \"2024-11-01T10:00:00Z\", \"status\" : \"TEMP\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
