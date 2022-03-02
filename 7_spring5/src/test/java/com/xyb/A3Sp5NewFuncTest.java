package com.xyb;

import com.atguigu.spring5.a3sp5newfunc.SpringConfig;
import com.atguigu.spring5.a3sp5newfunc.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class A3Sp5NewFuncTest {

    @Autowired
    private SpringConfig springConfig;

    /**
     * 测试函数式注册bean
     */
    @Test
    public void testGenericApp() {
        AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext(SpringConfig.class);
        try {
            User user = context1.getBean(User.class);
            System.out.println("在函数式注册bean之前，查看bean是否存在：" + user);
        } catch (Exception e) {
            System.out.println("在函数式注册bean之前，bean不存在。");
        }

        User user1 = context1.getBean(SpringConfig.class).registerBean();
        System.out.println("在函数式注册bean之后，查看bean是否存在：" + user1);
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
