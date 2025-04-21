package com.pada.sandbox.kafka.withavro;

import com.pada.sandbox.withavro.AvroMessage;
import com.pada.sandbox.withavro.AvroSecondMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaAvroProducer {

    private final KafkaTemplate<String, AvroMessage> kafkaTemplate;

    private final KafkaTemplate<String, AvroSecondMessage> kafkaSecondTemplate;


    public KafkaAvroProducer(KafkaTemplate<String, AvroMessage> kafkaTemplate, KafkaTemplate<String, AvroSecondMessage> kafkaSecondTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaSecondTemplate = kafkaSecondTemplate;
    }

    public void sendAvroMessage(String topic, AvroMessage message) {
        kafkaTemplate.send(topic, message.getName(), message);
    }

    public void sendAvroSecondMessage(String topic, AvroSecondMessage message) {
        kafkaSecondTemplate.send(topic, message.getSecondName(), message);
    }
}