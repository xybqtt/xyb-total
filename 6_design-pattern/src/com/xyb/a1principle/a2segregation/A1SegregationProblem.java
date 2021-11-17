package com.xyb.a1principle.a2segregation;

/**
 * 接口隔离原则：问题展示
 *      new A().f1(new B())、new C().f2(new D())，即A依赖B、C依赖D，且B、D实
 *      现了同一个接口I1(有5个方法)。
 *      但是A只使用了I1的1、2、3方法，C使用了1、4、5方法，那么B没必要实现4、5方法，
 *      D没必要实现2、3方法。
 *      按照接口隔离原则，应该把I1拆分为3个接口I2(1)、I3(2、3)、I4(4、5)
 *      且B implements I2, I3。D implement I2, I4。
 *
 */
public class A1SegregationProblem {

    public static void main(String[] args) {
        A a = new A();
        a.depend1(new B());
        a.depend2(new B());
        a.depend3(new B());

        C c = new C();
        c.depend1(new D());
        c.depend4(new D());
        c.depend5(new D());
    }

}

interface Interface1 {
    void op1();
    void op2();
    void op3();
    void op4();
    void op5();
}

class B implements Interface1 {

    @Override
    public void op1() {
        System.out.println("B.op1()");
    }

    @Override
    public void op2() {
        System.out.println("B.op2()");
    }

    @Override
    public void op3() {
        System.out.println("B.op3()");
    }

    @Override
    public void op4() {
        System.out.println("B.op4()");
    }

    @Override
    public void op5() {
        System.out.println("B.op5()");
    }
}

class D implements Interface1 {

    @Override
    public void op1() {
        System.out.println("D.op1()");
    }

    @Override
    public void op2() {
        System.out.println("D.op2()");
    }

    @Override
    public void op3() {
        System.out.println("D.op3()");
    }

    @Override
    public void op4() {
        System.out.println("D.op4()");
    }

    @Override
    public void op5() {
        System.out.println("D.op5()");
    }
}

class A {
    // A类通过Interface1接口依赖(使用)B类，但只会用到1、2、3方法
    public void depend1(Interface1 interface1) {
        interface1.op1();
    }

    public void depend2(Interface1 interface1) {
        interface1.op2();
    }

    public void depend3(Interface1 interface1) {
        interface1.op3();
    }

}

class C {
    // C类通过Interface1接口依赖(使用)D类，但只会用到1、4、5方法
    public void depend1(Interface1 interface1) {
        interface1.op1();
    }

    public void depend4(Interface1 interface1) {
        interface1.op4();
    }

    public void depend5(Interface1 interface1) {
        interface1.op5();
    }

}