package com.atguitu.boot.a2annoshow.a1configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration：lite模式测试，该模式下注入容器中的同一个组件无论被取出多少次都是不同的bean实例，
 * 即多实例对象，在该模式下SpringBoot每次启动会跳过检查容器中是否存在该组件。
 */
@Configuration(proxyBeanMethods = false)
public class A2LiteConfig {

    @Bean(value = "liteClass1")
    public Class1 createClass1() {
        Class1 class1 = new Class1();
        class1.setField1("1");
        class1.setClass2(createClass2());
        return class1;
    }

    @Bean(value = "liteClass2")
    public Class2 createClass2() {
        return new Class2("1");
    }

}

