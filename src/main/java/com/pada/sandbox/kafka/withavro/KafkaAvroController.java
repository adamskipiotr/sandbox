package com.pada.sandbox.kafka.withavro;

import com.pada.sandbox.withavro.AvroMessage;
import com.pada.sandbox.withavro.AvroSecondMessage;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/avro")
public class KafkaAvroController {

    private final KafkaAvroProducer kafkaAvroProducer;

    public KafkaAvroController(KafkaAvroProducer kafkaAvroProducer) {
        this.kafkaAvroProducer = kafkaAvroProducer;
    }

    @GetMapping
    public void produceAvroMessage() {
        AvroMessage avroMessage = new AvroMessage(1, "Avro Message", 100);
        kafkaAvroProducer.sendAvroMessage("avro-message", avroMessage);
    }

    @GetMapping
    public void produceAvroSecondMessage() {
        AvroSecondMessage avroSecondMessage = new AvroSecondMessage(1, "Avro Second Message", 100);
        kafkaAvroProducer.sendAvroSecondMessage("avro-second-message", avroSecondMessage);
    }
}