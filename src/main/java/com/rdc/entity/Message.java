package com.rdc.entity;

public class Message {
    private int id;
    private String time;
    private String content;
    private User fromUser;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", fromUser=" + fromUser +
                '}';
    }
}
