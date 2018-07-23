package com.rdc.entity;

import com.rdc.bean.UserBean;

import java.util.ArrayList;

public class Comment {
    private int id;
    private String comments;
    private String time;
    private UserBean fromUserBean;
    private int fromId;
    private ArrayList<Reply> replies;
    private int upCount;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public UserBean getFromUserBean() {
        return fromUserBean;
    }

    public void setFromUserBean(UserBean fromUserBean) {
        this.fromUserBean = fromUserBean;
    }

    public ArrayList<Reply> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Reply> replies) {
        this.replies = replies;
    }

    public int getUpCount() {
        return upCount;
    }

    public void setUpCount(int upCount) {
        this.upCount = upCount;
    }
}
