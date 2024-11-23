package com.consertreservation.kafka;

import com.consertreservation.domain.model.OutboxEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOutboxPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaOutboxPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(OutboxEvent event) {
        try {
            // Kafka로 메시지 발행
            kafkaTemplate.send(event.getAggregateType(), event.getPayload());
            System.out.println("Kafka로 이벤트 발행 성공: " + event);
        } catch (Exception e) {
            System.err.println("Kafka 발행 실패: " + e.getMessage());
            throw e; // 실패 시 예외를 다시 던져 처리
        }
    }
}
