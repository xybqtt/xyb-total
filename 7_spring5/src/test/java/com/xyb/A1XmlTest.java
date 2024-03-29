package com.xyb;

import com.atguigu.spring5.a1xml.a1ioc.a1beanCreate.UserInjection;
import com.atguigu.spring5.a1xml.a1ioc.a2lifecycle.Orders;
import com.atguigu.spring5.a1xml.a1ioc.a3importProp.UserConfig;
import com.atguigu.spring5.a1xml.a2aop.UserCls;
import com.atguigu.spring5.a1xml.a3jdbcTemplate.entity.User;
import com.atguigu.spring5.a1xml.a3jdbcTemplate.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class A1XmlTest {

    /**
     * 查看属性的注入
     * @throws InterruptedException
     */
    @Test
    public void testBeanCreate() throws InterruptedException {
        // 1、加载spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:a1xml/0total.xml");

        // 2、测试属性注入
        UserInjection userInjection = context.getBean("userInjection", UserInjection.class);

        System.out.println(userInjection.toString());


    }

    /**
     * 查看bean的生命周期
     * @throws InterruptedException
     */
    @Test
    public void testLifeCycle() throws InterruptedException {
        // 1、加载spring 配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:a1xml/0total.xml");

        // 2、测试属性注入
        Orders orders = context.getBean("orders", Orders.class);

        System.out.println(orders.toString());;

        context.close();
    }

    /**
     * 查看导入properties文件
     * @throws InterruptedException
     */
    @Test
    public void testImportProps() throws InterruptedException {
        // 1、加载spring 配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:a1xml/0total.xml");

        // 2、测试读取并赋值properties文件数据
        UserConfig userConfig = context.getBean("userConfig", UserConfig.class);

        System.out.println(userConfig.toString());;

        context.close();
    }

    /**
     * 测试aop
     * @throws InterruptedException
     */
    @Test
    public void testAop() throws InterruptedException {
        // 1、加载spring 配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:a1xml/0total.xml");

        // 2、测试读取并赋值properties文件数据
        UserCls userService = context.getBean("userCls", UserCls.class);

        userService.run();

        context.close();
    }

    /**
     * 测试jdbcTemplate
     * @throws InterruptedException
     */
    @Test
    public void testJdbcTemplate() throws InterruptedException {
        // 1、加载spring 配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:a1xml/0total.xml");

        // 2、测试读取并赋值properties文件数据
        UserService userService = context.getBean("userServiceImpl", UserService.class);

        userService.deleteAllUser();

        // 参数只有一条数据的curd操作。
        User user = new User();
        user.setUserId(1);
        user.setUserName("java");
        user.setSex("0");
        userService.operaOneUser(user);

        // 参数只有多条数据的curd操作。
        List insList = new ArrayList<String[]>();
        insList.add(new Object[]{1, "java", "1"});
        insList.add(new Object[]{2, "c++", "1"});
        insList.add(new Object[]{3, "python", "1"});
        insList.add(new Object[]{4, "go", "1"});

        List updList = new ArrayList<String[]>();
        updList.add(new Object[]{"c++1", "1", 2});
        updList.add(new Object[]{"python1", "1", 3});

        List delList = new ArrayList<String[]>();
        delList.add(new Integer[]{1});
        delList.add(new Integer[]{2});

        userService.batchOperate(insList, updList, delList);

        context.close();
    }


}
