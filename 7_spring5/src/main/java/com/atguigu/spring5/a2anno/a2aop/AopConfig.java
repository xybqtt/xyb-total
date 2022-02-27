package com.atguigu.spring5.a2anno.a2aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy // 开启aop代理
@ComponentScan(value = {"com.atguigu.spring5.a2anno.a2aop"}) // 部件扫描
public class AopConfig {
}
