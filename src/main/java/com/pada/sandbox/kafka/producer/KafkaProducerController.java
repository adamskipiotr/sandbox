package com.pada.sandbox.kafka.producer;

import com.pada.sandbox.kafka.domain.NewAnimal;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.kafka.clients.producer.Callback;

@RestController
public class KafkaProducerController {

    @Autowired
    private KafkaTemplate<String, Object> template;

//    @Autowired
//    private KafkaProducer<String, String> producer;  TODO


    @GetMapping("kafka/produce")
    public void produce(@RequestParam String message) {
        template.send("basicTopic", message);
        template.send("myTopic", "myKey", message);
        template.send("topicPartition", 2,"otherKey", message);
    }

    @GetMapping("kafka/produce-json")
    public void produceJson(@RequestParam String message) {
        NewAnimal animal = new NewAnimal("Summer", 123,"4", "comment");
        ProducerRecord<String, Object> record = new ProducerRecord<>("jsonTopic", animal);
        template.send(record);
    }

    @GetMapping("kafka/async-produce")
    public void asyncProduce(@RequestParam String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>("asyncTopic", message);
   //     template.send(record);
    //  producer.send(record, new ProducerCallback());
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
