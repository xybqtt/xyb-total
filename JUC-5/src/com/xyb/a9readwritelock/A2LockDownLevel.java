package com.xyb.a9readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示锁降级
 */
public class A2LockDownLevel {

    public static void main(String[] args) {

        // 获取写锁和读锁
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wLock =  rwLock.writeLock();
        ReentrantReadWriteLock.ReadLock rLock =  rwLock.readLock();


        // 写锁生效
        wLock.lock();
        System.out.println("写锁生效");


        // 读锁生效
        rLock.lock();
        System.out.println("读锁生效");


        // 释放写锁
        wLock.unlock();
        System.out.println("释放写锁，此时锁降级，此线程只有读锁了");


        // 释放读锁
        rLock.unlock();
        System.out.println("释放读锁");


    }

}
