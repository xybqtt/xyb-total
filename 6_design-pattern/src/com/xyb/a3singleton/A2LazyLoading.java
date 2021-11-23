package com.xyb.a3singleton;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 懒汉式(线程安全)创建方式
 *      提供一个静态的公有方法，当使用到该方法时，才去创建 instance，加倘
 */
public class A2LazyLoading {

    public static void main(String[] args) {

        // 查看懒汉式(线程安全)的方式
        final A2Singleton1[] ss = new A2Singleton1[2];

        CyclicBarrier cb = new CyclicBarrier(2, ()->{
            System.out.println("懒汉式(线程安全)不会出现的线程安全问题，2个是同一个：" + (ss[0] == ss[1]));
        });

        for (int i = 0; i < ss.length; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    ss[finalI] = A2Singleton1.getInstance();
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
class A2Singleton1 {

    private static A2Singleton1 s1;

    private A2Singleton1() {
    }

    /**
     * 此处方法加了synchronized，是线程安全的，但大量线程同时获取此对象时，会影响获取速度。
     * @return
     */
    public static synchronized A2Singleton1 getInstance() {

        if(s1 == null) {
            // 就处是暂停也不会造成线程不安全
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s1 = new A2Singleton1();
        }

        return s1;
    }
}

