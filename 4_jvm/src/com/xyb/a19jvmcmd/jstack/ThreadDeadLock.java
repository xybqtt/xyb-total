package com.xyb.a19jvmcmd.jstack;

/**
 * 写个线程死锁问题，用jstack查看
 */
public class ThreadDeadLock {

    public static void main(String[] args) {

        StringBuilder sb1 = new StringBuilder("");
        StringBuilder sb2 = new StringBuilder("");

        new Thread(){

            @Override
            public void run() {
                synchronized (sb1) {
                    sb1.append("a");
                    sb2.append("b");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (sb2) {
                        sb1.append("c");
                        sb2.append("d");
                    }
                }
            }
        }.start();

        new Thread(){

            @Override
            public void run() {
                synchronized (sb2) {
                    sb1.append("a");
                    sb2.append("b");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (sb1) {
                        sb1.append("c");
                        sb2.append("d");
                    }
                }
            }
        }.start();

        System.out.println("结束");

    }

}
