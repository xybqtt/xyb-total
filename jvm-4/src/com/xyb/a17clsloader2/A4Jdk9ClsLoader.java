package com.xyb.a17clsloader2;

/**
 * jdk9的类加载器相对变化很多，需要使用jdk9去运行
 */
public class A4Jdk9ClsLoader {

    public static void main(String[] args) {
        System.out.println(A4Jdk9ClsLoader.class.getClassLoader());
        System.out.println(A4Jdk9ClsLoader.class.getClassLoader().getParent());
        System.out.println(A4Jdk9ClsLoader.class.getClassLoader().getParent().getParent());

        // 获取系统类加载器
        System.out.println(ClassLoader.getSystemClassLoader());

        // 获取平台类加载器
//        System.out.println(ClassLoader.getPlatformClassLoader());

        // 获取当前类加载器的名称：系统类加载器为app
//        System.out.println(A4Jdk9ClsLoader.class.getClassLoader().getName());
    }

}
