package com.xyb.a9readwritelock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class A1ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // 写的时候是单独写
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.put(num + "", num + "");
            }, "写线程" + i).start();
        }

        // 读的时候是并发读
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.get(num + "");
            }, "读线程" + i).start();
        }
    }

}

class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        // 添加写锁
        rwLock.writeLock().lock();


        try {
            System.out.println(Thread.currentThread().getName() + "写：" + key);
            // 暂停一会
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完了：" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }

    }

    /**
     * 读的时候是并发读
     * @param key
     * @return
     */
    public Object get(String key) {
        // 添加读锁
        rwLock.readLock().lock();

        Object result = null;
        // 暂停一会
        try {
            System.out.println(Thread.currentThread().getName() + "正在读：" + key);
            TimeUnit.MICROSECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读完了：" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }

        return result;

    }

}
