package com.consertreservation.domain.repository;

import com.consertreservation.domain.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.concert.concertId = :concertId AND s.isAvailable = true")
    List<Seat> findAvailableSeatsByConcertId(Long concertId);

    @Query("SELECT s FROM Seat s WHERE s.concert.concertId = :concertId AND s.seatNumber = :seatNumber")
    Optional<Seat> findByConcertIdAndSeatNumber(Long concertId, String seatNumber);

}
