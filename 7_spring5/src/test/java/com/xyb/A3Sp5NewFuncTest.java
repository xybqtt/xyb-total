package com.xyb;

import com.atguigu.spring5.a3sp5newfunc.SpringConfig;
import com.atguigu.spring5.a3sp5newfunc.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class}, locations = {""})
public class A3Sp5NewFuncTest {

    @Autowired
    private SpringConfig springConfig;


    /**
     * 测试函数式注册bean
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
