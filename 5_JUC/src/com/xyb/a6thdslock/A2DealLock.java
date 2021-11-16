package com.xyb.a6thdslock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁演示
 */
public class A2DealLock {

    static Object a = new Object();

    static Object b = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "已获取a的锁，现试图获取b的锁");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "获取b的锁");
                }

            }
        }, "aa").start();

        new Thread(()->{
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "已获取b的锁，现试图获取a的锁");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + "获取a的锁");
                }

            }
        }, "bb").start();
    }

}
