package com.consertreservation.kafka.controller;

import com.consertreservation.kafka.producer.KafkaProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducerService producerService;

    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/api/kafka/publish")
    public String publishMessage(@RequestParam String message) {
        producerService.sendMessage(message);
        return "Message sent to Kafka topic!";
    }
}
