package com.xyb.a13completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class A1CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 异步调用，没有返回值
        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
        });

        cf1.get();

        // 异步调用，有返回值
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            int i = 5 / 0;
            return 1024;
        });
        cf2.whenComplete((t, u)->{
            System.out.println("--t=" + t); // 方法返回值
            System.out.println("--u=" + u); // 异常
        }).get();

    }

}
