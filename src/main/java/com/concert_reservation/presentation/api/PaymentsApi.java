/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.concert_reservation.presentation.api;

import com.concert_reservation.config.ApiUtil;
import com.concert_reservation.presentation.dto.PaymentRequest;
import com.concert_reservation.presentation.dto.PaymentResponse;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T21:38:13.756543200+09:00[Asia/Seoul]")
@Validated
@Tag(name = "payments", description = "the payments API")
public interface PaymentsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /payments : 사용자가 예약한 좌석을 결제합니다.
     *
     * @param paymentRequest  (required)
     * @return Payment processed (status code 200)
     */
    @Operation(
        operationId = "paymentsPost",
        summary = "사용자가 예약한 좌석을 결제합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Payment processed", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/payments",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<PaymentResponse> paymentsPost(
        @Parameter(name = "PaymentRequest", description = "", required = true) @Valid @RequestBody PaymentRequest paymentRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"paymentId\" : \"paymentId\", \"status\" : \"SUCCESS\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
