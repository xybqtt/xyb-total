package com.xyb.a8assistcls;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier的用法：
 *      一组线程达到某一个公共屏障点(common barrier point)时，唤醒相关线程。
 *      集齐7龙珠召唤神龙。
 */
public class A2CyclicBarrier {

    // 创建固定值
    private static final int NUMBER = 7;

    public static void main(String[] args) {
        // 创建CyclicBarrier，这个第一个number参数代表有几个线程达到标时，运行后面的Runnable。
        CyclicBarrier cb = new CyclicBarrier(NUMBER, ()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行顺序2：----集齐7颗龙珠可以召唤神龙");
            System.out.println();
        });

        // 集齐7龙珠
        for (int i = 0; i < NUMBER; i++) {
            new Thread(()->{
                System.out.println("执行顺序1：这是" + Thread.currentThread().getName() + "星珠");
                try {
                    // 每个线程执行到这都等待，等所有都运行到这后，执行cb里面的Runnable线程。
                    cb.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("执行顺序3：cb.runnable执行后，再执行" + Thread.currentThread().getName() + "后面的代码");
            }, String.valueOf(i + 1)).start();
        }
    }

}
