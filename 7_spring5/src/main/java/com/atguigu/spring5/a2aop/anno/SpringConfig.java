package com.atguigu.spring5.a2aop.anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 用配置类代替配置文件
 */
@Configuration // 让spring知道这个是配置类
@ComponentScan(value = {"com.atguigu.spring5.a2aop"}) // 开启组件扫描
@EnableAspectJAutoProxy(proxyTargetClass = true) // 启动生成代理对象，后面可以不写，只要写了这个注解后面默认是true
public class SpringConfig {
}
