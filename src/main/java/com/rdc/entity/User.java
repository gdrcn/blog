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
    private ArrayList<User> niceFriendsList;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getNewFans() {
        return newFans;
    }

    public void setNewFans(Integer newFans) {
        this.newFans = newFans;
    }

    public Integer getIdols() {
        return idols;
    }

    public void setIdols(Integer idols) {
        this.idols = idols;
    }

    public Integer getNotReadComment() {
        return notReadComment;
    }

    public void setNotReadComment(Integer notReadComment) {
        this.notReadComment = notReadComment;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
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

    public ArrayList<User> getNiceFriendsList() {
        return niceFriendsList;
    }

    public void setNiceFriendsList(ArrayList<User> niceFriendsList) {
        this.niceFriendsList = niceFriendsList;
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
                ", fans=" + fans +
                ", newFans=" + newFans +
                ", idols=" + idols +
                ", notReadComment=" + notReadComment +
                ", permission=" + permission +
                ", albumList=" + albumList +
                ", blogList=" + blogList +
                ", niceFriendsList=" + niceFriendsList +
                '}';
    }
}
