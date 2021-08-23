package com.xyb.a16detailclsloader;

import java.util.Random;

/**
 * 过程三：初始化阶段
 */
public class A3InitializationTest {

    public static int id = 1;

    public static int number;

    public static final int INT_CONSTANT = 10;                                // 在链接阶段的准备环节赋值
    public static final int NUM1 = new Random().nextInt(10);           // 在初始化阶段clinit>()中赋值
    public static int a = 1;                                                  // 在初始化阶段<clinit>()中赋值

    public static final Integer INTEGER_CONSTANT1 = Integer.valueOf(100);     // 在初始化阶段<clinit>()中赋值
    public static Integer INTEGER_CONSTANT2 = Integer.valueOf(100);           // 在初始化阶段<clinit>()中概值

    public static final String s0 = "helloworld0";                            // 在链接阶段的准备环节赋值
    public static final String s1 = new String("helloworld1");        // 在初始化阶段<clinit>()中赋值
    public static String s2 = "hellowrold2";                                  // 在初始化阶段<clinit>()中赋值

    static {
        number = 2;
        System.out.println("fff");
    }

}

/**
 * 不会生成<clinit>()方法的情况
 */
class InitializetionTest2 {

    // 1、没有静态变量
    public int a;

    // 2、有静态变量，但没有显式赋值和使用静态代码块赋值
    public static String b;

    // 3、静态变量，且为final，且为基本类型
    public static final int c = 5;

}
