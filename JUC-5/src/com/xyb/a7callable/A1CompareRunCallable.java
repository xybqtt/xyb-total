package com.xyb.a7callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 比较Runnable和Callable接口
 */
public class A1CompareRunCallable {

    public static void main(String[] args) throws Exception {
        // Runnable接口创建线程
        new Thread(new RunnableImpl(), "runnable");

        // 或者直接用lam表达式，但是就无法调用FutureTask的get方法了
//        new Thread(new FutureTask<Integer>(()->{
//            return 1024;
//        }), "lamCallable");

        // Callable接口创建线程，FutureTask实现了Runnable，且其构造方法可以传入Callable
        FutureTask<Integer> f1 = new FutureTask<>(new CallableImpl());
        new Thread(f1, "callable").start();

        // 注意FutureTask有阻塞线程的功能，即如果Callable的call()方法没执行出结果或未出现异常，这个方法会一直等待，如果在主线程调用，也会阻塞主线程
        System.out.println(f1.get());
        System.out.println(Thread.currentThread().getName() + "结束。");




    }

}

class RunnableImpl implements Runnable {

    @Override
    public void run() {

    }
}

class CallableImpl implements Callable {

    @Override
    public Object call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
//         int i = 5 / 0;
        return 5;
    }
}
