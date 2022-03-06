package com.xyb.a2j8features.a1lambda;

/**
 * 方法引用测试：知道引用的方法了，就不用写参数了
 *      类::静态方法名
 *      对象::实例方法名
 *      类::实例方法名，本质和2是一样的。
 */
public class A3MethodRef {

    public static void main(String[] args) {
        System.out.println("使用Lambda表达式：");
        System.out.println("一、使用 \"对象::实例方法\"");

        MethodRefCls mcls = new MethodRefCls();
        MethodRef1 m1 = mcls::method1;
        m1.f1();
        System.out.println();

        System.out.println("二、使用 \"类::静态方法\"");
        MethodRef2 m2 = MethodRefCls::method2;
        System.out.println(m2.f2("5", 6));

        // 发现MethodRefCls没有method4静态方法时，就会查看有没有new MethodRefCls().method();方法。
        System.out.println();
        System.out.println("三、使用 \"类::实例方法\"");
        MethodRef3 m3 = MethodRefCls::method4;
        m3.f3(new MethodRefCls(), "a");

    }

}

@FunctionalInterface
interface MethodRef1 {
    public void f1();
}

@FunctionalInterface
interface MethodRef2 {
    public Object f2(String a, int b);
}

@FunctionalInterface
interface MethodRef3 {
    public Object f3(MethodRefCls a, String b);
}

class MethodRefCls {

    public static Object method2(String a, int b) {
        System.out.println("通过 类::静态方法名 调用");
        System.out.println(a);
        System.out.println(b);
        return a + b;
    }

    public void method1() {
        System.out.println("通过 对象::实例方法名 调用");
    }


    public Object method4(String b) {
        System.out.println("通过 类::实例方法名 调用");
        System.out.println(b);
        return b;
    }

}

