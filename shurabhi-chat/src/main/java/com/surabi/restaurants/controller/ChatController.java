package com.surabi.restaurants.controller;

import com.surabi.restaurants.consumer.KafkaConsumerExample;
import com.surabi.restaurants.model.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static com.surabi.restaurants.constants.KafkaConstants.KAFKA_TOPIC;

@RestController
@RequestMapping("/surabi/chat-controller")
public class ChatController {




    @Autowired
    KafkaConsumerExample kafkaConsumerExample;


    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        Properties kafkaProps = new Properties();
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.connect.json.JsonSerializer");

        try {
            kafkaProps.load(new FileReader("producer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        KafkaProducer<String,Message> producer = new KafkaProducer<>(kafkaProps);
        ProducerRecord<String, Message> record = new ProducerRecord<>("demo-topic", message);
        //message.setTimestamp(LocalDateTime.now().toString());
        try{
            producer.send(record);
            producer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/api/receive")
    public List<Message> receiveMessage() throws InterruptedException {
        return kafkaConsumerExample.runConsumer();
    }

}
