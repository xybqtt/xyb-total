package com.xyb.a11threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示创建线程池的3种方法
 */
public class A1CreateThreadPoolDemo {

    public static void main(String[] args) {
        // 一池一线程
        f1();

        // 一池N线程
        f2();

        // 可扩容线程池
        f3();
    }


    /**
     * 可扩容线程池，根据需求来创建线程数量
     */
    public static void f3() {

        // 池中线程数量可扩容
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 20; i++) {
                int finalI = i;
                threadPool.execute(()->{
                    System.out.println("可扩容线程" + Thread.currentThread().getName() + "执行任务" + finalI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }


    }

    /**
     * 一池N线程
     */
    public static void f2() {

        // 池中有5个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try {
            // 10个任务添加到池中
            for (int i = 0; i < 10; i++) {
                // 执行你的血
                threadPool.execute(()->{
                    System.out.println("一池n线程" + Thread.currentThread().getName() + "处理任务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    /**
     * 一池一线程
     */
    public static void f1() {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println("一池1线程" + Thread.currentThread().getName() + "处理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }

}
