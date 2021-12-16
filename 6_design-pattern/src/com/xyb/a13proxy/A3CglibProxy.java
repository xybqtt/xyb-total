package com.xyb.a13proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib实现动态代理
 */
public class A3CglibProxy {

    public static void main(String[] args) {
        // 创建代理对象
        A3ProxyFactory a3ProxyFactory = new A3ProxyFactory(new A3RealSubject());
        A3RealSubject a3RealSubject = (A3RealSubject) a3ProxyFactory.getInstance();

        // 执行代理对象的方法，触发intecept方法，从而实现对目标对象的调用。
        a3RealSubject.request();
    }

}

class A3RealSubject {
    public void request() {
        System.out.println("被代理对象A3RealSubject.request()");
    }
}

class A3ProxyFactory implements MethodInterceptor {

    private Object target;

    public A3ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getInstance() {
        // 1、创建一个工具类
        Enhancer enhancer = new Enhancer();

        // 2、设置代理对象的父类
        enhancer.setSuperclass(target.getClass());

        // 3、设置回调函数
        enhancer.setCallback(this);

        // 4、创建target的子类对象，即代理对象
        return enhancer.create();
    }

    /**
     * 重写此方法，会调用目标对象方法
     * @param o
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理模式开始");
        Object obj = method.invoke(this.target, args);
        System.out.println("cglib代理模式结束");
        return obj;
    }
}















