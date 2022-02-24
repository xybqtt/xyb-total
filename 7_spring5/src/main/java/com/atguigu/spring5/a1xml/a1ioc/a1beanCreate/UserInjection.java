package com.atguigu.spring5.a1xml.a1ioc.a1beanCreate;

/**
 * 测试属性注入
 */
public class UserInjection {

    // 构造器注入
    private int userId;

    // 构造器注入
    private String userName;

    // set注入基本类型
    private String sex;

    // set注入对象
    private OtherInfo otherInfo;

    public UserInjection() {
    }

    public UserInjection(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
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

    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", otherInfo=" + otherInfo +
                '}';
    }
}
