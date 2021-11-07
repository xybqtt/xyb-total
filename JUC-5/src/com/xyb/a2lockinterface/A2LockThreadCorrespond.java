package com.xyb.a2lockinterface;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class A2LockThreadCorrespond {

    public static void main(String[] args) {
        LShare lShare = new LShare();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    lShare.inc();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "aa").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    lShare.dec();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "bb").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    lShare.inc();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "cc").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    lShare.dec();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "dd").start();
    }

}

/**
 * 第1步，创建资源类，在资源类创建属性和操作方法；
 */
class LShare {

    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    // +1的方法
    public void inc() throws InterruptedException {

        lock.lock();

        try {
            // 第2步 判断 干活 通知
            // 判断
            while (number != 0) {
                condition.await();
            }
            // 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "：" + number);

            // 通知其它线程
            condition.signalAll();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    // -1的方法
    public void dec() throws InterruptedException {
        lock.lock();

        try {
            // 第2步 判断 干活 通知
            // 判断
            while (number != 1) {
                condition.await();
            }
            // 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "：" + number);

            // 通知其它线程
            condition.signalAll();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }


}
