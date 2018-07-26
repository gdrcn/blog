package com.rdc.bean;

public class UserBean {

	private int id;
	private String username;
	private String face;

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

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", face='" + face + '\'' +
                '}';
    }
}
