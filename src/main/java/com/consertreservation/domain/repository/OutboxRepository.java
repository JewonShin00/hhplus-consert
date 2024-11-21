package com.consertreservation.domain.repository;

import com.consertreservation.domain.model.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEvent, Long> {
    List<OutboxEvent> findByStatus(String status); // PENDING 상태 조회
}
