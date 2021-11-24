package com.xyb.a4factory.a4absfactory;

class ColaFactory implements AbsFactory {

    @Override
    public ADrink createADrink() {
        return new ACola();
    }

    @Override
    public BDrink createBDrink() {
        return new BCola();
    }
}
