package com.rdc.entity;

import java.util.ArrayList;

public class Album {
    private Integer id;
    private Integer userId;
    private String albumName;
    private String coverHash;
    private ArrayList<Comment> commentList;

    public Album() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getCoverHash() {
        return coverHash;
    }

    public void setCoverHash(String coverHash) {
        this.coverHash = coverHash;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", userId=" + userId +
                ", albumName='" + albumName + '\'' +
                ", coverHash='" + coverHash + '\'' +
                ", commentList=" + commentList +
                '}';
    }

}
