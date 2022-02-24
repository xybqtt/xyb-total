package com.atguigu.spring5.a1xml.a2aop;

import org.aspectj.lang.JoinPoint;

public class MyAspect2 {

    public void before(JoinPoint joinPoint) {
        System.out.println("UserProxy1.前置通知：" + MyAspect1.getJPInfo(joinPoint));
    }


}
