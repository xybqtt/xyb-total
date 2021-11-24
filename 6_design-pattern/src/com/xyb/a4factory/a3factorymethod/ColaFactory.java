package com.xyb.a4factory.a3factorymethod;

class ColaFactory implements DrinkFactory {
    @Override
    public Drink createDrink() {
        return new Cola();
    }
}
