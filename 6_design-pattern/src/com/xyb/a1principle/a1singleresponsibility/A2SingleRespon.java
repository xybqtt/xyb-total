package com.xyb.a1principle.a1singleresponsibility;

/**
 * 单一职责原则
 * 交通工具类，方案2：
 *      1、遵守单一职责原则；
 *      2、但改动大，即将类分解，同时修改客户端；
 *      3、改进：直接修改Vehicle类，这样修改的代码会比较少 => 方案3"A3SingleRespon"。
 */
public class A2SingleRespon {

    public static void main(String[] args) {

        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("摩托车");
        roadVehicle.run("汽车");

        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
    }

}

class RoadVehicle {

    public void run(String vehicle) {
        System.out.println(vehicle + " 在公路上跑");
    }

}

class AirVehicle {

    public void run(String vehicle) {
        System.out.println(vehicle + " 在天上飞");
    }

}

class DriveVehicle {

    public void run(String vehicle) {
        System.out.println(vehicle + " 在公路上跑");
    }

}