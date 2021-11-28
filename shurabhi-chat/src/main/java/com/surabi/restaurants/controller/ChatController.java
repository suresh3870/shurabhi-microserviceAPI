package com.surabi.restaurants.controller;

import com.surabi.restaurants.consumer.KafkaConsumerExample;
import com.surabi.restaurants.consumer.MessageListener;
import com.surabi.restaurants.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.surabi.restaurants.constants.KafkaConstants.KAFKA_TOPIC;

@RestController
@RequestMapping("/surabi/chat-controller")
public class ChatController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    MessageListener exampleConsumer;

    @Autowired
    KafkaConsumerExample kafkaConsumerExample;


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
    public List<Message> receiveMessage() throws InterruptedException {
        return kafkaConsumerExample.runConsumer();
    }

}
