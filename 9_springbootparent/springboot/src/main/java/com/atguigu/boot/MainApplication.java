package com.atguigu.boot;

import com.atguigu.boot.utils.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * 主程序类
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        // 1、返回ioc容器，到这就已经启动了
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);

        SpringUtil.setContext(context);

        // 2、查看容器中的组件
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);

        // 3、从容器中获取组件
    }

}
