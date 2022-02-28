package com.xyb;

import com.atguigu.spring5.a2anno.a3jdbcTemplate.entity.User;
import com.atguigu.spring5.a2anno.a3jdbcTemplate.service.UserService;
import com.atguigu.spring5.a2anno.a1ioc.a1beanCreate.UserInjection;
import com.atguigu.spring5.a2anno.a1ioc.a2lifecycle.Orders;
import com.atguigu.spring5.a2anno.a1ioc.a3importProp.UserConfig;
import com.atguigu.spring5.a2anno.a2aop.UserCls;
import com.atguigu.spring5.a2anno.SpringConfig;
import com.atguigu.spring5.a2anno.a4tx.entity.UserTx;
import com.atguigu.spring5.a2anno.a4tx.service.UserTxService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A2AnnoTest {


    /**
     * 查看属性的注入
     * @throws InterruptedException
     */
    @Test
    public void testBeanCreate() throws InterruptedException {
        // 1、加载spring配置类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

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
        // 1、加载spring配置类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // 2、测试生命周期
        Orders orders = context.getBean("orders", Orders.class);

        orders.user();

        context.close();
    }

    /**
     * 查看导入properties文件
     * @throws InterruptedException
     */
    @Test
    public void testImportProps() throws InterruptedException {
        // 1、加载spring配置类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

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
        // 1、加载spring配置类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // 2、测试读取并赋值properties文件数据
        UserCls userCls = context.getBean("userCls", UserCls.class);

        userCls.method();

        context.close();
    }

    /**
     * 测试jdbcTemplate
     * @throws InterruptedException
     */
    @Test
    public void testJdbcTemplate() throws InterruptedException {
        // 1、加载spring 配置文件
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

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

    /**
     * 测试调用方法无事务，被调用方法的各种事务传播行为
     * @throws InterruptedException
     */
    @Test
    public void testTx() throws InterruptedException {
        // 1、加载spring 配置文件
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserTxService service = context.getBean("userTxServiceImpl", UserTxService.class);
        service.deleteAllUser();
        service.insertData(prepareData());
        System.out.println("显示测试前数据：");
        service.showAllUserTx();


        // 4、测试service类无事务情况下，Dao层的各个传播行为
        System.out.println();
        System.out.println("开始修改数据");
        service.showTx(prepareData(), 100, "tx"); // 有事务
//        service.showTx(prepareData(), 100, "nottx"); // 无事务
        System.out.println("修改数据结束");
        System.out.println();


        // 6、查询所有数据，并显示
        System.out.println("显示测试后数据：");
        service.showAllUserTx();


        context.close();
    }

    /**
     * 测试事务隔离级别
     * @throws InterruptedException
     */
    @Test
    public void testIsolation() throws Exception {
        // 1、加载spring 配置文件
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserTxService service = context.getBean("userTxServiceImpl", UserTxService.class);
        service.deleteAllUser();

        UserTx userTx = new UserTx(1, "java", 0);
        service.insertOneData(userTx);
        System.out.println("显示测试前数据：");
        service.showAllUserTx();


        // 4、测试4个事务隔离级别，所能解决的问题
        service.showisolation(userTx, 3, "1"); // 演示“读未提交”
//        service.showisolation(userTx, 3, "2"); // 演示“读已提交”
//        service.showisolation(userTx, 3, "3"); // 演示“可重读”
//        service.showisolation(userTx, 3, "4"); // 演示“可串行化”

        // 6、查询所有数据，并显示
        System.out.println("显示测试后数据：");
        service.showAllUserTx();


        context.close();
    }


    private Map<String, List<UserTx>> prepareData() {
        Map<String, List<UserTx>> map = new HashMap<>();

        List<UserTx> list = new ArrayList<>();
        list.add(new UserTx(0, "begindata", 0));
        map.put("begindata", list);

        list = new ArrayList<>();
        list.add(new UserTx(100, "enddata", 0));
        map.put("enddata", list);

        list = new ArrayList<>();
        list.add(new UserTx(1, "never", 0));
        map.put("never", list);

        list = new ArrayList<>();
        list.add(new UserTx(20, "notsupport", 0));
        map.put("notsupport", list);

        list = new ArrayList<>();
        list.add(new UserTx(30, "support", 0));
        map.put("support", list);

        list = new ArrayList<>();
        list.add(new UserTx(40, "mandatory", 0));
        map.put("mandatory", list);

        list = new ArrayList<>();
        list.add(new UserTx(50, "requiresnew", 0));
        map.put("requiresnew", list);

        list = new ArrayList<>();
        list.add(new UserTx(60, "requires", 0));
        map.put("requires", list);

        list = new ArrayList<>();
        list.add(new UserTx(70, "nested", 0));
        map.put("nested", list);

        return map;
    }

}
















