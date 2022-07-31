package com.xyb.a1thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程的4种创建方式：
 * 4种方式在运行时，本质都是调用Thread.run()方法
 *      但是在默认的run()方法中(除了继承Thread这种形式)，实际是调用了runnable.run()方法，
 *      所以本质是runnable.run()方法的运行。
 */
public class A1ThreadCreate {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1、通过继承 Thread 类
        Thread t1 = new MyThread();
        t1.setName("extends Thread");
        t1.start();

        // 2、通过 implements Runnable，并作为Thread的构造参数
        new Thread(()->System.out.println(Thread.currentThread().getName() + "方式：已运行。"), "implements Runnable").start();

        // 3、前2种都是无法获取线程运行返回值的，通过callable可以获取线程返回值。
        /**
         * 此处为什么要写得如此啰嗦？
         * 此处的运行流程：Thread.start()内部，会调用FutureTask.run()方法，调用Callable.call()方法，
         * Callable运行的结果存入FutureTask，我们从Ft中去获取结果。
         */
        FutureTask ft = new FutureTask(()-> {
            System.out.println(Thread.currentThread().getName() + "方式：已运行。");
            return "1";
        });
        new Thread(ft, "Callable").start();

        System.out.println("callable的返回值(会阻塞)：" + ft.get());


        // 4、通过线程池

    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "方式：已运行。");
        }
    }
}
