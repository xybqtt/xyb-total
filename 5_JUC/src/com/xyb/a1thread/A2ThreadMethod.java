package com.xyb.a1thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 线程常用方法
 * - 成员方法：
 *   - t.start()：启动线程；
 *   - t.getState()：获取线程状态
 *   - t.setName(String)：设置线程名；
 *   - t.getName()：获取线程名；
 *   - t.setPriority(10)：设置线程优先级，查看此方法源码可知优先级从1到10，默认是5；
 *   - t.getPriority()：获取线程优先级；
 *   - t.interrupt()：
 *     - 中断线程，会让被调用此方法的线程抛出InterruptedException；
 *     - 但并没有结束此线程，在线程内部捕获异常后，接着往下处理就行了；
 *     - 如果线程正在休眠中(Thread.sleep(n))，也会抛出此异常；
 *   - t1.join()：在本线程调用t1.join()，表示在这个代码位置让t1线程先执行完，t再接着运行；
 *   - t1.setDaemon(boolean)：true设置为守护线程，false相反。
 *   - t1.isDaemon()：true守护线程，false非守护线程
 * - 静态方法：
 *   - Thread.sleep(long)：线程的静态方法，使当前线程休眠 long ms。
 *   - Thread.yield()：当前线程让出CPU，让其它线程先执行，但最终还是由CPU决定；
 */
public class A2ThreadMethod {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Thread t1 = new Thread(()-> {
            System.out.println("进入t1");
            try {
                for (int i = 0; i < 1000; i++) {
                    System.out.println(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + ".interrupt()被调用，抛出了InterruptedException异常。");
            }
            System.out.println("退出t1");
        });

        new Thread(()-> {
            System.out.println("进入t2了，让t1先把剩下的执行完，再执行t2");
            try {
                TimeUnit.SECONDS.sleep(3);
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("退出t2");

        }, "t2").start();

//        Thread.yield(); // 当前线程让出CPU，让其它线程先执行，但最终还是由CPU决定

        t1.setName("t1");
        t1.setPriority(10); // 设置线程优先级，查看代码可看到优先级从1到10，默认是5
        t1.setDaemon(false); // 是否设置为守护线程，true是，false否

        System.out.println("线程名" + t1.getName()
                + "。守护线程：" + t1.isDaemon()
                + "。优先级：" + t1.getPriority()
                + "。线程状态：" + t1.getState());
        t1.start();

        t1.wait();
        t1.notifyAll();

        System.out.println(Thread.currentThread().getName() + "休眠开始");
        Thread.sleep(5000); // 线程休眠 ms，线程的静态方法，使当前线程休眠
        System.out.println(Thread.currentThread().getName() + "休眠结束");

        t1.interrupt(); // 中断线程，但并没有结束线程，一般用于中断正在休眠的线程

    }

}
