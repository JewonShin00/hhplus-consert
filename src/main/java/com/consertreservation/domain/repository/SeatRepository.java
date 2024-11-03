package com.consertreservation.domain.repository;

import com.consertreservation.domain.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByConcert_ConcertIdAndIsAvailable(Long concertId, boolean isAvailable);
}
