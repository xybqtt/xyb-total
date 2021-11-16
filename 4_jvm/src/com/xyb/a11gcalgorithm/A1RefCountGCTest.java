package com.xyb.a11gcalgorithm;

/**
 * 测试java不是使用的引用计数算法
 * -XX:+PrintGCDetails
 */
public class A1RefCountGCTest {

    private byte[] bigSize = new byte[5 * 1024 * 1024];

    Object ref = null;

    public static void main(String[] args) {
        A1RefCountGCTest o1 = new A1RefCountGCTest();
        A1RefCountGCTest o2 = new A1RefCountGCTest();

        // 将这2个对象分别引用对方
        o1.ref = o2;
        o2.ref = o1;

        // 进行GC，如果2个对象被GC了，证明不是使用引用计数算法。否则，则是。
        System.gc();

    }

}
