package com.xyb.a4factory.a2simple;

/**
 * 简单工厂模式(静态工厂模式)，
 * 这种情况，我们就不用关心new cola的时候，参数有没有增加或减少，直接获取Cola就行，
 * 但是也有问题，比如又新增一种产品，如"冰红茶"，那需要如下步骤：
 *      1、新增class IceBlackTea，必须步骤；
 *      2、在DrinkFactory新增一个case；
 * 这样违反了ocp原则，所以接着向下改进。
 */
class A1SimpleFactory {

    public static void main(String[] args) {

        Drink drink = DrinkFactory.getInstance("cola");
        System.out.println("创建的可乐：" + drink.getName());

        drink = DrinkFactory.getInstance("fanta");
        System.out.println("创建的芬达：" + drink.getName());


    }

}


