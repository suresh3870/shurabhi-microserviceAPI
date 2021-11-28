
package com.surabi.restaurants.consumer;


import com.surabi.restaurants.model.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
public class MessageListener {

    private KafkaConsumer<Integer, Message> consumer = null;
    private String topic="shurabhi-chat";

    public static final String KAFKA_SERVER_URL = "localhost";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final String CLIENT_ID = "Chat-consumer11";

    public MessageListener() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CLIENT_ID);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");  //earliest
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);

    }


    public List<Message> doWork() {
        consumer.subscribe(Collections.singletonList(topic));
        ConsumerRecords<Integer, Message> records = consumer.poll(Duration.ofMillis(1000));
        System.out.println("Number of message/s from records:->  "+records.count());
        List<Message> msgs= new ArrayList<>();
        for (ConsumerRecord<Integer, Message> record : records) {
            msgs.add(record.value());
           ////System.out.println(record.value().getTo());
            System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
            // System.out.println("Received message: " + record.value() + ") at offset " + record.offset());
        }
       return msgs;
    }

}