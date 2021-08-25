package com.xyb.a16detailclsloader;

import java.io.*;

/**
 * 类的主动使用：
 * 1、实例化：当创建一个类的实例时，比如使用new关键字，或者通过反射、克隆、反序列化。
 * 2、静态方法：当调用类的静态方法时，即当使用了字节码invokestatic指令。
 * 3、静态字段：当使用类、接口的静态字段时（final修饰特殊考虑），比如，使用getstatic或者putstatic指令。（对应访问变量、赋值变量操作）
 */
public class A4ActiveUse {

    // 6、
    static {
        System.out.println("调用main方法会初始化类");
    }

    /**
     * 类的主动使用测试，每次测试时，需要关闭其它主动使用类的情况
     *
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // 1、实例化：当创建一个类的实例时，比如使用new关键字，或者通过反射、克隆、反序列化。
        Order order = new Order();

        // 2、静态方法：当调用类的静态方法时，即当使用了字节码invokestatic指令。
        Order.method();

        // 3、使用或设置接口或类的静态字段
        System.out.println(Order.str);
        Order.str = "123";

        // 4、初始化其子类的时候
        Order2 order2 = new Order2();

        // 5、反射
        Class cls = Class.forName("com.xyb.a16detailclsloader.Order");
    }

}

class Order implements Serializable {

    public static String str = "abc";

    static {
        System.out.println("Order类的初始化");
    }

    public static void method() {
        System.out.println("Order的static method");
    }
}

class Order2 extends Order {

    static {
        System.out.println("Order2类的初始化");
    }

}