package com.rdc.entity;

public class Comment {
    private int id;
    private int fromUserId;
    private String comments;
    private String time;
    private User user;
    private int status;
    private int from_id;


    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", comments='" + comments + '\'' +
                ", time='" + time + '\'' +
                ", user=" + user +
                ", status=" + status +
                ", from_id=" + from_id +
                '}';
    }
}
