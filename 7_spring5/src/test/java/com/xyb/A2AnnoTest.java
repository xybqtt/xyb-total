package com.xyb;

import com.atguigu.spring5.a1xml.a3autowired.Emp;
import com.atguigu.spring5.a2anno.SpringConfig;
import com.atguigu.spring5.a2anno.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class A2AnnoTest {

    /**
     * 使用注解进行注入
     */
    @Test
    public void testAnno() {
        // 1、加载spring 配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("a2anno/1annoVersion.xml");

        // 2、测试自动装配
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();

        System.out.println(userService);
    }

    /**
     * 使用配置类替代配置文件的开发，即不需要xml配置文件了。
     */
    @Test
    public void testConfigCls() {
        // 1、加载spring 配置文件
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // 2、测试自动装配
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();

        System.out.println(userService);
    }

}
