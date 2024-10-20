/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.concert_reservation.presentation.api;

import com.concert_reservation.config.ApiUtil;
import com.concert_reservation.presentation.dto.ConcertListResponse;
import com.concert_reservation.presentation.dto.SeatListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T21:38:13.756543200+09:00[Asia/Seoul]")
@Validated
@Tag(name = "concerts", description = "the concerts API")
public interface ConcertsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /concerts/{concertId}/seats : 특정 콘서트에서 예약할 수 있는 좌석 목록을 보여줍니다.
     *
     * @param concertId 콘서트 ID (required)
     * @param onlyAvailable true일 경우 예약 가능한 좌석만 조회 (optional)
     * @return A list of available seats for the concert (status code 200)
     */
    @Operation(
        operationId = "concertsConcertIdSeatsGet",
        summary = "특정 콘서트에서 예약할 수 있는 좌석 목록을 보여줍니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of available seats for the concert", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = SeatListResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/concerts/{concertId}/seats",
        produces = { "application/json" }
    )
    default ResponseEntity<SeatListResponse> concertsConcertIdSeatsGet(
        @Parameter(name = "concertId", description = "콘서트 ID", required = true, in = ParameterIn.PATH) @PathVariable("concertId") String concertId,
        @Parameter(name = "onlyAvailable", description = "true일 경우 예약 가능한 좌석만 조회", in = ParameterIn.QUERY) @Valid @RequestParam(value = "onlyAvailable", required = false) Boolean onlyAvailable
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"seats\" : [ { \"isTempReserved\" : true, \"reservedUntil\" : \"2000-01-23T04:56:07.000+00:00\", \"isReserved\" : true, \"seatId\" : \"seatId\", \"seatNumber\" : 0 }, { \"isTempReserved\" : true, \"reservedUntil\" : \"2000-01-23T04:56:07.000+00:00\", \"isReserved\" : true, \"seatId\" : \"seatId\", \"seatNumber\" : 0 } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /concerts : 예약할 수 있는 콘서트 목록을 가져옵니다.
     *
     * @param location 특정 지역의 콘서트만 조회 (optional)
     * @param date 특정 날짜에 열리는 콘서트만 조회 (optional)
     * @return A list of available concerts (status code 200)
     */
    @Operation(
        operationId = "concertsGet",
        summary = "예약할 수 있는 콘서트 목록을 가져옵니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of available concerts", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ConcertListResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/concerts",
        produces = { "application/json" }
    )
    default ResponseEntity<ConcertListResponse> concertsGet(
        @Parameter(name = "location", description = "특정 지역의 콘서트만 조회", in = ParameterIn.QUERY) @Valid @RequestParam(value = "location", required = false) String location,
        @Parameter(name = "date", description = "특정 날짜에 열리는 콘서트만 조회", in = ParameterIn.QUERY) @Valid @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"concerts\" : [ { \"date\" : \"2000-01-23\", \"concertId\" : \"concertId\", \"location\" : \"location\", \"title\" : \"title\" }, { \"date\" : \"2000-01-23\", \"concertId\" : \"concertId\", \"location\" : \"location\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
