package com.xyb;

import com.atguigu.spring5.a4tx.SpringConfig;
import com.atguigu.spring5.a4tx.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.TransactionManager;

/**
 * 事务测试
 */
public class A4TxTest {

    @Test
    public void testTx() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService userService = context.getBean("userService", UserService.class);

        userService.accountMoney();

    }

}
