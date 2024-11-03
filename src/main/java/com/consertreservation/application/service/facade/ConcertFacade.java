package com.consertreservation.application.service.facade;

import com.consertreservation.application.service.usecase.GetConcertsByDateUseCase;
import com.consertreservation.application.service.usecase.GetDetailConcertByIdUseCase;
import com.consertreservation.domain.model.Concert;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ConcertFacade {
    private final GetConcertsByDateUseCase getConcertsByDateUseCase;
    private final GetDetailConcertByIdUseCase getDetailConcertByIdUseCase;

    public ConcertFacade(GetConcertsByDateUseCase getConcertsByDateUseCase,
                         GetDetailConcertByIdUseCase getDetailConcertByIdUseCase) {
        this.getConcertsByDateUseCase = getConcertsByDateUseCase;
        this.getDetailConcertByIdUseCase = getDetailConcertByIdUseCase;
    }
    //날짜로 콘서트 기본정보 조회
    public List<Concert> getConcertsByDate(LocalDate date) {
        return getConcertsByDateUseCase.execute(date);
    }
    // 콘서트 ID로 콘서트 상세조회
    public Concert getDetailConcertById(Long id) {
        return getDetailConcertByIdUseCase.execute(id);
    }

}