package com.xyb.a4factory.a4absfactory;

class FantaFactory implements AbsFactory {

    @Override
    public ADrink createADrink() {
        return new AFanta();
    }

    @Override
    public BDrink createBDrink() {
        return new BFanta();
    }
}
