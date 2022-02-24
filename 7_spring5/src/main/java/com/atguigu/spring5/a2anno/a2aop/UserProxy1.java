package com.atguigu.spring5.a2anno.a2aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect(value = "userProxy1")
@Order(value = 1)
public class UserProxy1 {

    // 定义了一个切入点，可以匹配Service1中所有方法
    @Pointcut(value = "execution(public int com.atguigu.spring5.a2anno.xml.User.*(..))")
    public void pointcut1() {
    }

    @Before(value = "pointcut1()")
    public void before(JoinPoint joinPoint) {
        System.out.println("UserProxy1.前置通知：" + UserProxy.getJPInfo(joinPoint));
    }


}
