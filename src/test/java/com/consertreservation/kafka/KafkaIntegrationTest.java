package com.consertreservation.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"test-topic"})
class KafkaIntegrationTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Test
    @DisplayName("카프카 정상 작동 테스트_Producer와 Consumer 간 메시지 일치 확인")
    void testKafkaMessageSendAndReceive() {
        // 1. Producer 설정
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafkaBroker);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(producerProps);

        // 2. 메시지 보내기
        String testMessage = "Hello, Kafka!";
        producer.send(new ProducerRecord<>("test-topic", "key", testMessage));
        producer.flush();

        // 3. Consumer 설정
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(
                "test-group", "true", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        Consumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singletonList("test-topic"));

        // 4. 메시지 수신 및 검증
        var records = KafkaTestUtils.getRecords(consumer);
        assertThat(records.count()).isGreaterThan(0);
        assertThat(records.iterator().next().value()).isEqualTo(testMessage);

        consumer.close();
        producer.close();
    }
}


