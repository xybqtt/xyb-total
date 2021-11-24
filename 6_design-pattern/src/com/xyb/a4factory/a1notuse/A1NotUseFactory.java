package com.xyb.a4factory.a1notuse;

/**
 * 不使用工厂模式：喝饮料，
 * 如下这种情况需要使用者自己去查找需要哪些参数，如果有一天new Cola需要的参数增加，那
 * 也得相应修改所有new Cola的地方。
 * 为了解决这个问题，我们可以让使用者直接拿到Cola，而不需要使用者知道这个可乐如何创建的。
 */
public class A1NotUseFactory {

    public static void main(String[] args) {

        Cola cola = new Cola("可乐糖", "水");
        System.out.println("正在喝" + cola.getName());

        Fanta fanta = new Fanta("芬达糖", "水", "CO2");
        System.out.println("正在喝" + fanta.getName());

    }

}

class Cola {

    public Cola(String ele1, String ele2) {

    }

    public String getName() {
        return "可乐";
    }

}

class Fanta {

    public Fanta(String ele1, String ele2, String ele3) {

    }

    public String getName() {
        return "芬达";
    }

}
