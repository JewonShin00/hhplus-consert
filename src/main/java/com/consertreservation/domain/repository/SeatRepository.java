package com.consertreservation.domain.repository;

import com.consertreservation.domain.model.Seat;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.concert.concertId = :concertId AND s.isAvailable = true")
    List<Seat> findAvailableSeatsByConcertId(Long concertId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT s FROM Seat s WHERE s.concert.concertId = :concertId AND s.seatNumber = :seatNumber")
    Optional<Seat> findByConcertIdAndSeatNumber(Long concertId, String seatNumber);

    @Transactional
    @Modifying
    @Query("DELETE FROM Seat")
    //Version 필드가 필요 없이 삭제
    void deleteAllSeatsWithoutVersion();
}
