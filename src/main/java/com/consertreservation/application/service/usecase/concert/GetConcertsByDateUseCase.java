package com.consertreservation.application.service.usecase.concert;

import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.repository.ConcertRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

//날짜로 콘서트 기본정보 조회
@Service
public class GetConcertsByDateUseCase {
    private final ConcertRepository concertRepository;

    public GetConcertsByDateUseCase(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public List<Concert> execute(LocalDate date) {
        return concertRepository.findByDate(date);
    }
}