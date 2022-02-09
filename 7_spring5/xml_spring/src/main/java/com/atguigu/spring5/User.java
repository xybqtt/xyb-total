package com.atguigu.spring5;

public class User {

    // 使用set方法注入
    private String username;

    // 使用构造器注入
    private int age;

    // 使用构造器注入
    private String sex;

    // 设置属性为null
    private String pwd;

    // 设置属性中包含特殊字符
    private String pwd2;

    // 注入对象
    private User son;

    // 通过内部bean注入对象
    private User son2;

    public User() {
    }

    public User(int age, String sex) {
        this.age = age;
        this.sex = sex;
    }

    public void setSon(User son) {
        this.son = son;
    }

    public void setSon2(User son2) {
        this.son2 = son2;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void add() {
        System.out.println("add()方法");
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", pwd='" + pwd + '\'' +
                ", pwd2='" + pwd2 + '\'' +
                ", son=" + son +
                ", son2=" + son2 +
                '}';
    }
}
