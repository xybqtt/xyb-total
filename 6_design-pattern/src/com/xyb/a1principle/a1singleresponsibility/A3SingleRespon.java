package com.xyb.a1principle.a1singleresponsibility;

/**
 * 单一职责原则
 *
 * 交通工具类，方案3
 *      1、这种修改方法没有对原来的类做大的修改，只是增加方法；
 *      2、这里虽然没有在类上遵守"单一职责原则"，但是在方法级别上，仍然遵守单一职责原则。
 *
 */
public class A3SingleRespon {

    public static void main(String[] args) {

        Vehicle2 vehicle2 = new Vehicle2();
        vehicle2.run("汽车");
        vehicle2.runAir("飞机");
        vehicle2.runWater("船");

    }

}

class Vehicle2 {

    public void run(String vehicle) {
        System.out.println(vehicle + " 在公路上跑....");
    }

    public void runAir(String vehicle) {
        System.out.println(vehicle + " 在天上飞....");
    }

    public void runWater(String vehicle) {
        System.out.println(vehicle + " 在水上跑....");
    }

}