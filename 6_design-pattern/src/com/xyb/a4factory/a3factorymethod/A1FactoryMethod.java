package com.xyb.a4factory.a3factorymethod;


/**
 * 工厂方法模式
 */
class A1FactoryMethod {

    public static void main(String[] args) {
        // 先new子工厂，由具体的子工厂去创建具体的对象，而不是由DrinkFactory去new对象。
        DrinkFactory colaFactory = new ColaFactory();
        Drink cola = colaFactory.createDrink();
        System.out.println("这是" + cola.getDrinkName());

        DrinkFactory fantaFactory = new FantaFactory();
        Drink fanta = fantaFactory.createDrink();
        System.out.println("这是" + fanta.getDrinkName());

        // 当我们想再新增一种饮料时，新增一个AFactory extends DrinkFactory即可，实现了ocp

    }

}

