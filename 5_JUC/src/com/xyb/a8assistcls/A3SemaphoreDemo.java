package com.xyb.a8assistcls;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore信号灯：
 *      6辆车停3个停车位
 */
public class A3SemaphoreDemo {

    public static void main(String[] args) {
        // 创建Semaphore，设置许可量
        Semaphore semaphore = new Semaphore(3);

        // 模拟6车
        for (int i = 0; i < 6; i++) {

            new Thread(()->{
                try {
                    // 抢车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");

                    // 停一段时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 离开车位
                    semaphore.release();
                    System.out.println("------" + Thread.currentThread().getName() + "离开了车位");
                }
            }, String.valueOf(i + 1)).start();

        }
    }

}
