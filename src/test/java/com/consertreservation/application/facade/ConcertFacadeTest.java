package com.consertreservation.application.facade;

import com.consertreservation.application.service.facade.ConcertFacade;
import com.consertreservation.application.service.facade.ReservationFacade;
import com.consertreservation.application.service.usecase.concert.GetAvailableSeatsByConcertIdUseCase;
import com.consertreservation.application.service.usecase.concert.GetConcertsByDateUseCase;
import com.consertreservation.application.service.usecase.concert.GetDetailConcertByIdUseCase;
import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ConcertFacadeTest {

    @Autowired
    private GetConcertsByDateUseCase getConcertsByDateUseCase;

    @Autowired
    private GetDetailConcertByIdUseCase getDetailConcertByIdUseCase;

    @Autowired
    private GetAvailableSeatsByConcertIdUseCase getAvailableSeatsByConcertIdUseCase;

    @Autowired
    private ConcertFacade concertFacade;

    @Autowired
    private ReservationFacade reservationFacade;

    @Autowired
    private SeatRepository seatRepository;

    @Test
    @DisplayName("특정 날짜에 있는 콘서트 조회 테스트")
    public void testGetConcertByDate() {
        // 테스트할 날짜를 데이터베이스에 실제 존재하는 날짜로 설정합니다.
        LocalDate testDate = LocalDate.of(2024, 11, 20); // 예시로 2024-11-20 날짜 사용

        // 데이터베이스에서 해당 날짜의 콘서트를 조회합니다.
        List<Concert> concerts = concertFacade.getConcertsByDate(testDate);

        // 조회된 콘서트가 존재하는지 검증
        assertFalse(concerts.isEmpty(), "해당 날짜에 콘서트가 존재해야 합니다.");

        // 조회된 콘서트를 출력해 확인
        concerts.forEach(concert -> System.out.println("Concert ::: " + concert));
    }

    @Test
    @DisplayName("특정 콘서트 ID로 예약 가능한 좌석 조회 테스트")
    public void testGetAvailableSeatsByConcertId() {
        // 테스트할 콘서트 ID 설정 (데이터베이스에 존재하는 concert_id로 설정)
        Long concertId = 1L;

        // 예약 가능한 좌석 조회
        List<Seat> availableSeats = concertFacade.getAvailableSeatsByConcertId(concertId);
        // 조회된 좌석 목록이 비어 있지 않은지 확인
        assertNotNull(availableSeats, "예약 가능한 좌석 목록은 null이 아니어야 합니다.");
        assertFalse(availableSeats.isEmpty(), "해당 콘서트에 예약 가능한 좌석이 존재해야 합니다.");

        // 각 좌석이 예약 가능한 상태인지 확인
        for (Seat seat : availableSeats) {
            assertTrue(seat.isAvailable(), "모든 좌석은 예약 가능해야 합니다.");
            System.out.println("Available Seat ::: " + seat);
        }
    }

    @Test
    @DisplayName("특정 날짜의 콘서트를 조회하고 필드를 검증")
    public void testGetConcertByDateWithDetailedFieldCheck() {
        LocalDate testDate = LocalDate.of(2024, 11, 20); // 테스트 날짜
        List<Concert> concerts = concertFacade.getConcertsByDate(testDate);

        // 콘서트 목록이 비어 있지 않은지 검증
        assertFalse(concerts.isEmpty(), "해당 날짜에 콘서트가 존재해야 합니다.");

        // 첫 번째 콘서트 필드 검증
        Concert concert = concerts.get(0);
        assertEquals(1L, concert.getConcertId(), "Concert ID가 일치하지 않습니다.");
        assertEquals("Classic Concert", concert.getName(), "Concert 이름이 일치하지 않습니다.");
        assertEquals(testDate, concert.getDate(), "Concert 날짜가 일치하지 않습니다.");
        assertEquals("Seoul Arts Center", concert.getLocation(), "Concert 장소가 일치하지 않습니다.");
        assertEquals("A classic music concert", concert.getDescription(), "Concert 설명이 일치하지 않습니다.");
        assertEquals(50000.0, concert.getPrice(), "Concert 가격이 일치하지 않습니다.");

    }

    @Test
    @DisplayName("특정 콘서트의 예약 가능한 좌석을 조회하고 필드를 검증")
    public void testGetAvailableSeatsByConcertIdWithDetailedFieldCheck() {
        Long concertId = 1L; // 테스트할 Concert ID
        List<Seat> availableSeats = concertFacade.getAvailableSeatsByConcertId(concertId);

        // 예약 가능한 좌석이 비어 있지 않은지 검증
        assertFalse(availableSeats.isEmpty(), "예약 가능한 좌석이 존재해야 합니다.");

        // 첫 번째 좌석 필드 검증
        Seat seat = availableSeats.get(0);
        assertEquals("1", seat.getSeatNumber(), "Seat 번호가 일치하지 않습니다.");
        assertTrue(seat.isAvailable(), "Seat 사용 가능 여부가 일치하지 않습니다.");

        // 각 좌석이 예약 가능한 상태인지 확인
        for (Seat seats : availableSeats) {
            System.out.println("SeatNumber ::: " + seats.getSeatNumber() +" == Available Seat ::: " + seats.isAvailable());
        }
    }


    @Test
    @Commit
    @DisplayName("concert_id와 seat_number 조합으로 좌석 예약 상태 반영 테스트_통합테스트")
    public void testReserveSeatByConcertIdAndSeatNumber() {
        Long concertId = 1L;
        String seatNumber = "11";
        String userId = "123";  // String 타입으로 일치시키기

        // 좌석 조회
        Seat seat = seatRepository.findByConcertIdAndSeatNumber(concertId, seatNumber)
                .orElseThrow(() -> new NoSuchElementException("Seat not found with concert_id: " + concertId + " and seat_number: " + seatNumber));

        // 예약 전 상태 확인
        assertTrue(seat.isAvailable(), "좌석은 예약 가능해야 합니다.");

        // 좌석 예약
        reservationFacade.reserveSeat(concertId, seatNumber, userId);

        // 좌석 예약 후 상태 확인
        Seat reservedSeat = seatRepository.findByConcertIdAndSeatNumber(concertId, seatNumber)
                .orElseThrow(() -> new NoSuchElementException("Reserved seat not found"));
        assertFalse(reservedSeat.isAvailable(), "좌석이 예약된 상태여야 합니다.");
        assertEquals(userId, reservedSeat.getReservedBy(), "예약자 정보가 일치해야 합니다.");
    }






}
