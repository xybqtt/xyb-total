package com.atguigu.spring5.a3sp5newfunc;

import org.springframework.lang.Nullable;

/**
 * 测试spring新增的功能：
 *     1、log4j2日志功能；
 *     2、@Nullable注解：对开发没什么用。
 *         注解用在方法上面，方法返回值可以为空。
 *         注解使用在方法参数里面，方法参数可以为空。
 *         注解使用在属性上面，属性值可以为空。
 *     3、函数式注册bean
 *     4、支持整合JUnit5
 */
public class User {

    private String username;

    private String sex;

    public User() {
    }

    public User(@Nullable String username, String sex) {
        this.username = username;
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                "username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
