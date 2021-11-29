package com.surabi.restaurants.consumer;

import com.surabi.restaurants.model.Message;
import com.surabi.restaurants.serviceimpl.UserLoggedDetailsImpl;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
public class KafkaConsumerExample {
    public Consumer<Integer, Message> createConsumer() {
        Properties props = new Properties();
        String bootstrapServers="localhost:9092";
        String topic="shurabhi-chat";
        System.out.println(UserLoggedDetailsImpl.getUserRole().toString());
        if (UserLoggedDetailsImpl.getUserRole().equals("[ADMIN]")){
            props.put(ConsumerConfig.GROUP_ID_CONFIG,
                    "ADMIN");}
        else
        {
            String group_id= UserLoggedDetailsImpl.getUserName();
            props.put(ConsumerConfig.GROUP_ID_CONFIG,
                    group_id);
        }
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.surabi.restaurants.model");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        // Create the consumer using props.
        //KafkaConsumer<Integer, Message> consumer = new KafkaConsumer<>(props,
              //  new IntegerDeserializer(), new KryoPOJODeserializer(Message.class));
         Consumer<Integer, Message> consumer = new KafkaConsumer<>(props,new IntegerDeserializer(), new JsonDeserializer<>(Message.class, false));

        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(topic));
        return consumer;
    }
     public List<Message> runConsumer() throws InterruptedException {
         Consumer<Integer, Message> consumer = createConsumer();

        final int giveUp = 2;   int noRecordsCount = 0;
         List<Message> msgs=new ArrayList<>();

        while (true) {
            final ConsumerRecords<Integer, Message> consumerRecords =
                    consumer.poll(1000);

            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            for(ConsumerRecord<Integer,Message> record: consumerRecords){
                //System.out.println(record.value().getTo());

               if ((record.value().getTo().equals(UserLoggedDetailsImpl.getUserName())) ||
                       ((record.value().getTo().equals("ADMIN")) && (UserLoggedDetailsImpl.getUserRole().equals("[ADMIN]"))) ) {
                    msgs.add(record.value());
                    System.out.println("Key: " + record.key() + ", Value:" + record.value());
                    System.out.println("Partition:" + record.partition() + ",Offset:" + record.offset());
               }
            }

            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("DONE");
        return msgs;
    }
}
