package com.consertreservation.application.service;

import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.repository.ConcertRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertServiceImpl implements ConcertService {
    private final ConcertRepository concertRepository;

    public ConcertServiceImpl(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    @Override
    public List<Object> getAvailableConcerts(String date) {
        // 문자열 형태의 날짜를 LocalDate로 변환
        LocalDate localDate = LocalDate.parse(date);

        // 특정 날짜에 해당하는 콘서트 목록 조회
        List<Concert> concerts = concertRepository.findByDate(localDate);

        // 콘서트 이름만 반환 (서비스에서 필요한 데이터만 가공)
        return concerts.stream()
                .map(Concert::getName)
                .collect(Collectors.toList());
    }
}