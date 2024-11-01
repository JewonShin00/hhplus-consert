package com.consertreservation.domain.repository;

import com.consertreservation.domain.model.Concert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConcertRepository {
    List<Concert> findByDate(LocalDate date);
    Optional<Concert> findById(Long id);

}