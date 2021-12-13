package com.xyb.a13proxy;

/**
 * 静态代理
 */
public class A1StaticProxy {

    public static void main(String[] args) {
        A1Proxy proxy = new A1Proxy(new A1RealSubject());
        proxy.request();
    }

}

/**
 * 抽象主题
 */
interface A1Subject {
    public void request();
}

/**
 * 真实主题
 */
class A1RealSubject implements A1Subject {
    @Override
    public void request() {
        System.out.println("被代理对象realSubject.request()");
    }
}

/**
 * 代理对象
 */
class A1Proxy implements A1Subject {

    private A1RealSubject a1RealSubject;

    public A1Proxy(A1RealSubject a1RealSubject) {
        this.a1RealSubject = a1RealSubject;
    }

    @Override
    public void request() {
        preRequest();
        this.a1RealSubject.request();
        postRequest();
    }

    public void preRequest() {
        System.out.println("代理对象proxy.preRequest()");
    }

    public void postRequest() {
        System.out.println("代理对象proxy.postRequest()");
    }
}








