package com.xyb.a23strategy;

public class A1StrategyClient {

    public static void main(String[] args) {
        A1Context context = new A1Context(new A1ConcreteStrategy1());
        context.handle();

        System.out.println("-------------------------");

        context = new A1Context(new A1ConcreteStrategy2());
        context.handle();
    }

}

class A1Context {
    private A1Strategy strategy;

    public A1Context(A1Strategy strategy) {
        this.strategy = strategy;
    }

    public void handle() {
        this.strategy.strategyMethod();
    }

    public void handle(A1Strategy strategy) {
        strategy.strategyMethod();
    }


}

interface A1Strategy {
    public void strategyMethod();
}

class A1ConcreteStrategy1 implements A1Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("调用" + this + "处理");
    }
}

class A1ConcreteStrategy2 implements A1Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("调用" + this + "处理");
    }
}
