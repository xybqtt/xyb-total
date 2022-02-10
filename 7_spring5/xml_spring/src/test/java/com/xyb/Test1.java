package com.xyb;

import com.atguigu.spring5.a1create.User;
import com.atguigu.spring5.a2lifecycle.Orders;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {

    @Test
    public void testBeanCreate() throws InterruptedException {
        // 1、加载spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("1beanCreate.xml");

        // 2、测试属性注入
        User user = context.getBean("user", User.class);

        System.out.println(user.toString());
        user.add();

        Thread.sleep(2000);

    }


    @Test
    public void testBeanCycle() throws InterruptedException {
        // 1、加载spring 配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("2lifeCycle.xml");

        // 2、测试属性注入
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println(orders);
        context.close();

    }

}
