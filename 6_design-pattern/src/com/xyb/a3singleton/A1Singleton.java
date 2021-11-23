package com.xyb.a3singleton;

/**
 * 单例模式的2种实现：
 *      饿汉式(静态变量)；
 *      饿汉式(静态块)；
 * 这2种写法本质上是一样的，都是在类初始化阶段<clinit>()，对私有静态变量赋值，
 * 且无线程安全问题，因为<clinit>()是同步方法。
 *
 */
public class A1Singleton {

    public static void main(String[] args) {

        A1SingleTon1 s1 = A1SingleTon1.getInstance();
        A1SingleTon1 s2 = A1SingleTon1.getInstance();
        System.out.println("查看SingleTon1(静态变量)的2个对象都是不是同一个对象：" + (s1 == s2));

        A1SingleTon2 s3 = A1SingleTon2.getInstance();
        A1SingleTon2 s4 = A1SingleTon2.getInstance();
        System.out.println("查看SingleTon2(静态代码块)的2个对象都是不是同一个对象：" + (s3 == s4));

    }

}

/**
 * 1、静态变量形式
 */
class A1SingleTon1 {

    private static final A1SingleTon1 S1 = new A1SingleTon1();

    private A1SingleTon1() {
    }

    public static A1SingleTon1 getInstance() {
        return S1;
    }
}


/**
 * 2、静态块形式
 */
class A1SingleTon2 {

    private static final A1SingleTon2 S2;

    static {
        S2 = new A1SingleTon2();
    }

    private A1SingleTon2() {
    }

    public static A1SingleTon2 getInstance() {
        return S2;
    }
}