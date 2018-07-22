package com.rdc.entity;

import java.util.ArrayList;

public class Blog {
    private int id;
    private String title;
    private String article;
    private String finishTime;
    private User user;
    private ArrayList<Category> category;
    private String coverImg;

    public Blog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Category> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<Category> category) {
        this.category = category;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", user=" + user +
                ", category=" + category +
                ", coverImg='" + coverImg + '\'' +
                '}';
    }
}
