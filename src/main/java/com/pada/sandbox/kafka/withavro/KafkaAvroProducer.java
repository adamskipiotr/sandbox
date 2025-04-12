package com.pada.sandbox.kafka.withavro;

import com.pada.sandbox.withavro.AvroMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaAvroProducer {

    private final KafkaTemplate<String, AvroMessage> kafkaTemplate;

    public KafkaAvroProducer(KafkaTemplate<String, AvroMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAvroMessage(String topic, AvroMessage message) {
        kafkaTemplate.send(topic, message.getName(), message);
    }
}