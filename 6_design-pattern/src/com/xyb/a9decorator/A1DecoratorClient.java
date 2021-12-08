package com.xyb.a9decorator;

/**
 * 装饰者模式，饮料加调味品计算价格
 */
public class A1DecoratorClient {

    public static void main(String[] args) {
        A1Drink a1Drink = new A1Chocolate(new A1Chocolate(new A1Coffee()));
        System.out.println("名称：" + a1Drink.getDrinkName() + "，价格：" + a1Drink.getPrice());

        a1Drink = new A1Chocolate(new A1Sugar(new A1Milk()));
        System.out.println("名称：" + a1Drink.getDrinkName() + "，价格：" + a1Drink.getPrice());
    }

}

interface A1Drink {
    public String getDrinkName();
    public double getPrice();
}

class A1Coffee implements A1Drink {
    @Override
    public String getDrinkName() {
        return "咖啡";
    }

    @Override
    public double getPrice() {
        return 5;
    }
}

class A1Milk implements A1Drink {
    @Override
    public String getDrinkName() {
        return "牛奶";
    }

    @Override
    public double getPrice() {
        return 3;
    }
}

abstract class A1Decorator implements A1Drink {
    protected String name;
    protected double price;
    protected A1Drink a1Drink;

    public A1Decorator(A1Drink a1Drink) {
        this.a1Drink = a1Drink;
    }

    public String getDrinkName() {
        return this.a1Drink.getDrinkName();
    }
    public double getPrice() {
        return this.a1Drink.getPrice();
    }
}

class A1Chocolate extends A1Decorator {

    public A1Chocolate(A1Drink a1Drink) {
        super(a1Drink);
        super.name = "巧克力";
        super.price = 2;
    }

    public String getDrinkName() {
        return super.getDrinkName() + "+" + super.name;
    }
    public double getPrice() {
        return super.a1Drink.getPrice() + super.price;
    }
}

class A1Sugar extends A1Decorator {

    public A1Sugar(A1Drink a1Drink) {
        super(a1Drink);
        super.name = "糖";
        super.price = 1;
    }

    public String getDrinkName() {
        return super.getDrinkName() + "+" + super.name;
    }
    public double getPrice() {
        return super.a1Drink.getPrice() + super.price;
    }
}