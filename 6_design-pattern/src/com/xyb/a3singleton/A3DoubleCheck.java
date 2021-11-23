package com.xyb.a3singleton;

import java.util.concurrent.CyclicBarrier;

/**
 * 双重检查
 */
public class A3DoubleCheck {

    public static void main(String[] args) {
        // 查看懒汉式(线程安全)的方式
        final A3Singleton1[] ss = new A3Singleton1[2];

        CyclicBarrier cb = new CyclicBarrier(2, ()->{
            System.out.println("双重检查，2个是同一个：" + (ss[0] == ss[1]));
        });

        for (int i = 0; i < ss.length; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    ss[finalI] = A3Singleton1.getInstance();
                    cb.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}


/**
 * 懒汉式(线程安全)
 */
class A3Singleton1 {

    // 必须加上volatile
    private static volatile A3Singleton1 s1;

    private A3Singleton1() {
    }

    /**
     * 双重检查，
     *      1、保证只会生成一个对象；
     *      2、提升了效率，只有第一次会进行加锁操作，后面根据判断就不需要加锁了。
     * @return
     */
    public static A3Singleton1 getInstance() {

        if(s1 == null) {
            synchronized (A3Singleton1.class) {
                if(s1 == null) {
                    s1 = new A3Singleton1();
                }
            }
        }
        return s1;
    }
}