package com.xyb.a16detailclsloader;

import java.io.*;

/**
 * 类的主动使用：
 * 1、实例化：当创建一个类的实例时，比如使用new关键字，或者通过反射、克隆、反序列化。
 * 2、静态方法：当调用类的静态方法时，即当使用了字节码invokestatic指令。
 * 3、静态字段：当使用类、接口的静态字段时（final修饰特殊考虑），比如，使用getstatic或者putstatic指令。（对应访问变量、赋值变量操作）
 */
public class A4ActiveUse {

    /**
     * 类的主动使用测试，每次测试时，需要关闭其它主动使用类的情况
     *
     * @param args
     */
    public static void main(String[] args) {
        // 1、实例化：当创建一个类的实例时，比如使用new关键字，或者通过反射、克隆、反序列化。
        Order order = new Order();

        // 2、静态方法：当调用类的静态方法时，即当使用了字节码invokestatic指令。
        Order.method();
    }

}

class Order implements Serializable {
    static {
        System.out.println("Order类的初始化");
    }

    public static void method() {
        System.out.println("Order的static method");
    }
}