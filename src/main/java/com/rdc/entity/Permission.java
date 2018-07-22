package com.rdc.entity;

import java.util.Date;

public class Permission {
    private int id;
    private int permissionType;
    private int pushStatus;
    private int loginStatus;
    private String pushTime;
    private String loginTime;

    public Permission() {
    }

    public int getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(int permissionType) {
        this.permissionType = permissionType;
    }

    public int getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(int pushStatus) {
        this.pushStatus = pushStatus;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionType=" + permissionType +
                ", pushStatus=" + pushStatus +
                ", loginStatus=" + loginStatus +
                ", pushTime='" + pushTime + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }
}
