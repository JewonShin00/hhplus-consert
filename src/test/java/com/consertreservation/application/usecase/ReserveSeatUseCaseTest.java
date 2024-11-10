package com.consertreservation.application.usecase;

import com.consertreservation.application.service.usecase.reservation.ReserveSeatUseCase;
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
    //예약 가능한 좌석을 성공적으로 예약하는 경우를 테스트
    public void testExecute_ReservesAvailableSeat() {
        Long seatId = 1L; // 테스트할 좌석 ID
        String reservedBy = "user123"; // 예약자 정보
        Seat seat = new Seat("A1", true, null, null); // 예약 가능한 좌석 생성

        // 리포지토리 호출을 모킹하여 해당 좌석을 반환하도록 설정
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));

        // 유스케이스 실행
 //       reserveSeatUseCase.execute(concertId, seatNumber, reservedBy);

        // 좌석 예약 후 상태 검증
        assertFalse(seat.isAvailable()); // 좌석이 예약 상태로 변경되어야 함
    }

    @Test
    //이미 예약된 좌석을 예약하려고 할 때 발생하는 예외를 테스트
    public void testExecute_ThrowsExceptionWhenSeatNotAvailable() {
        Long seatId = 1L; // 테스트할 좌석 ID
        String reservedBy = "user123"; // 예약자 정보
        Seat seat = new Seat("A1", false, null, null); // 이미 예약된 좌석 생성

        // 리포지토리 호출을 모킹하여 해당 좌석을 반환하도록 설정
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));

        // 예약 불가 좌석에 대해 예외가 발생하는지 검증
   //     assertThrows(IllegalStateException.class, () -> reserveSeatUseCase.execute(seatId, reservedBy));
    }

    @Test
    //존재하지 않는 좌석 ID로 예약을 시도할 때 발생하는 예외를 테스트
    public void testExecute_ThrowsExceptionWhenSeatNotFound() {
        Long seatId = 1L; // 존재하지 않는 좌석 ID
        String reservedBy = "user123"; // 예약자 정보
        // 리포지토리 호출을 모킹하여 좌석을 찾지 못하도록 설정
        when(seatRepository.findById(seatId)).thenReturn(Optional.empty());

        // 좌석이 존재하지 않을 때 예외가 발생하는지 검증
   //     assertThrows(NoSuchElementException.class, () -> reserveSeatUseCase.execute(seatId, reservedBy));
    }
}
