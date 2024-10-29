package com.consertreservation.presentation.controller;

import com.consertreservation.application.service.ConcertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConcertController {

    private final ConcertService concertService;

    // 생성자 주입
    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping("/api/concerts/available")
    public List<Object> getAvailableConcerts(@RequestParam String date) {
        // ConcertService를 통해 날짜별 콘서트 조회
        return concertService.getAvailableConcerts(date);
    }
}
