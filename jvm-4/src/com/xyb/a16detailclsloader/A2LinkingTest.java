package com.xyb.a16detailclsloader;

/**
 * 过程2：链接阶段
 * 基本数据类型：
 *  非final修饰的静态变量，在准备环节进行默认初始化赋值
 *  final修饰后，在准备环节直接进行显式赋值
 *  如果使用字面量的方式定义一个字符串常量的话，也是在准备环境进行初始化赋值。
 */
public class A2LinkingTest {

    private static long id; // 在准备环境默认初始化赋值 0l

    private static final int NUM = 1; // 编译期确定，在准备阶段显式赋值 1

    public static final String constStr = "const"; // 编译期确定，在准备阶段显式赋值

    public static final String str2 = new String("abc"); // 在初始化阶段赋值




}
