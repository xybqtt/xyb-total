package com.atguigu.spring5.a2anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

// 使用注解，
@Service(value = "userService") // 可以只写@Service
public class UserService {

    // 通过类型注入，不过当一个接口有多个实现类时，不确定找的哪一个会报错
    @Autowired
    private User user;

    // 通过bean id注入
    @Autowired
    @Qualifier(value = "userDaoImpl1")
    private UserDao userDao;

    // 通过类型注入
    @Resource
    private User user2;

    // 通过bean id注入
    @Resource(name = "userDaoImpl2")
    private UserDao userDao2;

    // 普通类型注入
    @Value(value = "张三")
    private String name;

    public void add() {
        System.out.println("userService.add()...");
    }

    @Override
    public String toString() {
        return "UserService{" +
                "user=" + user +
                ", userDao=" + userDao +
                ", user2=" + user2 +
                ", userDao2=" + userDao2 +
                ", name='" + name + '\'' +
                '}';
    }
}
