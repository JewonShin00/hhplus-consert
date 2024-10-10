package com.concert_reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

import com.concert_reservation.repository.ConcertDTO;
import com.concert_reservation.repository.ConcertListResponse;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-10T23:17:48.086265+09:00[Asia/Seoul]")
@Controller
@RequestMapping("${openapi.concertReservation.base-path:}")
public class ConcertsApiController {

    private final NativeWebRequest request;

    @Autowired
    public ConcertsApiController(NativeWebRequest request) {
        this.request = request;
    }


}