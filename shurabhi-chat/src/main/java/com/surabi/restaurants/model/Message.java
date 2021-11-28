package com.shubh.kafkachat.model;

public class Message {
    private String from;
    private String to;
    private String content;
    private String timestamp;

    public Message() {
    }

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

    public Message(String from, String to, String content, String timestamp) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.timestamp = timestamp;
    }
}
