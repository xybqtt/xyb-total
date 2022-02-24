package com.atguigu.spring5.a1xml.a4tx.entity;

import com.atguigu.spring5.a1xml.a1ioc.a1beanCreate.OtherInfo;

public class UserTx {

    private int userId;

    private String userName;

    private int money;

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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    @Override
    public String toString() {
        return "UserTx{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", money=" + money +
                '}';
    }
}
