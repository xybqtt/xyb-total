package com.xyb.a15classinstruction;

import java.io.File;

/**
 * 对象、数组创建与访问指令
 */
public class A4NewObjTest {

    /**
     * 创建对象指令
     */
    public static void newInstance() {
        Object obj = new Object();

        File file = new File("src\\com\\xyb\\a15classinstruction\\A4NewObjTest.java");
    }

    /**
     * 创建数组指令
     */
    public static void newArray() {

        int[] intArray = new int[10];
        Object[] objArr = new Object[10];
        int[][] intArrArr = new int[10][10];
        String[][] strArrs = new String[10][10];

    }

    /**
     * 字段访问指令
     */
    public static void sayHello() {
        System.out.println("hello");
    }

    public static void setOrderId() {

        Order order = new Order();
        order.id = 1001;
        System.out.println(order.id);

        order.name = "ORDER";
        System.out.println(order.name);

    }

}

class Order {

    public int id;
    public static String name;

}
