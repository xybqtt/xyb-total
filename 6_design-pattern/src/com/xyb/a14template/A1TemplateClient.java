package com.xyb.a14template;

/**
 * 模板方法
 */
public class A1TemplateClient {

    public static void main(String[] args) {
        A1AbsTemplate a1 = new A1ConcreteCls1();
        a1.templateMethod();

        System.out.println("---------------------------");

        A1AbsTemplate a2 = new A1ConcreteCls2();
        a2.templateMethod();
    }

}

abstract class A1AbsTemplate {

    /**
     * 模板方法，确定调用方法的顺序
     */
    public void templateMethod() {
        if (hookSpecificMethod())
            specificMethod();
        absMethod1();
        absMethod2();
    }

    /**
     * 钩子方法，可以决定是否调用specificMethod()
     */
    public boolean hookSpecificMethod() {
        return true;
    }

    public final void specificMethod() {
        System.out.println("抽象类中的具体方法被调用...");
    }

    public abstract void absMethod1();

    public abstract void absMethod2();
}

class A1ConcreteCls1 extends A1AbsTemplate {

    /**
     * 子类重写钩子方法，决定不调用specificMethod()
     */
    public boolean hookSpecificMethod() {
        return false;
    }

    @Override
    public void absMethod1() {
        System.out.println("调用A1ConcreteCls1.absMethod1()");
    }

    @Override
    public void absMethod2() {
        System.out.println("调用A1ConcreteCls1.absMethod2()");
    }
}

class A1ConcreteCls2 extends A1AbsTemplate {

    @Override
    public void absMethod1() {
        System.out.println("调用A1ConcreteCls2.absMethod1()");
    }

    @Override
    public void absMethod2() {
        System.out.println("调用A1ConcreteCls2.absMethod2()");
    }
}
