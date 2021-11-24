package com.xyb.a4factory.a2simple;

class DrinkFactory {

    public static Drink getInstance(String drinkName) {

        switch (drinkName) {
            case "cola":
                return new Cola();
            case "fanta":
                return new Fanta();
            default:
                throw new RuntimeException("不知名饮料");
        }

    }

}
