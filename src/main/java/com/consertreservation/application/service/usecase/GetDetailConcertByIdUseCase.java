package com.consertreservation.application.service.usecase;

import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.repository.ConcertRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

// 콘서트 ID로 콘서트 상세조회
@Service
public class GetDetailConcertByIdUseCase {

    private final ConcertRepository concertRepository;

    public GetDetailConcertByIdUseCase(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public Concert execute(Long id) {
        return concertRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Concert not found with id: " + id));
    }
}
