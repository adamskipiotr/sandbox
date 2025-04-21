package com.pada.sandbox.kafka.avroschemaregistry;

import com.pada.sandbox.avroschemaregistry.AvroSchemaRegistryMessage;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;


@EnableKafka
@Configuration
@ComponentScan(basePackages = "com.pada.sandbox.kafka.avroschemaregistry")
public class KafkaAvroSchemaRegistryConfiguration {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String SCHEMA_REGISTRY_URL = "http://localhost:8081";

    // PRODUCER

    @Bean
    public ProducerFactory<String, AvroSchemaRegistryMessage> avroSchemaRegistryProducerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        config.put("schema.registry.url", SCHEMA_REGISTRY_URL);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, AvroSchemaRegistryMessage> kafkaSchemaRegistryAvroTemplate() {
        return new KafkaTemplate<>(avroSchemaRegistryProducerFactory());
    }
}
