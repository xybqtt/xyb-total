package com.xyb.a1principle.a1singleresponsibility;

/**
 * 单一职责原则
 *
 * 交通工具类，方案1
 *      1、在run方法中违反了单一职责原则；
 *      2、解决方法是拆分为多个类，根据交通工具运行方法不同，分解为不同的类 => 方案2"A2SingleRespon2"
 *
 */
public class A1SingleResponsibility {

    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        vehicle.run("飞机");

    }

}

class Vehicle {

    public void run(String vehicle) {
        System.out.println(vehicle + " 在公路上跑....");
    }

}
