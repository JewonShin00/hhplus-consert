package com.consertreservation.application.usecase.reservation;

import com.consertreservation.application.service.usecase.reservation.ReserveSeatUseCase;
import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.domain.repository.ConcertRepository;
import com.consertreservation.domain.repository.SeatRepository;
import com.consertreservation.domain.repository.WaitlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // 테스트 프로파일 활성화
@Transactional // H2 데이터베이스를 트랜잭션 범위 내에서 테스트
class ReserveSeatUseCaseTest {

    @Autowired
    private ReserveSeatUseCase reserveSeatUseCase;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private WaitlistRepository waitlistRepository;

    @Autowired
    private ConcertRepository concertRepository;

    @BeforeEach
    void setUp() {
        // 데이터 초기화
        waitlistRepository.deleteAll();
        seatRepository.deleteAll();
        concertRepository.deleteAll();

        // 테스트용 Concert 데이터 생성
        Concert concert = new Concert();
        concert.setConcertId(1L);
        concert.setName("Test Concert");
        concert.setDate(LocalDate.now());
        concert.setPrice(100.0);
        concertRepository.save(concert);

        // 테스트용 Seat 데이터 생성
        Seat seat = new Seat();
        seat.setSeatNumber("A1");
        seat.setAvailable(true);
        seat.setSeatStatus(Seat.SeatStatus.AVAILABLE);
        seat.setConcert(concert); // Concert와 Seat 연결
        seatRepository.save(seat);
    }

    @Test
    @DisplayName("예약 테스트_좌석이 예약가능한 상태일 때")
    void shouldReserveSeatWhenAvailable() {
        // Given: 예약 가능한 좌석이 존재하는 상태

        // When: 좌석 예약을 시도하면
        reserveSeatUseCase.execute(1L, "A1", "user1");

        // Then: 좌석 상태가 RESERVED로 변경되고 예약자가 설정된다.
        Seat updatedSeat = seatRepository.findByConcertIdAndSeatNumber(1L, "A1")
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        assertFalse(updatedSeat.isAvailable());
        assertEquals(Seat.SeatStatus.RESERVED, updatedSeat.getSeatStatus());
        assertEquals("user1", updatedSeat.getReservedBy());
    }

    @Test
    @DisplayName("예약 테스트_좌석이 이미 RESERVED 상태인 경우")
    void shouldAddToWaitlistWhenSeatUnavailable() {
        // Given: 좌석이 이미 RESERVED 상태인 경우
        Seat seat = seatRepository.findByConcertIdAndSeatNumber(1L, "A1")
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        seat.setAvailable(false);
        seat.setReservedBy("user2");
        seat.setSeatStatus(Seat.SeatStatus.RESERVED);
        seatRepository.save(seat);

        // When: 다른 사용자가 동일 좌석을 예약 시도하면
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                reserveSeatUseCase.execute(1L, "A1", "user3")
        );

        // Then: 대기열에 추가되고 예외가 발생한다.
        assertEquals("Seat is not available, added to waitlist", exception.getMessage());
        Waitlist waitlistEntry = waitlistRepository.findAll().get(0); // 대기열 첫 번째 항목 확인
        assertEquals("user3", waitlistEntry.getUserId());
        assertEquals("A1", waitlistEntry.getSeatNumber());
    }
}
