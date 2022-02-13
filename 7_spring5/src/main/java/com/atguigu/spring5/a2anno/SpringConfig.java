package com.atguigu.spring5.a2anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 用配置类代替配置文件
 */
@Configuration // 让spring知道这个是配置类
@ComponentScan(value = {"com.atguigu.spring5.a2anno"}) // 开启组件扫描
public class SpringConfig {
}
