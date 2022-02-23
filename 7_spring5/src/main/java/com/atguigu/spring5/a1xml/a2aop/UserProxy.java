package com.atguigu.spring5.a1xml.a2aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

public class UserProxy {

    /**
     * 通过JointPoint获取连接点的信息
     * @param joinPoint
     * @return
     */
    public static String getJPInfo(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder("");
        sb.append("通过JointPoint获取被增强方法信息：");
        sb.append("    目标方法名为:" + joinPoint.getSignature().getName());
        sb.append("    目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        sb.append("    目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        sb.append("    目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            sb.append("    第" + (i + 1) + "个参数为:" + args[i]);
        }
        sb.append("    被代理的对象:" + joinPoint.getTarget());
        sb.append("    代理对象自己:" + joinPoint.getThis());
        return joinPoint.getSignature().getDeclaringType().getSimpleName() + " " + joinPoint.getSignature().getName();
    }

    public void before(JoinPoint joinPoint) {
        System.out.println("UserProxy前置通知...，连接点：" + getJPInfo(joinPoint));
    }

    public void after(JoinPoint joinPoint) {
        System.out.println("UserProxy最终通知...，连接点：" + getJPInfo(joinPoint));
    }

    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("UserProxy返回(后置)通知...，连接点：" + getJPInfo(joinPoint));
    }

    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("UserProxy异常通知...，连接点：" + getJPInfo(joinPoint));
    }

    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("UserProxy环绕通知前...，连接点：" + getJPInfo(pjp));
        Object obj = pjp.proceed(); // 执行被增强的方法
        System.out.println("UserProxy环绕通知后...，连接点：" + getJPInfo(pjp) + "，执行结果为" + obj);
        return obj;
    }
}
