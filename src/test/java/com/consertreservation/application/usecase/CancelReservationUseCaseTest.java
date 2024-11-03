package com.consertreservation.application.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.consertreservation.application.service.usecase.CancelReservationUseCase;
import com.consertreservation.domain.model.Reservation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.consertreservation.domain.repository.ReservationRepository;

import java.util.Optional;

@SpringBootTest
public class CancelReservationUseCaseTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private CancelReservationUseCase cancelReservationUseCase;

    @Test
    public void cancelReservationTest() {
        // Given
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);
        reservation.setStatus("Booked");

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        // When
        cancelReservationUseCase.execute(reservationId);

        // Then
        assertEquals("Cancelled", reservation.getStatus());
        verify(reservationRepository).save(reservation);
    }
}
