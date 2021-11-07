package com.xyb.a1sync;

/**
 * synchronized关键字的使用
 * 卖票：3个售票员卖30张票
 *      1、创建资源类，定义属性和操作方法
 *      2、创建多个线程，调用资源类的操作方法
 */
public class A1SyncUse {


    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // 创建3个线程，调用资源类的操作方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "aa").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "bb").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "cc").start();


    }

}

/**
 * 创建资源类
 */
class Ticket {

    // 总票数
    private int number = 30;

    public synchronized void sale() {
        // 判断：是否有票
        if(number > 0)
            System.out.println(Thread.currentThread().getName() + "：卖出1张，剩下：" + --number);

    }

}




