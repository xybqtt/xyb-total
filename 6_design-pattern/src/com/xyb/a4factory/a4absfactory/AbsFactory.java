package com.xyb.a4factory.a4absfactory;

interface AbsFactory {
    public ADrink createADrink();
    public BDrink createBDrink();
}

class Test {

    public static void main(String[] args) {

        AbsFactory absFactory = new ColaFactory();
        ADrink aDrink = absFactory.createADrink();
        System.out.println("喝" + aDrink.getDrinkName());


        absFactory = new FantaFactory();
        BDrink bDrink = absFactory.createBDrink();
        System.out.println("喝" + bDrink.getDrinkName());

    }

}