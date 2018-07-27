package com.rdc.entity;

import java.util.ArrayList;

public class Photo {

    private int id;
    private Integer albumId;
    private String photoHash;
    private String  pushTime;
    private ArrayList<Comment> commentArrayList;
    private Integer beUpNum;
    private Integer commentsNum;

    public Photo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoHash() {
        return photoHash;
    }

    public void setPhotoHash(String photoHash) {
        this.photoHash = photoHash;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    public ArrayList<Comment> getCommentArrayList() {
        return commentArrayList;
    }

    public void setCommentArrayList(ArrayList<Comment> commentArrayList) {
        this.commentArrayList = commentArrayList;
    }

    public Integer getBeUpNum() {
        return beUpNum;
    }

    public void setBeUpNum(Integer beUpNum) {
        this.beUpNum = beUpNum;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", photoHash='" + photoHash + '\'' +
                ", pushTime='" + pushTime + '\'' +
                ", commentArrayList=" + commentArrayList +
                ", beUpNum=" + beUpNum +
                ", commentsNum=" + commentsNum +
                '}';
    }
}
