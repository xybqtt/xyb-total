package com.xyb.a6thdslock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁演示
 */
public class A1Rlock {

    public static void main(String[] args) {

        C1 c1 = new C1();
        new Thread(()->{
            c1.f1();
        },"synchronized").start();


        C2 c2 = new C2();
        new Thread(()->{
            c2.f1();
        },"ReenTrantLock").start();
    }

}

/**
 * synchronized的可重入锁演示
 */
class C1 {

    public synchronized void f1() {
        // 此时已经加了一把锁
        System.out.println(Thread.currentThread().getName() + "---进入f1");
        // 进入f2之前还要加一把锁，如果不是可重入锁的话，那么进入f2时就不能获取锁，就会造成死锁
        f2();
        System.out.println(Thread.currentThread().getName() + "---退出f1");
    }

    public synchronized void f2() {
        System.out.println(Thread.currentThread().getName() + "---f2");
    }

}

/**
 * ReentrantLock的可重入锁演示
 */
class C2 {

    private final ReentrantLock lock = new ReentrantLock();

    public void f1() {
        lock.lock();
        try {
            // 此时已经加了一把锁
            System.out.println(Thread.currentThread().getName() + "---进入f1");
            // 进入f2之前还要加一把锁，如果不是可重入锁的话，那么进入f2时就不能获取锁，就会造成死锁
            f2();
            System.out.println(Thread.currentThread().getName() + "---退出f1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void f2() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "---f2");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
