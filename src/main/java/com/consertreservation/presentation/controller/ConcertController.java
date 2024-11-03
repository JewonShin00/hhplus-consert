package com.consertreservation.presentation.controller;

import com.consertreservation.application.service.facade.ConcertFacade;
import com.consertreservation.application.service.facade.WaitlistFacade;
import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.presentation.dto.ConcertDTO;
import com.consertreservation.presentation.dto.WaitlistDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import com.consertreservation.presentation.dto.ReserveSeatDTO;

@RestController
public class ConcertController {

    private final ConcertFacade concertFacade;
    private final ModelMapper modelMapper;
    private final WaitlistFacade waitlistFacade;

    @Autowired
    public ConcertController(ConcertFacade concertFacade, ModelMapper modelMapper, WaitlistFacade waitlistFacade) {
        this.concertFacade = concertFacade;
        this.modelMapper = modelMapper;
        this.waitlistFacade = waitlistFacade;
    }
    //날짜로 콘서트 기본정보 조회
    @GetMapping("/api/concerts/available")
    public ResponseEntity<List<ConcertDTO>> getAvailableConcerts(@RequestParam String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<Concert> concerts = concertFacade.getConcertsByDate(localDate);
            List<ConcertDTO> concertDTOS = concerts.stream()
                    .map(concert -> modelMapper.map(concert, ConcertDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(concertDTOS);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request 반환
        }
    }
    // 콘서트 상세 정보 및 예약 가능한 좌석 조회
    @GetMapping("/api/concerts/{id}/details")
    public ResponseEntity<?> getConcertDetails(@PathVariable Long id) {
        try {
            Concert concert = concertFacade.getDetailConcertById(id);
            List<Seat> availableSeats = concert.getSeats().stream()
                    .filter(Seat::isAvailable)
                    .collect(Collectors.toList());

            // 예약 가능한 좌석이 없는 경우 대기열에 추가
            if (availableSeats.isEmpty()) {
                String userId = ""; // JWT 등에서 사용자 ID를 가져오는 로직 필요
                waitlistFacade.addToWaitlist(id, userId);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("No available seats. You have been added to the waitlist. You will be notified when a seat becomes available.");
            }

            return ResponseEntity.ok(concert);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Concert not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving concert details.");
        }
    }



}
