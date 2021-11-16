package com.xyb.a12gcrelative;

import com.xyb.a6heap.A3HeapGC;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 强、软、弱、虚4种引用的回收机制
 * -Xms200m -Xmx200m
 */
public class A4ReferenceTest {

    public static A3HeapGC a3HeapGC = new A3HeapGC();


    public static void main(String[] args) throws InterruptedException {
//        testSoftRef();
//
//        testWeakRef();

        testPhantomRef();
    }

    /**
     * 测试虚引用
     */
    private static void testPhantomRef() throws InterruptedException {

        // new 实例对象
        Object obj = new Object();

        // 创建虚引用队列
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();

        // 创建虚引用，为其指定对象和虚引用队列
        PhantomReference<Object> sf = new PhantomReference<Object>(obj, referenceQueue);

        // 让此线程启动，当虚引用引用的对象被GC时，查看虚引用队列有无元素进入
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Object obj = null;
                    try {
                        obj = referenceQueue.remove();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(obj != null)
                        System.out.println("实例被GC了");
                }
            }
        });
        t.setDaemon(true);
        t.start();

        obj = null;
        System.gc();
        Thread.sleep(1000);

        if(obj == null)
            System.out.println("obj是null");
        else
            System.out.println("obj不是null");


    }

    /**
     * 测试弱引用：
     *      当GC时，发现弱引用就回收
     */
    private static void testWeakRef() throws InterruptedException {

        System.out.println("测试弱引用开始");
        // 测试软引用的回收时机
        // 创建 0.8old 的软引用
        WeakReference<byte[]> sr = new WeakReference<byte[]>(new byte[(int) (A3HeapGC.oldSize * 0.8)]);
        System.out.println("通过弱引用获取对象：" + sr.get());

        // 空间足够，gc不会清除软引用
        System.gc();
        Thread.sleep(3000);
        System.out.println("GC后查看是否还可以获取此弱引用的对象：" + sr.get());

        System.out.println("测试弱引用结束");

    }

    /**
     * 测试软引用：
     *      当为对象分配的空间小于old剩余空间时，软引用不会被清除；
     *      反之，会先清除软引用，再分配空间。
     */
    public static void testSoftRef() throws InterruptedException {
        System.out.println("测试软引用开始");
        // 测试软引用的回收时机
        // 创建 0.8old 的软引用
        SoftReference<byte[]> sr = new SoftReference<byte[]>(new byte[(int) (A3HeapGC.oldSize * 0.8)]);
        System.out.println("通过软引用获取对象：" + sr.get());

        // 空间足够，gc不会清除软引用
        System.gc();
        Thread.sleep(3000);
        System.out.println("GC后查看是否还可以获取此软引用的对象：" + sr.get());

        // 再创建 强引用，且此对象的大小与软引用之和大于 old区大小，此时软引用被删除。
        byte[] strongRef = new byte[(int) (A3HeapGC.oldSize * 0.4)];
        System.out.println("分配\"强引用 + 软引用\"之和超出old区大小，软引用是否被清除：" + sr.get());
        System.out.println("测试软引用结束");
    }


}
