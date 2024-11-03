package com.consertreservation.application.usecase;

import com.consertreservation.application.service.usecase.ReserveSeatUseCase;
import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ReserveSeatUseCaseTest {

    @Mock
    private SeatRepository seatRepository; // 좌석 리포지토리 모킹

    @InjectMocks
    private ReserveSeatUseCase reserveSeatUseCase; // 테스트할 유스케이스 인스턴스

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Mockito 초기화
    }

    @Test
    public void testExecute_ReservesAvailableSeat() {
        Long seatId = 1L; // 테스트할 좌석 ID
        Seat seat = new Seat("A1", true, null); // 예약 가능한 좌석 생성

        // 리포지토리 호출을 모킹하여 해당 좌석을 반환하도록 설정
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));

        // 유스케이스 실행
        reserveSeatUseCase.execute(seatId);

        // 좌석 예약 후 상태 검증
        assertFalse(seat.isAvailable()); // 좌석이 예약 상태로 변경되어야 함
    }

    @Test
    public void testExecute_ThrowsExceptionWhenSeatNotAvailable() {
        Long seatId = 1L; // 테스트할 좌석 ID
        Seat seat = new Seat("A1", false, null); // 이미 예약된 좌석 생성

        // 리포지토리 호출을 모킹하여 해당 좌석을 반환하도록 설정
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));

        // 예약 불가 좌석에 대해 예외가 발생하는지 검증
        assertThrows(IllegalStateException.class, () -> reserveSeatUseCase.execute(seatId));
    }

    @Test
    public void testExecute_ThrowsExceptionWhenSeatNotFound() {
        Long seatId = 1L; // 존재하지 않는 좌석 ID

        // 리포지토리 호출을 모킹하여 좌석을 찾지 못하도록 설정
        when(seatRepository.findById(seatId)).thenReturn(Optional.empty());

        // 좌석이 존재하지 않을 때 예외가 발생하는지 검증
        assertThrows(NoSuchElementException.class, () -> reserveSeatUseCase.execute(seatId));
    }
}
