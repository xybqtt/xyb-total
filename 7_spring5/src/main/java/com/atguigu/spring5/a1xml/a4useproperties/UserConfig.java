package com.atguigu.spring5.a1xml.a4useproperties;

public class UserConfig {

    private String name;

    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserConfig{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
