package com.xyb.a4factory.a3factorymethod;

class FantaFactory implements DrinkFactory {
    @Override
    public Drink createDrink() {
        return new Fanta();
    }
}
