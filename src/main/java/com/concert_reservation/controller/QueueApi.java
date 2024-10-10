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
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.Optional;
import javax.annotation.Generated;

import com.concert_reservation.repository.QueueStatusResponse;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-10T23:17:48.086265+09:00[Asia/Seoul]")
@Validated
@Tag(name = "QueueController", description = "the QueueController API")
public interface QueueApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /queue/status : Get user queue status
     * 사용자의 대기열 상태를 조회합니다.
     *
     * @param userId 사용자 ID (required)
     * @param concertId 콘서트 ID (required)
     * @return Queue status for the user. (status code 200)
     */
    @Operation(
        operationId = "getQueueStatus",
        summary = "Get user queue status",
        description = "사용자의 대기열 상태를 조회합니다.",
        tags = { "QueueController" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Queue status for the user.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = QueueStatusResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/queue/status",
        produces = { "application/json" }
    )
    default ResponseEntity<QueueStatusResponse> getQueueStatus(
        @NotNull @Parameter(name = "userId", description = "사용자 ID", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "userId", required = true) String userId,
        @NotNull @Parameter(name = "concertId", description = "콘서트 ID", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "concertId", required = true) String concertId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"concertId\" : \"123\", \"position\" : 1, \"userId\" : \"user123\", \"status\" : \"WAITING\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
