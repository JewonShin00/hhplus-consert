package com.concert_reservation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-10T23:17:48.086265+09:00[Asia/Seoul]")
@Controller
@RequestMapping("${openapi.concertReservation.base-path:}")
public class PaymentsApiController implements PaymentsApi {

    private final NativeWebRequest request;

    @Autowired
    public PaymentsApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
