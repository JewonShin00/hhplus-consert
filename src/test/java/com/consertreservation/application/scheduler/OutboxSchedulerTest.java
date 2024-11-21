package com.consertreservation.application.scheduler;

import com.consertreservation.domain.model.OutboxEvent;
import com.consertreservation.domain.repository.OutboxRepository;
import com.consertreservation.kafka.KafkaOutboxPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OutboxSchedulerTest {

    private OutboxRepository outboxRepository;
    private KafkaOutboxPublisher kafkaOutboxPublisher;
    private OutboxScheduler outboxScheduler;

    @BeforeEach
    void setUp() {
        outboxRepository = mock(OutboxRepository.class);
        kafkaOutboxPublisher = mock(KafkaOutboxPublisher.class);
        outboxScheduler = new OutboxScheduler(outboxRepository, kafkaOutboxPublisher);
    }

    @Test
    @DisplayName("FAILED 상태의 Outbox 이벤트를 재처리하여 Kafka로 발행 성공 시 상태가 PROCESSED로 변경된다.")
    void testRetryFailedEvents_Success() {
        // Given
        OutboxEvent failedEvent = new OutboxEvent();
        failedEvent.setId(1L);
        failedEvent.setAggregateType("Reservation");
        failedEvent.setAggregateId("A1");
        failedEvent.setEventType("SEAT_RESERVED");
        failedEvent.setPayload("{\"userId\": \"user1\", \"concertId\": \"1\", \"seatNumber\": \"A1\"}");
        failedEvent.setStatus("FAILED");

        when(outboxRepository.findByStatus("FAILED")).thenReturn(List.of(failedEvent));

        // When
        outboxScheduler.retryFailedEvents();

        // Then
        verify(kafkaOutboxPublisher).publish(failedEvent);
        verify(outboxRepository).save(failedEvent);
        assertThat(failedEvent.getStatus()).isEqualTo("PROCESSED");
    }

    @Test
    @DisplayName("Kafka 발행 실패 시 FAILED 상태로 유지된다.")
    void testRetryFailedEvents_Failure() {
        // Given
        OutboxEvent failedEvent = new OutboxEvent();
        failedEvent.setId(1L);
        failedEvent.setAggregateType("Reservation");
        failedEvent.setAggregateId("A1");
        failedEvent.setEventType("SEAT_RESERVED");
        failedEvent.setPayload("{\"userId\": \"user1\", \"concertId\": \"1\", \"seatNumber\": \"A1\"}");
        failedEvent.setStatus("FAILED");

        when(outboxRepository.findByStatus("FAILED")).thenReturn(List.of(failedEvent));
        doThrow(new RuntimeException("Kafka 발행 실패")).when(kafkaOutboxPublisher).publish(failedEvent);

        // When
        outboxScheduler.retryFailedEvents();

        // Then
        verify(kafkaOutboxPublisher).publish(failedEvent);
        verify(outboxRepository).save(failedEvent);
        assertThat(failedEvent.getStatus()).isEqualTo("FAILED");
    }

}
