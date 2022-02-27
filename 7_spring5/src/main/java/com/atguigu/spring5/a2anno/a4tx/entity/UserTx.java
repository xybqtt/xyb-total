package com.atguigu.spring5.a2anno.a4tx.entity;

public class UserTx {

    private Integer userId;

    private String userName;

    private Integer money;

    public UserTx() {
    }

    public UserTx(Integer userId, String userName, Integer money) {
        this.userId = userId;
        this.userName = userName;
        this.money = money;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }


    @Override
    public String toString() {
        return "UserTx{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
