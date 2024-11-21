package com.consertreservation.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "${spring.kafka.topic.concert-events}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(String message) {
        System.out.println("Consumed message: " + message);
    }
}
