package com.consertreservation.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

//테스트 환경에서 Kafka 브로커를 임베디드로 실행할 수 있도록 설정
@Configuration
public class EmbeddedKafkaConfig {

    @Bean
    public EmbeddedKafkaBroker embeddedKafkaBroker() {
        return new EmbeddedKafkaBroker(1, true, 1, "test-topic")
                .brokerProperty("listeners", "PLAINTEXT://localhost:9092")
                .brokerProperty("port", "9092");
    }
}
