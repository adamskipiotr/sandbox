package com.pada.sandbox.kafka.withavro;

import com.pada.sandbox.withavro.AvroMessage;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/avro")
public class KafkaAvroController {

    private final KafkaAvroProducer kafkaAvroProducer;

    public KafkaAvroController(KafkaAvroProducer kafkaAvroProducer) {
        this.kafkaAvroProducer = kafkaAvroProducer;
    }

    @GetMapping
    public void produce() {
        AvroMessage avroMessage = new AvroMessage(1, "Avro Message", 100);
        kafkaAvroProducer.sendAvroMessage("avro-message", avroMessage);
    }
}