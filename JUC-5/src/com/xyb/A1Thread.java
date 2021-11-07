package com.xyb;

/**
 * 用户线程和守护线程
 */
public class A1Thread {

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "aa").start();

        System.out.println(Thread.currentThread().getName() + "over");
    }

}
