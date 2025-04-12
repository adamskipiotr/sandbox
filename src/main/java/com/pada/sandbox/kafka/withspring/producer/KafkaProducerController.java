package com.pada.sandbox.kafka.withspring.producer;

import com.pada.sandbox.kafka.withspring.domain.Animal;
import com.pada.sandbox.kafka.withspring.domain.AnimalKind;
import com.pada.sandbox.kafka.withspring.domain.NewAnimal;
import jakarta.validation.constraints.NotBlank;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.pada.sandbox.kafka.withspring.domain.AnimalKind.ANIMAL;

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
        template.send("topicPartition", 2, "otherKey", message);
    }

    @GetMapping("kafka/produce-json")
    public ResponseEntity<String> produceJson(@RequestParam @NotBlank(message = "Kafka Topic can't be empty") String kafkaTopic,
                                              @RequestParam AnimalKind kind) {
        if(ANIMAL.equals(kind)){
            Animal animal = new Animal("Animal", "100", 4);
            ProducerRecord<String, Object> record = new ProducerRecord<>(kafkaTopic, animal);
            template.send(record);
        } else {
            NewAnimal animal = new NewAnimal("New Animal", 200, "2", "New Animal Comment", "newField");
            ProducerRecord<String, Object> record = new ProducerRecord<>(kafkaTopic, animal);
            template.send(record);
    }
        return ResponseEntity.ok().build();
    }

    @GetMapping("kafka/async-produce")
    public void asyncProduce(@RequestParam String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>("asyncTopic", message);
        //  template.send(record);
        //  producer.send(record, new ProducerCallback());
    }
}

class ProducerCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            e.printStackTrace();
        }
    }
}
