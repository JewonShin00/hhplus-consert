package com.consertreservation.application.scheduler;

import com.consertreservation.domain.model.OutboxEvent;
import com.consertreservation.domain.repository.OutboxRepository;
import com.consertreservation.kafka.KafkaOutboxPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxScheduler {

    private final OutboxRepository outboxRepository;
    private final KafkaOutboxPublisher kafkaOutboxPublisher;

    public OutboxScheduler(OutboxRepository outboxRepository, KafkaOutboxPublisher kafkaOutboxPublisher) {
        this.outboxRepository = outboxRepository;
        this.kafkaOutboxPublisher = kafkaOutboxPublisher;
    }

    @Scheduled(fixedRate = 5000) // 5초마다 실행
    public void processPendingEvents() {
        // PENDING 상태 처리
        List<OutboxEvent> pendingEvents = outboxRepository.findByStatus("PENDING");
        handleEvents(pendingEvents);
    }

    @Scheduled(fixedRate = 60000) // 1분마다 실행
    public void retryFailedEvents() {
        // FAILED 상태 재처리
        List<OutboxEvent> failedEvents = outboxRepository.findByStatus("FAILED");
        handleEvents(failedEvents);
    }

    private void handleEvents(List<OutboxEvent> events) {
        for (OutboxEvent event : events) {
            try {
                kafkaOutboxPublisher.publish(event);
                event.setStatus("PROCESSED");
            } catch (Exception e) {
                event.setStatus("FAILED");
                System.err.println("Kafka 발행 실패: " + e.getMessage());
            }
            outboxRepository.save(event);
        }
    }
}

