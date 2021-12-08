package com.xyb.a11facade;

/**
 * 外观模式，用红警举例，让一队队列进行行进、停止、射击
 */
public class A1FacadeDemo {

    public static void main(String[] args) {
        A1Facade a1Facade = new A1Facade();
        a1Facade.fire();
        System.out.println("-----------------------");
        a1Facade.move();
        System.out.println("-----------------------");
        a1Facade.stop();
    }

}


class A1Facade {

    private A1ApocalypseTank a1ApocalypseTank = new A1ApocalypseTank();
    private A1AmericanSoldier a1AmericanSoldier = new A1AmericanSoldier();


    public void move() {
        this.a1ApocalypseTank.move1();
        this.a1AmericanSoldier.move2();
    }

    public void stop() {
        this.a1ApocalypseTank.stop1();
        this.a1AmericanSoldier.stop2();
    }

    public void fire() {
        this.a1ApocalypseTank.fire1();
        this.a1AmericanSoldier.fire2();
    }

}

class A1ApocalypseTank {
    private String name = "天启坦克";

    public void move1() {
        System.out.println(this.name + "移动");
    }

    public void stop1() {
        System.out.println(this.name + "停止");
    }

    public void fire1() {
        System.out.println(this.name + "射击");
    }
}

class A1AmericanSoldier {
    private String name = "美国大兵";

    public void move2() {
        System.out.println(this.name + "移动");
    }

    public void stop2() {
        System.out.println(this.name + "停止");
    }

    public void fire2() {
        System.out.println(this.name + "射击");
    }
}

