package com.rdc.bean;

public class UserBean {

	private int id;
	private String username;
	private String face;
    private String background;
    private String signature;
    private Integer beUpStatus;

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

    public Integer getBeUpStatus() {
        return beUpStatus;
    }

    public void setBeUpStatus(Integer beUpStatus) {
        this.beUpStatus = beUpStatus;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", face='" + face + '\'' +
                ", background='" + background + '\'' +
                ", signature='" + signature + '\'' +
                ", beUpStatus=" + beUpStatus +
                '}';
    }
}
