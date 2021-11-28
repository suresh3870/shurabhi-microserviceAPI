package com.surabi.restaurants.controller;

import com.surabi.restaurants.constants.KafkaConstants;
import com.surabi.restaurants.consumer.MessageListener;
import com.surabi.restaurants.model.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static com.surabi.restaurants.constants.KafkaConstants.KAFKA_TOPIC;

@RestController
public class ChatController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    MessageListener exampleConsumer;

    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        try {
            System.out.println("Sending the message to kafka topic queue");
            kafkaTemplate.send(KAFKA_TOPIC, message).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/api/receive")
    public List<Message> receiveMessage() {
        return exampleConsumer.doWork();
    }

}
