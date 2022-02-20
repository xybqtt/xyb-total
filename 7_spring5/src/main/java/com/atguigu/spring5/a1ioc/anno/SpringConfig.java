package com.atguigu.spring5.a1ioc.anno;

import org.springframework.context.annotation.*;

/**
 * 用配置类代替配置文件
 */
@Configuration // 让spring知道这个是配置类
// 开启组件扫描，或在配置文件中添加<context:component-scan base-package="com.atguigu.spring5.a1ioc.anno"></context:component-scan>，需要添加context名称空间
@ComponentScan(value = {"com.atguigu.spring5.a1ioc.anno"})
@Import(value = JdbcConfig.class) // 引入JdbcConfig.class文件
@PropertySource(value = "a1ioc/xml/config.properties")
public class SpringConfig {

}
