package com.xyb.a4orderthdcorres;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定制线程间通信，即每个线程的任务完成后，后面运行的线程顺序是固定的，即不唤醒所有线程，而是唤醒固定线程，synchronized实现不了。
 * 先运行A、再运行B、再C
 */
public class A1OrderThdCorres {


    public static void main(String[] args) {
        L3Share l3Share = new L3Share();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    l3Share.print5(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "aa").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    l3Share.print10(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "bb").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    l3Share.print15(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "cc").start();

    }

}


/**
 * 第1步，创建资源类，在资源类创建属性和操作方法；
 */
class L3Share {

    private int flag = 1;

    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    /**
     * flag决定行哪个线程
     * @throws InterruptedException
     */
    public void print5(int loop) throws InterruptedException {

        lock.lock();

        try {
            // 第2步 判断 干活 通知
            // 判断
            while (flag != 1) {
                c1.await();
            }
            // 干活
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + "：i = " + i + "，轮数 = " + loop);
            }
            // 修改标志位
            this.flag = 2;

            // 通知c2线程
            c2.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * flag决定行哪个线程
     * @throws InterruptedException
     */
    public void print10(int loop) throws InterruptedException {

        lock.lock();

        try {
            // 第2步 判断 干活 通知
            // 判断
            while (flag != 2) {
                c2.await();
            }
            // 干活
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "：i = " + i + "，轮数 = " + loop);
            }
            // 修改标志位
            this.flag = 3;

            // 通知c3线程
            c3.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * flag决定行哪个线程
     * @throws InterruptedException
     */
    public void print15(int loop) throws InterruptedException {

        lock.lock();

        try {
            // 第2步 判断 干活 通知
            // 判断
            while (flag != 3) {
                c3.await();
            }
            // 干活
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "：i = " + i + "，轮数 = " + loop);
            }
            // 修改标志位
            this.flag = 1;

            // 通知c1线程
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}

