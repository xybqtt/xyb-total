package com.xyb.a1principle.a4liskov;

/**
 * 里氏替换原则的问题介绍
 */
public class A1Liskov {

    public static void main(String[] args) {
        A a = new A();
        System.out.println("11 - 3 = " + a.func1(11, 3));
        System.out.println("1 - 8 = " + a.func1(1, 8));

        System.out.println("----------------");
        B b = new B();
        /*
        下面2个本意是求出11 - 3和 1 - 8的值，但因为B重写了func1()方法，导致参数相加了。
        违背了里氏替换原则，即调用子类的相同方法的结果与调用父类相同方法的结果不一致了。
         */
        System.out.println("11 - 3 = " + b.func1(11, 3));
        System.out.println("1 - 8 = " + b.func1(1, 8));
        System.out.println("11 - 3 + 8 = " + b.func2(1, 8));
    }

}

class A {
    public int func1(int a, int b) {
        return a - b;
    }
}

class B extends A {
    /**
     * 这里重写了A类方法，可以是无意识修改的
     * @param a
     * @param b
     * @return
     */
    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }
}
