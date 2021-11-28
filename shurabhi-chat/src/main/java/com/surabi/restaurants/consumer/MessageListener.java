
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
    public List<Message> doWork() {
        String topic = "shurabhi-chat";
        String KAFKA_SERVER_URL = "localhost";
        int KAFKA_SERVER_PORT = 9092;
        String CLIENT_ID = "Chat-consumer11";
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, CLIENT_ID);
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.setProperty(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");  //earliest
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<Integer, Message> consumer = new KafkaConsumer<Integer, Message>(props);
        consumer.subscribe(Collections.singletonList(topic));
        while (true) {
            ConsumerRecords<Integer, Message> records = consumer.poll(Duration.ofMillis(3000));
            System.out.println("Number of message/s from records:->  " + records.count());
            List<Message> msgs = new ArrayList<>();
            for (ConsumerRecord<Integer, Message> record : records) {
                msgs.add(record.value());
                ////System.out.println(record.value().getTo());
                System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
                // System.out.println("Received message: " + record.value() + ") at offset " + record.offset());
            }
            return msgs;
        }
    }

}