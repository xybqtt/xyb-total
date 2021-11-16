package com.xyb.a12gcrelative;

public class A1SystemGCTest {

    public static void main(String[] args) throws InterruptedException {
        new A1SystemGCTest();
        System.gc(); // 提醒jvm的垃圾回收器执行gc，但不确定是否马上执行

        System.runFinalization(); // 强制调用失去引用对象的finalize()方法，如果没有GC，则不会调用。
        Thread.sleep(10000);
        System.out.println("------------");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("这是SystemGCTest1的finalize()方法。");
    }
}
