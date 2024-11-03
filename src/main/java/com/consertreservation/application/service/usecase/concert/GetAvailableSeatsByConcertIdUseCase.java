package com.consertreservation.application.service.usecase.seat;

import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
// 콘서트 ID에 따라 예약 가능한 좌석 조회
@Service
public class GetAvailableSeatsByConcertIdUseCase {

    private final SeatRepository seatRepository;

    public GetAvailableSeatsByConcertIdUseCase(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    // 콘서트 ID에 따라 예약 가능한 좌석 조회
    public List<Seat> execute(Long concertId) {
        return seatRepository.findByConcert_ConcertIdAndIsAvailable(concertId, true);
    }
}
