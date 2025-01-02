package com.pada.sandbox.kafka.controller;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.kafka.clients.producer.Callback;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> template;

    @Autowired
    private KafkaProducer<String, String> producer;


    @GetMapping("kafka/produce")
    public void produce(@RequestParam String message) {
        template.send("basicTopic", message);
        template.send("myTopic", "myKey", message);
        template.send("topicPartition", 2,"otherKey", message);
    }

    @GetMapping("kafka/async-produce")
    public void asyncProduce(@RequestParam String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>("asyncTopic", message);
        template.send(record);
        producer.send(record, new ProducerCallback());
    }
}

class ProducerCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if(e != null){
            e.printStackTrace();
        }
    }
}
