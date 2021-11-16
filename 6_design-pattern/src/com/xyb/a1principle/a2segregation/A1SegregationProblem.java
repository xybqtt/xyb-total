package com.xyb.a1principle.a2segregation;

/**
 * 接口隔离原则：问题展示
 *
 */
public class A1SegregationProblem {


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