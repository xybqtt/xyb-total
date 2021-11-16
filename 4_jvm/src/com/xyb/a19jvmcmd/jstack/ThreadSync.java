package com.xyb.a19jvmcmd.jstack;

/**
 * 写个线程死锁问题，用jstack查看
 */
public class ThreadSync {

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(1000 * 10 * 60);

    }

}
