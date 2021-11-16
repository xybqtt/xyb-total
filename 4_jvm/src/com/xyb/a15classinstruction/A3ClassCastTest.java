package com.xyb.a15classinstruction;

/**
 * 类型转换指令
 */
public class A3ClassCastTest {

    public static void main(String[] args) {
        upCast2();
    }

    // 宽化类型转换
    public static void upCast1() {
        // int转换为long、float、double
        int i = 10;
        long l = i;
        float f = i;
        double d = i;

        // long转换为float、double
        float f1 = l;
        double d1 = l;

        // float转换为double
        double d2 = f1;
    }

    /**
     * int、long转float、double的精度损失问题
     */
    public static void upCast2() {
        int i = 123123123;
        float f = i;
        System.out.println(f);

        long l = 123123123123123123L;
        double d = l;
        System.out.println(d);
    }

    /**
     * 针对byte、short等转换为容量大的类型时，将此类型当作int处理。
     */
    public static void upCast3() {
        byte b = 5;
        int i = b; // 实际并没有进行b2i操作，只是把b的数转移到的i中了。
        long l = b;
        double d = b;

        short s = 5;
        int i1 = s;
        long l1 = s;
        double d1 = s;
    }


    // 窄化类型转换
    // 基本使用
    public static void downCast1() {

        int i = 10;
        byte b = (byte)i;
        short s = (short)i;
        char c = (char)i;

        long l = 10L;
        i = (int)l;
        b = (byte)l;

    }

    public static void downCast2() {

        float f = 10f;
        long l = (long)f;
        int i = (int)f;
        byte b = (byte)f;

        double d = 10.0;
        b = (byte)d;

    }

    public static void downCast3() {

        short s = 10;
        byte b = (byte)s; // 直接i2b

    }

    /**
     * 窄化类型转换的精度损失
     */
    public static void downCast4() {

        int i = 128;
        byte b = (byte)i;
        System.out.println(b);

    }

    /**
     * 测试Nan、无穷大的情况
     */
    public static void downCast5() {

        double d1 = Double.NaN;
        int i = (int)d1;
        System.out.println(i);

        double d2 = Double.POSITIVE_INFINITY;
        long l = (long)d2;
        int j = (int)d2;
        System.out.println(l);
        System.out.println(j);

        float f = (float)d2;
        System.out.println(f);
    }

}
