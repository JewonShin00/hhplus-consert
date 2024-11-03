package com.consertreservation.application.usecase;

import com.consertreservation.application.service.usecase.concert.GetDetailConcertByIdUseCase;
import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.repository.ConcertRepository;
import com.consertreservation.domain.repository.SeatRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetDetailConcertByIdUseCaseTest {

    @Mock
    private ConcertRepository concertRepository;

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private GetDetailConcertByIdUseCase getDetailConcertByIdUseCase;

    public GetDetailConcertByIdUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("콘서트 ID로 상세 정보를 조회하고 예약 가능한 좌석을 함께 반환하는지 테스트")
    public void testExecute_ReturnsConcertWithAvailableSeats() {
        Long concertId = 1L;
        Concert concert = new Concert(concertId, "Concert A", null, "Location A");
        Seat seat1 = new Seat("A1", true, concert, null);
        Seat seat2 = new Seat("A2", true, concert, null);

        when(concertRepository.findById(concertId)).thenReturn(java.util.Optional.of(concert));
        when(seatRepository.findByConcert_ConcertIdAndIsAvailable(concertId, true))
                .thenReturn(Arrays.asList(seat1, seat2));

        Concert resultConcert = getDetailConcertByIdUseCase.execute(concertId);

        assertEquals(concertId, resultConcert.getConcertId());
        assertEquals(2, resultConcert.getSeats().size());
    }

    @Test
    @DisplayName("콘서트를 찾지 못할 경우 NoSuchElementException이 발생하는지 테스트")
    public void testExecute_ThrowsException_WhenConcertNotFound() {
        Long concertId = 1L;

        when(concertRepository.findById(concertId)).thenReturn(java.util.Optional.empty());

        try {
            getDetailConcertByIdUseCase.execute(concertId);
        } catch (NoSuchElementException e) {
            assertEquals("Concert not found with id: 1", e.getMessage());
        }
    }
}
