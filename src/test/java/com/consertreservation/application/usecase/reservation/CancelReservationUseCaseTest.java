package com.consertreservation.application.usecase.reservation;

import com.consertreservation.application.service.usecase.reservation.CancelReservationUseCase;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // 테스트 프로파일 활성화
@Transactional // 트랜잭션 범위 내에서 테스트 실행
class CancelReservationUseCaseTest {

    @Autowired
    private CancelReservationUseCase cancelReservationUseCase;

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

        // 테스트용 Concert 생성 및 저장
        Concert concert = new Concert();
        concert.setConcertId(1L);
        concert.setName("Test Concert");
        concert.setDate(LocalDate.now());
        concert.setPrice(100.0);
        concertRepository.save(concert);

        // 테스트용 Seat 생성 및 저장
        Seat seat = new Seat();
        seat.setSeatNumber("A1");
        seat.setAvailable(false);
        seat.setSeatStatus(Seat.SeatStatus.RESERVED);
        seat.setReservedBy("user1");
        seat.setConcert(concert);
        seatRepository.save(seat);
    }

    @Test
    @DisplayName("예약취소 테스트_대기열이 비어 있는 상태에서 좌석이 예약된 경우")
    void shouldCancelReservationAndMakeSeatAvailableWhenWaitlistEmpty() {
        // Given: 대기열이 비어 있는 상태에서 좌석이 예약된 경우
        Seat seat = seatRepository.findByConcertIdAndSeatNumber(1L, "A1")
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        // When: 예약 취소를 실행
        cancelReservationUseCase.execute(1L, "A1");

        // Then: 좌석 상태가 AVAILABLE로 변경된다.
        Seat updatedSeat = seatRepository.findByConcertIdAndSeatNumber(1L, "A1")
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        assertTrue(updatedSeat.isAvailable());
        assertEquals(Seat.SeatStatus.AVAILABLE, updatedSeat.getSeatStatus());
        assertNull(updatedSeat.getReservedBy());
    }

    @Test
    @DisplayName("예약취소 테스트_대기열에 사용자가 존재하는 상태에서 좌석이 예약된 경우")
    void shouldCancelReservationAndAssignSeatToWaitlistUser() {
        // Given: 대기열에 사용자가 존재하는 상태에서 좌석이 예약된 경우
        Seat seat = seatRepository.findByConcertIdAndSeatNumber(1L, "A1")
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        Waitlist waitlistEntry = new Waitlist();
        waitlistEntry.setConcertId(1L);
        waitlistEntry.setSeatNumber("A1");
        waitlistEntry.setUserId("user2");
        waitlistEntry.setPosition(1);
        waitlistEntry.setReservationAttemptTime(LocalDateTime.now());
        waitlistRepository.save(waitlistEntry);

        // When: 예약 취소를 실행
        cancelReservationUseCase.execute(1L, "A1");

        // Then: 좌석이 대기열 첫 번째 사용자에게 예약되고 대기열이 비워진다.
        Seat updatedSeat = seatRepository.findByConcertIdAndSeatNumber(1L, "A1")
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        assertFalse(updatedSeat.isAvailable());
        assertEquals(Seat.SeatStatus.RESERVED, updatedSeat.getSeatStatus());
        assertEquals("user2", updatedSeat.getReservedBy());

        // 대기열이 비워졌는지 확인
        assertTrue(waitlistRepository.findAll().isEmpty());
    }
}
