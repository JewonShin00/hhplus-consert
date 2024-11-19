package com.consertreservation.application.service.facade;

import com.consertreservation.application.service.usecase.concert.GetConcertsByDateUseCase;
import com.consertreservation.application.service.usecase.concert.GetDetailConcertByIdUseCase;
import com.consertreservation.application.service.usecase.concert.GetAvailableSeatsByConcertIdUseCase;
import com.consertreservation.application.service.usecase.waitlist.IssueQueueTokenUseCase;
import com.consertreservation.application.service.usecase.waitlist.AddToWaitlistUseCase;
import com.consertreservation.application.service.usecase.waitlist.GetCurrentQueuePositionUseCase;
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
    private final AddToWaitlistUseCase addToWaitlistUseCase;
    private final IssueQueueTokenUseCase issueQueueTokenUseCase;
    private final GetCurrentQueuePositionUseCase getCurrentQueuePositionUseCase;

    public ConcertFacade(GetConcertsByDateUseCase getConcertsByDateUseCase,
                         GetDetailConcertByIdUseCase getDetailConcertByIdUseCase,
                         GetAvailableSeatsByConcertIdUseCase getAvailableSeatsByConcertIdUseCase, AddToWaitlistUseCase addToWaitlistUseCase, IssueQueueTokenUseCase issueQueueTokenUseCase, GetCurrentQueuePositionUseCase getCurrentQueuePositionUseCase) {
        this.getConcertsByDateUseCase = getConcertsByDateUseCase;
        this.getDetailConcertByIdUseCase = getDetailConcertByIdUseCase;
        this.getAvailableSeatsByConcertIdUseCase = getAvailableSeatsByConcertIdUseCase;
        this.addToWaitlistUseCase = addToWaitlistUseCase;
        this.issueQueueTokenUseCase = issueQueueTokenUseCase;
        this.getCurrentQueuePositionUseCase = getCurrentQueuePositionUseCase;
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

    public List<Seat> getAvailableSeatsByConcertId(Long concertId) {
        return getAvailableSeatsByConcertIdUseCase.execute(concertId);
    }

    public String addToWaitlistAndIssueToken(Long concertId, String userId, String seatNumber) {
        // 대기열에 사용자 추가
        addToWaitlistUseCase.execute(concertId, userId, seatNumber);

        // 현재 대기열 순번 가져오기
        int queuePosition = getCurrentQueuePositionUseCase.execute(concertId, userId);

        // 토큰 발급
        return issueQueueTokenUseCase.execute(userId, concertId, queuePosition);
    }

}