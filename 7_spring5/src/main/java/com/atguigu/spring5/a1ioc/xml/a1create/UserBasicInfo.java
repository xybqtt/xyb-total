package com.atguigu.spring5.a1ioc.xml.a1create;

/**
 * 构造器注入
 */
public class UserBasicInfo {

    private String userName;

    private String sex;

    public UserBasicInfo(String userName, String sex) {
        userName = userName;
        sex = sex;
    }

    @Override
    public String toString() {
        return "UserBasicInfo{" +
                "userName='" + userName + '\'' + "\n" +
                ", sex='" + sex + '\'' + "\n" +
                '}';
    }
}
