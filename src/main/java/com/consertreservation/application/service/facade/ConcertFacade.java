package com.consertreservation.application.service.facade;

import com.consertreservation.application.service.usecase.concert.GetConcertsByDateUseCase;
import com.consertreservation.application.service.usecase.concert.GetDetailConcertByIdUseCase;
import com.consertreservation.application.service.usecase.reservation.ReserveSeatUseCase;
import com.consertreservation.application.service.usecase.concert.GetAvailableSeatsByConcertIdUseCase;
import com.consertreservation.application.service.usecase.waitlist.AddToWaitlistUseCase;
import com.consertreservation.application.service.usecase.waitlist.CheckWaitlistForSeatUseCase;
import com.consertreservation.application.service.usecase.waitlist.GetWaitlistByConcertIdUseCase;
import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.model.Seat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConcertFacade {
    private final GetConcertsByDateUseCase getConcertsByDateUseCase;
    private final GetDetailConcertByIdUseCase getDetailConcertByIdUseCase;
    private final GetAvailableSeatsByConcertIdUseCase getAvailableSeatsByConcertIdUseCase;
    private final ReserveSeatUseCase reserveSeatUseCase;
    private final GetWaitlistByConcertIdUseCase getWaitlistByConcertIdUseCase;
    private final CheckWaitlistForSeatUseCase checkWaitlistForSeatUseCase;
    private final AddToWaitlistUseCase addToWaitlistUseCase;


    public ConcertFacade(GetConcertsByDateUseCase getConcertsByDateUseCase,
                         GetDetailConcertByIdUseCase getDetailConcertByIdUseCase,
                         GetAvailableSeatsByConcertIdUseCase getAvailableSeatsByConcertIdUseCase,
                         ReserveSeatUseCase reserveSeatUseCase,
                         GetWaitlistByConcertIdUseCase getWaitlistByConcertIdUseCase,
                         CheckWaitlistForSeatUseCase checkWaitlistForSeatUseCase,
                         AddToWaitlistUseCase addToWaitlistUseCase) {
        this.getConcertsByDateUseCase = getConcertsByDateUseCase;
        this.getDetailConcertByIdUseCase = getDetailConcertByIdUseCase;
        this.getAvailableSeatsByConcertIdUseCase = getAvailableSeatsByConcertIdUseCase;
        this.reserveSeatUseCase = reserveSeatUseCase;
        this.checkWaitlistForSeatUseCase = checkWaitlistForSeatUseCase;
        this.getWaitlistByConcertIdUseCase = getWaitlistByConcertIdUseCase;
        this.addToWaitlistUseCase = addToWaitlistUseCase;
    }
    //날짜로 콘서트 기본정보 조회
    public List<Concert> getConcertsByDate(LocalDate date) {
        return getConcertsByDateUseCase.execute(date);
    }

    public Concert getDetailConcertById(Long concertId) {
        Concert concert = getDetailConcertByIdUseCase.execute(concertId);
        List<Seat> availableSeats = getAvailableSeatsByConcertIdUseCase.execute(concertId);

        concert.setSeats(availableSeats); // 좌석 정보를 콘서트에 설정
        return concert;
    }
}