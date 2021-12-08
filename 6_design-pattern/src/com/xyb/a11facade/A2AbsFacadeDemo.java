package com.xyb.a11facade;

/**
 * 外观模式，用红警举例，分别让一坦克进行行进、停止、射击，让一队士兵进行同样操作。
 * 当新增子系统时，可能通过添加新的Facade实现来扩展，而不用通过修改原有的Facade实现。
 */
public class A2AbsFacadeDemo {

    public static void main(String[] args) {
        A2AbsFacade a2AbsFacade = new A2SoliderFacade();
        a2AbsFacade.fire();
        System.out.println("-----------------------");
        a2AbsFacade.move();
        System.out.println("-----------------------");
        a2AbsFacade.stop();

        System.out.println("------------------------------------------------");
        A2AbsFacade a3AbsFacade = new A2TankFacade();
        a3AbsFacade.fire();
        System.out.println("-----------------------");
        a3AbsFacade.move();
        System.out.println("-----------------------");
        a3AbsFacade.stop();
    }

}

abstract class A2AbsFacade {

    public abstract void move();
    public abstract void fire();
    public abstract void stop();

}

class A2SoliderFacade extends A2AbsFacade {

    private A2ChineseSoldier a2ChineseSoldier = new A2ChineseSoldier();
    private A2AmericanSoldier a2AmericanSoldier = new A2AmericanSoldier();


    public void move() {
        this.a2ChineseSoldier.move2();
        this.a2AmericanSoldier.move2();
    }

    public void stop() {
        this.a2ChineseSoldier.stop2();
        this.a2AmericanSoldier.stop2();
    }

    public void fire() {
        this.a2ChineseSoldier.fire2();
        this.a2AmericanSoldier.fire2();
    }
}

class A2TankFacade extends A2AbsFacade {

    private A2GrizzlyTank a2GrizzlyTank = new A2GrizzlyTank();
    private A2ApocalypseTank a2ApocalypseTank = new A2ApocalypseTank();


    public void move() {
        this.a2GrizzlyTank.move1();
        this.a2ApocalypseTank.move1();
    }

    public void stop() {
        this.a2GrizzlyTank.stop1();
        this.a2ApocalypseTank.stop1();
    }

    public void fire() {
        this.a2GrizzlyTank.fire1();
        this.a2ApocalypseTank.fire1();
    }
}

class A2ApocalypseTank {
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

class A2GrizzlyTank {
    private String name = "灰熊坦克";

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

class A2ChineseSoldier {
    private String name = "中国解放军";

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

class A2AmericanSoldier {
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

