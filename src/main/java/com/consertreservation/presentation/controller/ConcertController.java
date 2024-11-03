package com.consertreservation.presentation.controller;

import com.consertreservation.application.service.facade.ConcertFacade;
import com.consertreservation.domain.model.Concert;
import com.consertreservation.presentation.dto.ConcertDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
public class ConcertController {

    private final ConcertFacade concertFacade;
    private final ModelMapper modelMapper;

    @Autowired
    public ConcertController(ConcertFacade concertFacade, ModelMapper modelMapper) {
        this.concertFacade = concertFacade;
        this.modelMapper = modelMapper;
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
    // 콘서트 ID로 콘서트 상세조회
    @GetMapping("/api/concerts/{id}")
    public ResponseEntity<ConcertDTO> getConcertDetails(@PathVariable Long id) {
        try {
            Concert concert = concertFacade.getDetailConcertById(id);
            ConcertDTO concertDetailDTO = modelMapper.map(concert, ConcertDTO.class);
            return ResponseEntity.ok(concertDetailDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
