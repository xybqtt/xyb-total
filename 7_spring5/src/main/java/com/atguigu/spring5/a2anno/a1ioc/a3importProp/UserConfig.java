package com.atguigu.spring5.a2anno.a1ioc.a3importProp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "userConfig")
public class UserConfig {

    @Value(value = "${userconfig.name}")
    private String name;

    @Value(value = "${user.password}")
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
