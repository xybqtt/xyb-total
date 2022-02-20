package com.xyb;

import com.atguigu.spring5.a3jdbcTemplate.anno.SpringConfig;
import com.atguigu.spring5.a3jdbcTemplate.anno.entity.User;
import com.atguigu.spring5.a3jdbcTemplate.anno.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class A3JdbcTemplateTest {

    @Test
    public void testBeanCreate() throws InterruptedException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService", UserService.class);

        userService.deleteAllUser();

        // 参数只有一条数据的curd操作。
        User user = new User();
        user.setUserId("1");
        user.setUserName("java");
        user.setUserStatus("a");
        userService.operaOneUser(user);

        // 参数只有多条数据的curd操作。
        List insList = new ArrayList<String[]>();
        insList.add(new String[]{"1", "java", "1"});
        insList.add(new String[]{"2", "c++", "1"});
        insList.add(new String[]{"3", "python", "1"});
        insList.add(new String[]{"4", "go", "1"});


        List updList = new ArrayList<String[]>();
        updList.add(new String[]{"c++1", "1", "2"});
        updList.add(new String[]{"python1", "1", "3"});

        List delList = new ArrayList<String[]>();
        delList.add(new String[]{"1"});
        delList.add(new String[]{"2"});

        userService.batchOperate(insList, updList, delList);
    }

}
