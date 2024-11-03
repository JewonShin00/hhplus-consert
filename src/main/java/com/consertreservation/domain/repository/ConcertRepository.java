package com.consertreservation.domain.repository;

import com.consertreservation.domain.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {
    // 특정 날짜의 콘서트를 조회
    List<Concert> findByDate(LocalDate date);
}
