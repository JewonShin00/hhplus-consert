package com.consertreservation.application.service.facade;

import com.consertreservation.application.service.usecase.concert.GetAvailableSeatsByConcertIdUseCase;
import com.consertreservation.application.service.usecase.concert.GetConcertsByDateUseCase;
import com.consertreservation.application.service.usecase.concert.GetDetailConcertByIdUseCase;
import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.model.Seat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ConcertFacadeTest {

    @Mock
    private GetConcertsByDateUseCase getConcertsByDateUseCase;

    @Mock
    private GetDetailConcertByIdUseCase getDetailConcertByIdUseCase;

    @Mock
    private GetAvailableSeatsByConcertIdUseCase getAvailableSeatsByConcertIdUseCase;

    @InjectMocks
    private ConcertFacade concertFacade;

    public ConcertFacadeTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("콘서트 ID에 대한 상세 정보와 예약 가능한 좌석이 올바르게 반환되는지 테스트")
    public void testGetDetailConcertById() {
        Long concertId = 1L;
        Concert concert = new Concert(concertId, "Concert A", null, "Location A");
        Seat seat1 = new Seat("A1", true, concert,null);
        Seat seat2 = new Seat("A2", true, concert,null);

        when(getDetailConcertByIdUseCase.execute(concertId)).thenReturn(concert);
        when(getAvailableSeatsByConcertIdUseCase.execute(concertId)).thenReturn(Arrays.asList(seat1, seat2));

        Concert resultConcert = concertFacade.getDetailConcertById(concertId);

        assertEquals(concertId, resultConcert.getConcertId());
        assertEquals(2, resultConcert.getSeats().size());
    }
}
