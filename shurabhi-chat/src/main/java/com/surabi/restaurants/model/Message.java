package com.surabi.restaurants.model;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;

import java.io.Serializable;

public class Message extends ProducerRecord<String, Message> {
    private String from;
    private String to;
    private String content;
    private String timestamp;


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }




    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Message(String topic, Integer partition, Long timestamp, String key, Message value, Iterable<Header> headers, String from, String to, String content, String timestamp1) {
        super(topic, partition, timestamp, key, value, headers);
        this.from = from;
        this.to = to;
        this.content = content;
        this.timestamp = timestamp1;
    }

    public Message(String topic, Integer partition, Long timestamp, String key, Message value, String from, String to, String content, String timestamp1) {
        super(topic, partition, timestamp, key, value);
        this.from = from;
        this.to = to;
        this.content = content;
        this.timestamp = timestamp1;
    }

    public Message(String topic, Integer partition, String key, Message value, Iterable<Header> headers, String from, String to, String content, String timestamp) {
        super(topic, partition, key, value, headers);
        this.from = from;
        this.to = to;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Message(String topic, Integer partition, String key, Message value, String from, String to, String content, String timestamp) {
        super(topic, partition, key, value);
        this.from = from;
        this.to = to;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Message(String topic, String key, Message value, String from, String to, String content, String timestamp) {
        super(topic, key, value);
        this.from = from;
        this.to = to;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Message(String topic, Message value, String from, String to, String content, String timestamp) {
        super(topic, value);
        this.from = from;
        this.to = to;
        this.content = content;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
