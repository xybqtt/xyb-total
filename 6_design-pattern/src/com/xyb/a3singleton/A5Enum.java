package com.xyb.a3singleton;

/**
 * 单例模式：枚举类方式
 */
public class A5Enum {

    public static void main(String[] args) {
        A5Singleton a1 = A5Singleton.INSTANCE;
        A5Singleton a2 = A5Singleton.INSTANCE;

        System.out.println(a1 == a2);

        Runtime runtime = Runtime.getRuntime();
    }


}

enum A5Singleton {
    INSTANCE; // 属性

    public void sayOK() {
        System.out.println("ok");
    }

}
