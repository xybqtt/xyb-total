package com.xyb;

import com.atguigu.spring5.a3aop.anno.SpringConfig;
import com.atguigu.spring5.a3aop.anno.User;
import com.atguigu.spring5.a3aop.xml.User1;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class A3AopTest {

    /**
     * 使用注解进行测试Aop
     */
    @Test
    public void testAnno() {
        // 1、加载spring 配置文件
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // 2、测试自动装配
        User user = context.getBean("user", User.class);
        int a = user.div(5, 4);
        System.out.println(a);

        System.out.println("-----------------------------------------------");
        System.out.println(user.div(5, 0));
    }

    /**
     * 使用xml进行测试Aop
     */
    @Test
    public void testXml() {
        // 1、加载spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("a3aop/1aop.xml");

        // 2、测试自动装配
        User1 user = context.getBean("user1", User1.class);
        int a = user.div(5, 4);
        System.out.println(a);

        System.out.println("-----------------------------------------------");
        System.out.println(user.div(5, 0));
    }

}
