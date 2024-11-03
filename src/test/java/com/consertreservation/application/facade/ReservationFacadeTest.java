package com.consertreservation.application.service.facade;

import com.consertreservation.application.service.usecase.FindReservationsByUserIdUseCase;
import com.consertreservation.domain.model.Reservation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ReservationFacadeTest {

    @Mock
    private FindReservationsByUserIdUseCase findReservationsByUserIdUseCase;

    @InjectMocks
    private ReservationFacade reservationFacade;

    @Test
    public void testFindReservationsByUserId() {
        // 준비
        Reservation reservation = new Reservation(); // 필요한 속성 설정
        List<Reservation> expectedReservations = Arrays.asList(reservation);
        when(findReservationsByUserIdUseCase.execute("user1")).thenReturn(expectedReservations);

        // 실행
        List<Reservation> actualReservations = reservationFacade.findReservationsByUserId("user1");

        // 검증
        assertEquals(expectedReservations, actualReservations);
    }
}
