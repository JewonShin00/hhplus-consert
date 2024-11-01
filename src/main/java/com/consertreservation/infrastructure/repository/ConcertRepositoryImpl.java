package com.consertreservation.infrastructure.repository;

import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.repository.ConcertRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

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
    public Optional<Concert> findById(Long id) {
        // ID로 콘서트를 조회하는 JPQL 쿼리
        String query = "SELECT c FROM Concert c WHERE c.concertId = :id";
        List<Concert> results = entityManager.createQuery(query, Concert.class)
                .setParameter("id", id)
                .getResultList();
        return results.stream().findFirst(); // 결과가 있으면 첫 번째 항목 반환, 없으면 빈 Optional 반환
    }
}
