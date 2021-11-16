package com.xyb.a12gcrelative;

/**
 * 测试被局部变量表引用的对象，何时可以被GC回收。
 */
public class A2LocalVarGC {

    /**
     * 一个方法一个方法测试
     * @param args
     */
    public static void main(String[] args) {
        f1();
//        f2();
//        f3();
//        f4();
//        f5();
    }

    public static void f1(){
        byte[] bytes = new byte[10 * 1024 * 1024]; // 10m
        System.gc(); // 在本方法内，10m大小不会被回收，因为bytes还在局部变量表中，数组也还在补引用
    }

    public static void f2(){
        byte[] bytes = new byte[10 * 1024 * 1024]; // 10m
        bytes = null;
        System.gc(); // 在本方法内，10m大小会被回收，因为bytes不再引用此数组
    }

    public static void f3(){
        {
            byte[] bytes = new byte[10 * 1024 * 1024]; // 10m
        }
        System.gc(); // 在本方法内，虽然出了bytes的作用域，但其所占slot依然没有被其它变量占用，且bytes依然引用数组，所以10m不会被gc
    }

    public static void f4(){
        {
            byte[] bytes = new byte[10 * 1024 * 1024]; // 10m
        }
        int value = 10;
        System.gc(); // 在本方法内，出了bytes的作用域，且其所占slot被value占据，数组失去被引用，GC回收。
    }

    public static void f5(){
        f1(); // f1在运行的时候，f1中的数组没有被GC
        System.gc(); // 本次GC运行时，f1中new的数组已经失去了引用，会被GC回收。
    }

}
