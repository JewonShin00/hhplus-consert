package com.consertreservation.application.usecase.payment;

import com.consertreservation.application.service.payment.PaymentService;
import com.consertreservation.application.service.usecase.payment.PaymentUseCase;
import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.repository.SeatRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentUseCaseTest {

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentUseCase paymentUseCase;

    public PaymentUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("결제 성공 시 좌석이 예약되고 true 반환")
    void testSuccessfulPayment() {
        Long concertId = 1L;
        String seatNumber = "A1";
        String userId = "user123";
        double amount = 100.0;

        Seat seat = new Seat(seatNumber, true, null, null);
        when(seatRepository.findByConcertIdAndSeatNumber(concertId, seatNumber)).thenReturn(java.util.Optional.of(seat));
        when(paymentService.processPayment(userId, concertId, seatNumber, amount)).thenReturn(true);

        boolean result = paymentUseCase.execute(concertId, seatNumber, userId, amount);

        assertTrue(result);
        assertFalse(seat.isAvailable());
        assertEquals(userId, seat.getReservedBy());
        verify(seatRepository).save(seat);
    }

    @Test
    @DisplayName("결제 실패 시 예외 발생")
    void testFailedPayment() {
        Long concertId = 1L;
        String seatNumber = "A1";
        String userId = "user123";
        double amount = 100.0;

        Seat seat = new Seat(seatNumber, true, null, null);
        when(seatRepository.findByConcertIdAndSeatNumber(concertId, seatNumber)).thenReturn(java.util.Optional.of(seat));
        when(paymentService.processPayment(userId, concertId, seatNumber, amount)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> paymentUseCase.execute(concertId, seatNumber, userId, amount));
        verify(seatRepository, never()).save(seat);
    }
}
