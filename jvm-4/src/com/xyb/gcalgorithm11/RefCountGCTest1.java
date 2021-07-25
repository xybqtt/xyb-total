package com.xyb.gcalgorithm11;

/**
 * 测试java不是使用的引用计数算法
 * -XX:+PrintGCDetails
 */
public class RefCountGCTest1 {

    private byte[] bigSize = new byte[5 * 1024 * 1024];

    Object ref = null;

    public static void main(String[] args) {
        RefCountGCTest1 o1 = new RefCountGCTest1();
        RefCountGCTest1 o2 = new RefCountGCTest1();

        // 将这2个对象分别引用对方
        o1.ref = o2;
        o2.ref = o1;

        // 进行GC，如果2个对象被GC了，证明不是使用引用计数算法。否则，则是。
        System.gc();

    }

}
