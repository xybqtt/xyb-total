package com.xyb;

import com.atguigu.spring5.a1ioc.xml.a1create.User;
import com.atguigu.spring5.a1ioc.xml.a2lifecycle.Orders;
import com.atguigu.spring5.a1ioc.xml.a3autowired.Emp;
import com.atguigu.spring5.a1ioc.xml.a4useproperties.UserConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class A1XmlTest {

    @Test
    public void testBeanCreate() throws InterruptedException {
        // 1、加载spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("a1ioc/xml/1beanCreate.xml");

        // 2、测试属性注入
        User user = context.getBean("user", User.class);

        System.out.println(user.toString());
        user.add();

        Thread.sleep(2000);

    }


    @Test
    public void testBeanCycle() throws InterruptedException {
        // 1、加载spring 配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("a1ioc/xml/2lifeCycle.xml");

        // 2、测试属性注入
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println(orders);
        context.close();

        // 3、测试自动装配
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);

    }


    @Test
    public void testAutowire() throws InterruptedException {
        // 1、加载spring 配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("a1ioc/xml/3autowired.xml");

        // 2、测试自动装配
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);

    }

    /**
     * 测试导入外部properties文件
     * @throws InterruptedException
     */
    @Test
    public void testImportOutProperties() throws InterruptedException {
        // 1、加载spring 配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("a1ioc/xml/4useproperties.xml");

        // 2、测试自动装配
        UserConfig userConfig = context.getBean("userConfig", UserConfig.class);
        System.out.println(userConfig);

    }

}
