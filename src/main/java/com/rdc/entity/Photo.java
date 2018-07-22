package com.rdc.entity;

public class Photo {

    private int id;
    private String photoHash;

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

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", photoHash='" + photoHash + '\'' +
                '}';
    }
}
