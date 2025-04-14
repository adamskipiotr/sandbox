package com.pada.sandbox.kafka.avroschemaregistry;

import com.pada.sandbox.avroschemaregistry.AvroSchemaRegistryMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaAvroSchemaRegistryProducer {

    private final KafkaTemplate<String, AvroSchemaRegistryMessage> kafkaTemplate;

    public KafkaAvroSchemaRegistryProducer(KafkaTemplate<String, AvroSchemaRegistryMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAvroSchemaRegistryMessage(String topic, AvroSchemaRegistryMessage message) {
        kafkaTemplate.send(topic, message.getName(), message);
    }
}