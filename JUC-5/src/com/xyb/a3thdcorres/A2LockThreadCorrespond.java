package com.xyb.a3thdcorres;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间通信，即本线程任务完成，唤醒其它线程，在进入到本线程时，如果不符合条件，则本线程wait()，但
 * 是需要保证，被notifyAll()的线程中至少有一个的条件可以判定不用wait()，不然所有的线程会一直wait()，而没有人唤醒。
 */
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
            e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}
