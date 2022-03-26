package com.atguigu.boot.a2annoshow.a2conditional;


import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;
import org.springframework.stereotype.Component;

/**
 * java 1.8以上才装载此类
 */
@Component
@ConditionalOnJava(value = JavaVersion.NINE)
public class A3CondiOnJava {
}
