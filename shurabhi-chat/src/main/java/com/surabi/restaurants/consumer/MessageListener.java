
package com.surabi.restaurants.consumer;


import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.surabi.restaurants.model.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

@Component
public class MessageListener {
    public List<Message> doWork() {
        String bootstrapServers="localhost:9092";
        String grp_id="consumer10";
        String topic="shurabhi-chat";
        Properties properties=new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,   "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,grp_id);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        //creating consumer
        KafkaConsumer<Integer,Message> consumer= new KafkaConsumer<Integer,Message>(properties);
        //Subscribing
        consumer.subscribe(Arrays.asList(topic));
        //polling

        ConsumerRecords<Integer,Message> records=consumer.poll(Duration.ofMillis(30000));
        List<Message> msgs=new ArrayList<>();
        for(ConsumerRecord<Integer,Message> record: records){
                msgs.add(record.value());
                System.out.println("Key: "+ record.key() + ", Value:" +record.value());
                System.out.println("Partition:" + record.partition()+",Offset:"+record.offset());
            }
        //consumer.close();
        return msgs;
    }

}