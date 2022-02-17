package com.atguigu.spring5.a1ioc.anno;

import org.springframework.context.annotation.*;

/**
 * 用配置类代替配置文件
 */
@Configuration // 让spring知道这个是配置类
@ComponentScan(value = {"com.atguigu.spring5.a1ioc.anno"}) // 开启组件扫描
@Import(JdbcConfig.class) // 引入JdbcConfig.class文件
@PropertySource("a1ioc/xml/config.properties")
public class SpringConfig {

}
