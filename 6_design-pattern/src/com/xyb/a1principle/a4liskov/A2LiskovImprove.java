package com.xyb.a1principle.a4liskov;

/**
 * 里氏替换原则的问题介绍
 */
public class A2LiskovImprove {

    public static void main(String[] args) {
        A2 a2 = new A2();
        System.out.println("11 - 3 = " + a2.func1(11, 3));
        System.out.println("1 - 8 = " + a2.func1(1, 8));

        System.out.println("----------------");
        B2 b2 = new B2();
        /*
        下面2个本意是求出11 - 3和 1 - 8的值，但因为B重写了func1()方法，导致参数相加了。
        违背了里氏替换原则，即调用子类的相同方法的结果与调用父类相同方法的结果不一致了。
         */
        System.out.println("11 - 3 = " + b2.func1(11, 3));
        System.out.println("1 - 8 = " + b2.func1(1, 8));
        System.out.println("11 - 3 + 8 = " + b2.func2(1, 8));
    }

}

/**
 * 创建一个更基础的基类，
 */
class Base {
    public int func1(int a, int b) {
        return a - b;
    }
}

class A2 extends Base {
    public int func1(int a, int b) {
        return a - b;
    }
}

class B2 extends Base {

    // 如果B2需要使用到A2，则可以使用组合关系
    private A2 a2 = new A2();

    public int fun3(int a, int b) {
        return this.a2.func1(a, b);
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }
}
