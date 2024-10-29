package com.consertreservation.infrastructure.repository;

import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.repository.ConcertRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ConcertRepositoryImpl implements ConcertRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Concert> findByDate(LocalDate date) {
        // JPQL을 사용해 특정 날짜의 콘서트를 조회
        String query = "SELECT c FROM Concert c WHERE c.date = :date";
        return entityManager.createQuery(query, Concert.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Concert> findByDate(String date) {
        return List.of();
    }
}