package com.atguigu.spring5.a3sp5newfunc;

import com.atguigu.spring5.a2anno.a1ioc.IocConfig;
import com.atguigu.spring5.a2anno.a2aop.AopConfig;
import com.atguigu.spring5.a2anno.a3jdbcTemplate.JdbcTemplateConfig;
import com.atguigu.spring5.a2anno.a4tx.JdbcTxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 使用注解代替xml文件
 */

@Configuration // 相当于 xml文件中的配置文件
@ComponentScan(value = {"com.atguigu.spring5.a3sp5newfunc"}) // 部件扫描，可以写多个包，用逗号隔开
public class SpringConfig {

    /**
     * 日志功能
     */
    private static final Logger log = LoggerFactory.getLogger(SpringConfig.class);

    /**
     * 函数式注入bean
     * @return
     */
    public User registerBean() {

        //1 创建 GenericApplicationContext 对象
        GenericApplicationContext context = new GenericApplicationContext();
        //2 调用 context 的方法对象注册
        context.refresh();
        context.registerBean(User.class,() -> new User());
        //3 获取在 spring 注册的对象，通过全限定名，则不能在注册时添加beanId。
        User user = (User)context.getBean("com.atguigu.spring5.a3sp5newfunc.User");
        log.info("已完成User bean通过类全限定名的注册");

        // 注册方式2
        context.registerBean("user1", User.class,() -> new User("a", "b"));
        User user2 = (User)context.getBean("user1");
        log.info("已完成User bean添加beanId的注册");
        return user2;
    }

}
