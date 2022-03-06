package com.xyb.a2j8features.a1lambda;

import java.util.Arrays;

/**
 * 数组引用，和构造器引用类，只用传个数组元素个数就行。
 *      interface：ArrayCls[] f1(int i);
 *      Array[] array[](int i)
 */
public class A5ArrayRef {

    public static void main(String[] args) {
        ICreateArray i1 = ArrayCls[]::new;

        System.out.println("数组引用演示：");
        ArrayCls[] a = i1.f1(5);
        System.out.println(Arrays.toString(a));
    }

}

class ArrayCls {

    private int a;

    private String b;

    public ArrayCls() {
    }

    @Override
    public String toString() {
        return "ConstructCls{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }
}

interface ICreateArray {
    public ArrayCls[] f1(int i);
}