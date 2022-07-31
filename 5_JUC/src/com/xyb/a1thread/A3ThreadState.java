package com.xyb.a1thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程状态转换。
 * 只有在抢锁的时候，是阻塞，其它的sleep()、join()、对象.wait()都是等待。
 */
public class A3ThreadState {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();

        Thread t2 = new Thread(()->{
            try {
                synchronized (o) {
                    TimeUnit.SECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        Thread t1 = new Thread(()->{
            String threadName = Thread.currentThread().getName();
            try {
                System.out.println(threadName + "sleep 5s");
                TimeUnit.SECONDS.sleep(5);

                System.out.println(threadName + " join " + t2.getName() + " 5s");
                t2.start();
                t2.join(5000);

                System.out.println(threadName + "yield 让出时间片");
                Thread.yield();

                System.out.println(threadName + "抢锁中");
                synchronized (o) {
                    System.out.println(threadName + "抢到锁了");

                    Thread.sleep(5000);

                    System.out.println(threadName + "中被加锁对象wait()");
                    o.wait(5000);

                    System.out.println("---------------------------");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }



        }, "t1");


        System.out.println("主线程每隔 1s 查看" + t1.getName() + "线程状态");
        while (true) {
            System.out.println("当前状态为：" + t1.getState());
            if (t1.getState().equals(Thread.State.NEW))
                t1.start();

            if(t1.getState().equals(Thread.State.TERMINATED))
                break;

            TimeUnit.SECONDS.sleep(1);
        }
    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {



        }
    }

}
