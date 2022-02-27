package com.atguigu.spring5.a2anno.a1ioc.a1beanCreate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 测试属性注入，使用注解注入，不需要get、set方法
 */
@Component(value = "userInjection")
@Lazy(value = true) // 懒加载
public class UserInjection {

    private String sexField;

    @Value(value = "1") // 设置字段值
    private int userId;

    @Value(value = "${user.name}") // $，从properties文件中取值
    private String userName;

    @Value(value = "#{otherInfo.sexField}") // #，从beanId为otherInfo的对象中取字段为sexField的值
    private String sex;

    /*
        自动注入：默认是byType
        配合@Qualifier(value = "a")是byName。
        @Resource：自动byType注入
        Resource(name = "abc")：byName注入
     */
    @Autowired
    private OtherInfo otherInfo;

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
