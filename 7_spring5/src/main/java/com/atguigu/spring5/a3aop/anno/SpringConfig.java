package com.atguigu.spring5.a3aop.anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 用配置类代替配置文件
 */
@Configuration // 让spring知道这个是配置类
@ComponentScan(value = {"com.atguigu.spring5.a3aop"}) // 开启组件扫描
@EnableAspectJAutoProxy // 启动生成代理对象
public class SpringConfig {
}
