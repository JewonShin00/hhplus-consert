package com.consertreservation.application.usecase;

import com.consertreservation.application.service.usecase.waitlist.AddToWaitlistUseCase;
import com.consertreservation.application.service.usecase.waitlist.CheckWaitlistForSeatUseCase;
import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.domain.repository.ConcertRepository;
import com.consertreservation.domain.repository.SeatRepository;
import com.consertreservation.domain.repository.WaitlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManageWaitlistUseCaseTest {

    private WaitlistRepository waitlistRepository;
    private ConcertRepository concertRepository;
    private AddToWaitlistUseCase addToWaitlistUseCase;
    private CheckWaitlistForSeatUseCase checkWaitlistForSeatUseCase;
    private SeatRepository seatRepository;

    @BeforeEach
    void setUp() {
        waitlistRepository = Mockito.mock(WaitlistRepository.class);
        concertRepository = Mockito.mock(ConcertRepository.class);
        seatRepository = Mockito.mock(SeatRepository.class);
        addToWaitlistUseCase = new AddToWaitlistUseCase(waitlistRepository, concertRepository);
        checkWaitlistForSeatUseCase = new CheckWaitlistForSeatUseCase(waitlistRepository, seatRepository, concertRepository);
    }

    @Test
        // 대기열에 사용자를 성공적으로 추가하는 경우를 테스트
    void testAddToWaitlist_Success() {
        Long concertId = 1L;
        String userId = "user123";
        String seatNumber = "11";
        // 콘서트가 존재한다고 가정
        when(concertRepository.existsById(concertId)).thenReturn(true);
        // 사용자가 대기열에 없다고 가정
        when(waitlistRepository.existsByConcertIdAndUserIdAndSeatNumber(concertId, userId, seatNumber)).thenReturn(false);

        // 대기열에 추가
        addToWaitlistUseCase.execute(concertId, userId, seatNumber);

        // 대기열에 추가된 항목이 저장된지 확인
        verify(waitlistRepository, times(1)).save(any(Waitlist.class));
    }

    @Test
        // 이미 대기열에 있는 사용자가 다시 추가하려고 할 때 예외가 발생하는지 테스트
    void testAddToWaitlist_UserAlreadyInWaitlist() {
        Long concertId = 1L;
        String userId = "user123";
        String seatNumber = "11";

        when(concertRepository.existsById(concertId)).thenReturn(true);
        when(waitlistRepository.existsByConcertIdAndUserIdAndSeatNumber(concertId, userId, seatNumber)).thenReturn(true);

        // 예외가 발생할 것으로 예상
        assertThrows(IllegalArgumentException.class, () -> {
            addToWaitlistUseCase.execute(concertId, userId, seatNumber);
        });
    }

    @Test
        // 존재하지 않는 콘서트 ID로 대기열에 추가하려고 할 때 예외가 발생하는지 테스트
    void testAddToWaitlist_ConcertNotFound() {
        Long concertId = 1L;
        String userId = "user123";
        String seatNumber = "11";
        when(concertRepository.existsById(concertId)).thenReturn(false);

        // 예외가 발생할 것으로 예상
        assertThrows(IllegalArgumentException.class, () -> {
            addToWaitlistUseCase.execute(concertId, userId, seatNumber);
        });
    }

    @Test
// 대기열에서 차례가 된 사용자가 좌석 확인 화면으로 이동할 수 있는지 테스트
    void testCheckWaitlistForSeat_Success() {
        Long concertId = 1L;
        String userId = "user123";
        String seatNumber = "11";

        Waitlist waitlistEntry = new Waitlist(concertId, userId, 1, LocalDateTime.now(), seatNumber);

        when(waitlistRepository.findByConcertId(concertId)).thenReturn(Collections.singletonList(waitlistEntry));
        // Mock the behavior of seatRepository to return available seats
        Seat availableSeat = new Seat(); // Create an instance of Seat
        when(seatRepository.findAvailableSeatsByConcertId(concertId)).thenReturn(Collections.singletonList(availableSeat));

        // 사용자의 차례가 되었는지 확인
        String result = checkWaitlistForSeatUseCase.execute(concertId, userId);
        assertEquals("It's your turn! Please check the available seats.", result);
    }


    @Test
// 대기열에서 차례가 아직 아닌 사용자가 좌석 확인 시도할 때 예외 발생하는지 테스트
    void testCheckWaitlistForSeat_NotYetTurn() {
        Long concertId = 1L;
        String userId = "user123";
        String seatNumber = "11";

        Waitlist waitlistEntry = new Waitlist(concertId, userId, 2, LocalDateTime.now(), seatNumber); // 순번을 2로 설정

        // 대기열 목록을 Mock으로 설정
        when(waitlistRepository.findByConcertId(concertId)).thenReturn(Collections.singletonList(waitlistEntry));

        // 예외가 발생할 것으로 예상
        assertThrows(IllegalArgumentException.class, () -> {
            checkWaitlistForSeatUseCase.execute(concertId, userId); // 유스케이스 호출
        });
    }

}
