package com.xyb.a15classinstruction;

/**
 * 算术指令
 */
public class A2ArithmeticTest {

    public static void main(String[] args) {
        method6();
    }

    public static void method1() {

        int i = 10;
        double j = i / 0.0;
        System.out.println(j); // 结果是无穷大Infinity

        double d1 = 0.0;
        double d2 = d1 / 0.0;
        System.out.println(d2); // NaN

    }

    public static void method2() {
        float f1 = 10;
        float f2 = -f1;
        f1 = -f2;
    }

    public static void method3() {
        int i = 100;
        i = i + 10; // 是先将i和10从加载到操作数据栈，求和，结果压栈，再存储到局部变量表
        i += 10; // 使用的是自增
    }

    public static int method4() {
        int a = 80;
        int b = 7;
        int c = 10;
        return (a + b) * c;
    }

    public static int method5(int i, int j) {
        return ((i + j - 1) & ~(j - 1));
    }

    /**
     * 前++和后++的区别
     */
    public static void method6() {
        // 如果只进行了++操作，而没有进行其它操作，其实i++和++i是一样的。
        int i = 10;
        System.out.println(i++);
        System.out.println(++i);

        // 如果进行了其它操作，则i++和++i是不一样的，假设i = 5
        i = 5;
        int a = i++; // 取i压栈(i栈 = 5)，局部变量表的i + 1(i局 = 6)，出栈存a(a局 = i栈 = 5)。
        System.out.println("i++ = " + a);

        i = 5;
        int b = ++i; // 局部变量表的i + 1(i局 = i局 + 1 = 6)，i局压栈：i栈 = 6，i栈出栈存a = i栈 = 6；
        System.out.println("++i = " + b);

        // 思考下下面这个输出的i是几
        i = 10;
        i = i++;
        System.out.println(i);
    }


}
