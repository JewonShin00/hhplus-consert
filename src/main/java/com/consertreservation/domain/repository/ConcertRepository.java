package com.consertreservation.domain.repository;

import com.consertreservation.domain.model.Concert;

import java.time.LocalDate;
import java.util.List;

public interface ConcertRepository {
    List<Concert> findByDate(LocalDate date);

    List<Concert> findByDate(String date);
}