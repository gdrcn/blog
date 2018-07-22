package com.rdc.entity;

import java.util.ArrayList;

public class User {

    private int id;
    private String username;
    private String password;
    private Integer age;
    private String phone;
    private String email;
    private String realname;
    private String signature;
    private String faceImg;
    private String address;
    private int visible;
    private Integer fans;
    private Integer newFans;
    private Integer idols;
    private Integer notReadComment;
    private Permission permission;
    private ArrayList<Album> albumList;
    private ArrayList<Blog> blogList;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public ArrayList<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(ArrayList<Album> albumList) {
        this.albumList = albumList;
    }

    public ArrayList<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(ArrayList<Blog> blogList) {
        this.blogList = blogList;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getNewFans() {
        return newFans;
    }

    public void setNewFans(int newFans) {
        this.newFans = newFans;
    }

    public int getIdols() {
        return idols;
    }

    public void setIdols(int idols) {
        this.idols = idols;
    }

    public int getNotReadComment() {
        return notReadComment;
    }

    public void setNotReadComment(int notReadComment) {
        this.notReadComment = notReadComment;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", realname='" + realname + '\'' +
                ", signature='" + signature + '\'' +
                ", faceImg='" + faceImg + '\'' +
                ", address='" + address + '\'' +
                ", visible=" + visible +
                ", permission=" + permission +
                ", albumList=" + albumList +
                ", blogList=" + blogList +
                ", fans=" + fans +
                ", newFans=" + newFans +
                ", idols=" + idols +
                ", notReadComment=" + notReadComment +
                '}';
    }
}
