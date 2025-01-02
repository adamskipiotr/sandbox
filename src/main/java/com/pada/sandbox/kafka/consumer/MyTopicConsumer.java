package com.pada.sandbox.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
public class MyTopicConsumer {

    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "myTopic", groupId = "kafka-sandbox")
    public void listen(ConsumerRecord<?, String> record) {
        System.out.println("Topic:" + record.topic() + ", Key:" + record.key() + ", Partition:" + record.partition() + ", Value:" + record.value());
        synchronized (messages) {
            messages.add(record.value());
        }
    }

    public List<String> getMessages() {
        return messages;
    }
}
