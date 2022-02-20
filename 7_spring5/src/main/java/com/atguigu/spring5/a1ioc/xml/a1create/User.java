package com.atguigu.spring5.a1ioc.xml.a1create;

public class User {

    private UserBasicInfo userBasicInfo;

    private UserBasicInfo2 userBasicInfo2;

    private UserCollectionDI userCollectionDI;

    private User userSon;

    public User() {
    }

    public void setUserSon(User userSon) {
        userSon = userSon;
    }

    public void setUserBasicInfo(UserBasicInfo userBasicInfo) {
        userBasicInfo = userBasicInfo;
    }

    public void setUserBasicInfo2(UserBasicInfo2 userBasicInfo2) {
        userBasicInfo2 = userBasicInfo2;
    }

    public void setUserCollectionDI(UserCollectionDI userCollectionDI) {
        userCollectionDI = userCollectionDI;
    }

    public void add() {
        System.out.println("add....");
    }

    @Override
    public String toString() {
        return "User{" +
                "userBasicInfo=" + userBasicInfo + "\n" +
                ", userBasicInfo2=" + userBasicInfo2 + "\n" +
                ", userCollectionDI=" + userCollectionDI + "\n" +
                ", userSon=" + userSon + "\n" +
                '}';
    }
}
