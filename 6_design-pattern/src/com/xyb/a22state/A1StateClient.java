package com.xyb.a22state;

/**
 * 状态模式
 */
public class A1StateClient {

    public static void main(String[] args) {
        A1Context context = new A1Context(new A1ConcreteState1());
        context.handle();
        System.out.println();
        context.handle();
    }

}

class A1Context {

    private A1State state;

    public A1Context(A1State a1State) {
        this.state = a1State;
    }

    public A1State getState() {
        return state;
    }

    public void setState(A1State state) {
        this.state = state;
    }

    public void handle() {
        this.state.handle(this);
    }
}

abstract class A1State {
    public abstract void handle(A1Context context);
}

class A1ConcreteState1 extends A1State {

    @Override
    public void handle(A1Context context) {
        System.out.println("当前context状态为：A1ConcreteState1");
        context.setState(new A1ConcreteState2());
        System.out.println("修改后context状态为：A1ConcreteState2");

    }
}

class A1ConcreteState2 extends A1State {

    @Override
    public void handle(A1Context context) {
        System.out.println("当前context状态为：A1ConcreteState2");
        context.setState(new A1ConcreteState1());
        System.out.println("修改后context状态为：A1ConcreteState1");
    }
}
