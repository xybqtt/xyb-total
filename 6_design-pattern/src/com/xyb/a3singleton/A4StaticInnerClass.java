package com.xyb.a3singleton;

import java.util.concurrent.CyclicBarrier;

/**
 * 静态内部类
 */
public class A4StaticInnerClass {

    public static void main(String[] args) {
        // 查看懒汉式(线程安全)的方式
        final A4Singleton1[] ss = new A4Singleton1[2];

        CyclicBarrier cb = new CyclicBarrier(2, ()->{
            System.out.println("私有静态内部类，2个是同一个：" + (ss[0] == ss[1]));
        });

        for (int i = 0; i < ss.length; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    ss[finalI] = A4Singleton1.getInstance();
                    cb.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}

class A4Singleton1 {

    private A4Singleton1() {

    }

    private static class InnerClass {
        public static final A4Singleton1 INSTANCE = new A4Singleton1();
    }

    public static A4Singleton1 getInstance() {
        return InnerClass.INSTANCE;
    }

}
