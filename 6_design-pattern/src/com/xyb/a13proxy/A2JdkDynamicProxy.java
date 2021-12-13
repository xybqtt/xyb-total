package com.xyb.a13proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理，我们需要知道如下内容：
 *      被代理的类；
 *      被代理类的接口；
 *      被代理类的类加载器；
 *      被代理类的实例；
 *      哪个方法被增强；
 *      如何增强
 */
public class A2JdkDynamicProxy {

    public static void main(String[] args) {

        A2Subject a2Subject = new A2RealSubject();
        A2Subject o = (A2Subject) new A2ProxyFactory(a2Subject).getProxyInstance();
        o.request();

    }

}

interface A2Subject {
    public void request();
}

/**
 * 被代理对象
 */
class A2RealSubject implements A2Subject {

    @Override
    public void request() {
        System.out.println("被代理对象a2RealSubject.request()");
    }
}


class A2ProxyFactory {
    /**
     * 被代理对象
     */
    private Object target;

    public A2ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象生成代理对象
     * @return
     */
    public Object getProxyInstance() {
        /**
         * 参数介绍：
         *      目标对象的类加载器；
         *      目标对象的接口数组；
         *      增强的方法接口，在实现方法中可以调用目标对象方法。
         */
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("JDK 代理开始~~");
                Object returnVal = method.invoke(target, args);
                System.out.println("JDK 代理提交");
                return returnVal;
            }
        });
    }


}