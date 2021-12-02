package com.xyb.a8bridge;

/**
 * 桥接模式
 */
public class A1Bridge {

    public static void main(String[] args) {
        A1Phone a1Phone = new A1XiaoMiPhone(new A1YellowColor());
        a1Phone.open();
        a1Phone.call();
        a1Phone.close();

        System.out.println("-----------------");

        A1Phone a2Phone = new A1HuaWeiPhone(new A1RedColor());
        a2Phone.open();
        a2Phone.call();
        a2Phone.close();

    }

}


/**
 * 实现化角色，供抽象化角色调用，所以这个属性应该作为副属性，如"黄色手机"，手机是主体，颜色是副属性
 */
interface A1PhoneColor {
    public String getColor();
}

/**
 * 具体实现化：给出实现化角色的具体实现，这个维就可以扩展了。如增加颜色。
 */
class A1YellowColor implements A1PhoneColor {
    @Override
    public String getColor() {
        return "黄色";
    }
}

class A1RedColor implements A1PhoneColor {
    @Override
    public String getColor() {
        return "红色";
    }
}

/**
 * 抽象化角色，建议将主体作为抽象化角色，因为抽象化角色含有接口类角色的引用，方便操作。
 * 定义抽象类，并包含一个对实现化对象的引用
 */
abstract class A1Phone {
    protected A1PhoneColor a1PhoneColor;
    protected String PhoneType;

    protected A1Phone(A1PhoneColor a1PhoneColor) {
        this.a1PhoneColor = a1PhoneColor;
    }

    public abstract void open();
    public abstract void call();
    public abstract void close();
}

class A1XiaoMiPhone extends A1Phone {

    protected A1XiaoMiPhone(A1PhoneColor a1PhoneColor) {
        super(a1PhoneColor);
        this.PhoneType = "小米";
    }

    @Override
    public void open() {
        String color = this.a1PhoneColor.getColor(); // 此处调用实现化角色对应方法
        System.out.println(color + this.PhoneType + "正在开机。");
    }

    @Override
    public void call() {
        String color = this.a1PhoneColor.getColor(); // 此处调用实现化角色对应方法
        System.out.println(color + this.PhoneType + "正在打电话。");
    }

    @Override
    public void close() {
        String color = this.a1PhoneColor.getColor(); // 此处调用实现化角色对应方法
        System.out.println(color + this.PhoneType + "正在关机。");
    }
}

class A1HuaWeiPhone extends A1Phone {

    protected A1HuaWeiPhone(A1PhoneColor a1PhoneColor) {
        super(a1PhoneColor);
        this.PhoneType = "华为";
    }

    @Override
    public void open() {
        String color = this.a1PhoneColor.getColor(); // 此处调用实现化角色对应方法
        System.out.println(color + this.PhoneType + "正在开机。");
    }

    @Override
    public void call() {
        String color = this.a1PhoneColor.getColor(); // 此处调用实现化角色对应方法
        System.out.println(color + this.PhoneType + "正在打电话。");
    }

    @Override
    public void close() {
        String color = this.a1PhoneColor.getColor(); // 此处调用实现化角色对应方法
        System.out.println(color + this.PhoneType + "正在关机。");
    }
}
