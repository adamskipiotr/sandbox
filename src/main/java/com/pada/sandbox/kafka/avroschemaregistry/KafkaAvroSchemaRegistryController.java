package com.pada.sandbox.kafka.avroschemaregistry;

import com.pada.sandbox.avroschemaregistry.AvroSchemaRegistryMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/schema-registry")
public class KafkaAvroSchemaRegistryController {

    private final KafkaAvroSchemaRegistryProducer kafkaAvroSchemaRegistryProducer;
    private final KafkaTemplate<String, AvroSchemaRegistryMessage> kafkaTemplate;

    public KafkaAvroSchemaRegistryController(KafkaAvroSchemaRegistryProducer kafkaAvroSchemaRegistryProducer,
                                             KafkaTemplate<String, AvroSchemaRegistryMessage> kafkaTemplate) {
        this.kafkaAvroSchemaRegistryProducer = kafkaAvroSchemaRegistryProducer;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping
    public void produce() {
        AvroSchemaRegistryMessage avroSchemaRegistryMessage = new AvroSchemaRegistryMessage(1, "Avro Message", 100);
        final var producerRecord = new ProducerRecord<>("avro-schema-registry-message", "key", avroSchemaRegistryMessage);
        try {
            final var sendResult = kafkaTemplate.send(producerRecord);
            kafkaTemplate.flush();
            sendResult.get();
            System.out.println("Send message");
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Sending interrupted:" + e.getMessage());
        } catch (final KafkaException | ExecutionException e) {
            System.out.println("There was error while synchronous send event to Kafka cluster:" + e.getMessage());
        }
        kafkaAvroSchemaRegistryProducer.sendAvroSchemaRegistryMessage("string", avroSchemaRegistryMessage
        );
    }
}