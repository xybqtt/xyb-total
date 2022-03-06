package com.xyb;

import com.atguigu.spring5.a3sp5newfunc.SpringConfig;
import com.atguigu.spring5.a3sp5newfunc.User;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 使用junit4
//@RunWith(SpringJUnit4ClassRunner.class) // 指定单元测试框架版本
//@ContextConfiguration(classes = {SpringConfig.class}) // 相当于spring加载配置类，加载配置文件用locations = {"classpath:", ""}

//// 使用junit5方式1
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {SpringConfig.class}) // 相当于spring加载配置类，加载配置文件用locations = {"classpath:", ""}

// 使用junit5方式2，注意junit5 的@Test是org.junit.jupiter.api.Test;包下的
@SpringJUnitConfig(classes = {SpringConfig.class}) // 相当于整合了方式2的2个注解
public class A3Sp5NewFuncTest {

    @Autowired
    private SpringConfig springConfig;


    /**
     * 测试函数式注册bean，注意junit4、junit5导入的@Test包不一样
     */
    @Test
    public void testGenericApp() {
        User user = springConfig.registerBean();
        System.out.println("在函数式注册bean之后，查看bean是否存在：" + user);
    }

    /**
     * 测试函数式注册bean
     */
    @Test
    public void testJunit5() {
        User user1 = springConfig.registerBean();
        System.out.println("在函数式注册bean之后，查看bean是否存在：" + user1);
    }

}
