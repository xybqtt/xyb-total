package com.atguigu.boot.a2annoshow.a1configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration：全模式测试，该模式下注入容器中的同一个组件无论被取出多少次都是同一个bean实例，
 * 即单实例对象，在该模式下SpringBoot每次启动都会判断检查容器中是否存在该组件。
 */
@Configuration(proxyBeanMethods = true)
public class A1FullConfig {

    @Bean(value = "fullClass1")
    public Class1 createClass1() {
        Class1 class1 = new Class1();
        class1.setField1("1");
        class1.setClass2(createClass2());
        return class1;
    }

    @Bean(value = "fullClass2")
    public Class2 createClass2() {
        return new Class2("1");
    }
}

