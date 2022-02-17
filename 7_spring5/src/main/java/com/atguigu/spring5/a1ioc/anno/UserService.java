package com.atguigu.spring5.a1ioc.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;

// 使用注解，
@Service(value = "userService") // 可以只写@Service
public class UserService {


    // 通过类型注入，不过当一个接口有多个实现类时，不确定找的哪一个会报错
    @Autowired
    private User user;

    // 注入非自定义的类，即spring或其它jar已经写好的类
    @Autowired
    private DataSource dataSource;

    // 通过@Bean注入非自定义的类，通过bean id注入
    @Autowired
    @Qualifier(value = "dataSource")
    private DataSource dataSourceByName;

    // 通过类型注入
    @Resource
    private User user2;

    // 通过bean id注入
    @Resource(name = "userDaoImpl1")
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
                ", dataSource=" + dataSource +
                ", dataSourceByName=" + dataSourceByName +
                ", user2=" + user2 +
                ", userDao2=" + userDao2 +
                ", name='" + name + '\'' +
                '}';
    }
}
