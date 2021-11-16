package com.xyb.a8assistcls;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch的用法，保证相关线程执行固定次数和之后，再运行后面的流程。
 *      6个人出去后，再锁门。
 */
public class A1CdlUse {

    public static void main(String[] args) throws InterruptedException {

        // 创建一个cdl对象，并设置其次数
        CountDownLatch cdl = new CountDownLatch(6);

        for(int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "完成");

                // 调用cdl的-1方法
                cdl.countDown();
            }, i + "").start();
        }

        new Thread((()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "是否被唤醒");

        }), "aaa").start();

        // 一直等待到cdl的数值为0时，为0时唤醒其它线程。
        cdl.await();

        System.out.println("所有的都已经完成。");

    }

}
