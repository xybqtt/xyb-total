package com.atguigu.spring5.a1create;

/**
 * 构造器注入
 */
public class UserBasicInfo {

    private String userName;

    private String sex;

    public UserBasicInfo(String userName, String sex) {
        this.userName = userName;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserBasicInfo{" +
                "userName='" + userName + '\'' + "\n" +
                ", sex='" + sex + '\'' + "\n" +
                '}';
    }
}
