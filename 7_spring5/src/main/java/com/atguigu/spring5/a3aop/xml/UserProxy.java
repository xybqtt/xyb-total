package com.atguigu.spring5.a3aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

public class UserProxy {

    // 定义了一个切入点，可以匹配Service1中所有方法
    @Pointcut(value = "execution(public int com.atguigu.spring5.a3aop.xml.User.*(..))")
    public void pointcut1() {
    }

    @Before(value = "pointcut1()")
    public void before(JoinPoint joinPoint) {
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());
        System.out.println("前置通知...");
    }

    @After(value = "pointcut1()")
    public void after(JoinPoint joinPoint) {
        System.out.println("最终通知...");
    }

    @AfterReturning(value = "pointcut1()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("返回(后置)通知...");
    }

    @AfterThrowing(value = "pointcut1()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("异常通知...");
    }

    @Around(value = "pointcut1()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知前...");
        Object obj = pjp.proceed(); // 执行被增强的方法
        System.out.println("环绕通知后...，执行结果为" + obj);
        return obj;
    }
}
