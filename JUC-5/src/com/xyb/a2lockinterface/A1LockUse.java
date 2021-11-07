package com.xyb.a2lockinterface;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock的使用，同样是卖票，
 * 卖票：3个售票员卖30张票
 *      1、创建资源类，定义属性和操作方法
 *      2、创建多个线程，调用资源类的操作方法
 */
public class A1LockUse {

    public static void main(String[] args) {

        LTicket lTicket = new LTicket();

        // 创建3个线程，调用资源类的操作方法
        new Thread(()->{
            for(int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        }, "aa").start();

        new Thread(()->{
            for(int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        }, "bb").start();

        new Thread(()->{
            for(int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        }, "cc").start();

    }

}

/**
 * 创建资源类
 */
class LTicket {

    // 总票数
    private int number = 30;

    // 创建可重入锁
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 不使用synchronized，使用Lock实现
     */
    public void sale() {
        // 上锁
        lock.lock();

        try {
            // 判断：是否有票
            if (number > 0)
                System.out.println(Thread.currentThread().getName() + "：卖出1张，剩下：" + --number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 解锁，为了防止出现异常，导致不能解锁
            lock.unlock();
        }


    }

}