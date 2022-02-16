package com.atguigu.spring5.a3aop.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

@Component
@Aspect
@Order(1)
public class UserProxy1 {

    // 定义了一个切入点，可以匹配Service1中所有方法
    @Pointcut(value = "execution(public int com.atguigu.spring5.a3aop.anno.User.*(..))")
    public void pointcut1() {
    }

    @Before(value = "pointcut1()")
    public void before(JoinPoint joinPoint) {
        System.out.println("UserProxy1.前置通知...");
    }


}
