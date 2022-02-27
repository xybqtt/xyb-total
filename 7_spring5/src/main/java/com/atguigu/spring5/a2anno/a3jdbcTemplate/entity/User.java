package com.atguigu.spring5.a2anno.a3jdbcTemplate.entity;

import com.atguigu.spring5.a1xml.a1ioc.a1beanCreate.OtherInfo;

public class User {

    private int userId;

    private String userName;

    private String sex;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
