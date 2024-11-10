package com.consertreservation.application.service.usecase.concert;

import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.repository.ConcertRepository;
import com.consertreservation.domain.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

// 콘서트 ID로 콘서트 상세조회
@Service
public class GetDetailConcertByIdUseCase {

    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;

    public GetDetailConcertByIdUseCase(ConcertRepository concertRepository, SeatRepository seatRepository) {
        this.concertRepository = concertRepository;
        this.seatRepository = seatRepository;
    }

    public Concert execute(Long concertId) {
        Concert concert = concertRepository.findById(concertId)
                .orElseThrow(() -> new NoSuchElementException("Concert not found with id: " + concertId));

        // 예약 가능한 좌석 조회
        List<Seat> availableSeats = seatRepository.findAvailableSeatsByConcertId(concertId);
        concert.setSeats(availableSeats); // Concert에 좌석 정보 설정

        return concert;
    }
}
