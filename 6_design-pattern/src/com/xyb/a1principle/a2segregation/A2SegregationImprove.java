package com.xyb.a1principle.a2segregation;

/**
 * 接口隔离问题改进
 */
public class A2SegregationImprove {

    public static void main(String[] args) {
        A1 a1 = new A1();
        a1.depend1(new B1());
        a1.depend2(new B1());
        a1.depend3(new B1());

        C1 c1 = new C1();
        c1.depend1(new D1());
        c1.depend4(new D1());
        c1.depend5(new D1());
    }

}

interface Interface2 {
    void op1();
}

interface Interface3 {
    void op2();
    void op3();
}

interface Interface4 {
    void op4();
    void op5();
}

class B1 implements Interface2, Interface3 {

    @Override
    public void op1() {
        System.out.println("B1.op1()");
    }

    @Override
    public void op2() {
        System.out.println("B1.op2()");
    }

    @Override
    public void op3() {
        System.out.println("B1.op3()");
    }

}

class D1 implements Interface2, Interface4 {

    @Override
    public void op1() {
        System.out.println("D1.op1()");
    }

    @Override
    public void op4() {
        System.out.println("D1.op4()");
    }

    @Override
    public void op5() {
        System.out.println("D1.op5()");
    }
}

class A1 {
    // A类通过Interface1接口依赖(使用)B类，但只会用到1、2、3方法
    public void depend1(Interface2 interface2) {
        interface2.op1();
    }

    public void depend2(Interface3 interface3) {
        interface3.op2();
    }

    public void depend3(Interface3 interface3) {
        interface3.op3();
    }

}

class C1 {
    // C类通过Interface1接口依赖(使用)D类，但只会用到1、4、5方法
    public void depend1(Interface2 interface2) {
        interface2.op1();
    }

    public void depend4(Interface4 interface4) {
        interface4.op4();
    }

    public void depend5(Interface4 interface4) {
        interface4.op5();
    }

}


