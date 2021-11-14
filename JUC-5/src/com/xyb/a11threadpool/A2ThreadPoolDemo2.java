package com.xyb.a11threadpool;

import java.util.concurrent.*;

/**
 * 自定义线程池
 */
public class A2ThreadPoolDemo2 {

    public static void main(String[] args) {

        ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        try {
            for (int i = 0; i < 20; i++) {
                int finalI = i;
                threadPool.execute(()->{
                    System.out.println("自定义线程" + Thread.currentThread().getName() + "执行任务" + finalI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
