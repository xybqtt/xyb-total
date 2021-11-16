package com.xyb.a15classinstruction;

import java.util.Date;

public class A1LoadAndStoreTest {

    /**
     * 1、局部变量压栈指令
     * @param num
     * @param obj
     * @param count
     * @param flag
     * @param arr
     */
    public void load(int num, Object obj, long count, boolean flag, short[] arr) {
        System.out.println(num);
        System.out.println(obj);
        System.out.println(count);
        System.out.println(flag);
        System.out.println(arr);
    }

    /**
     * 2、常量入栈指令
     */
    public void pushConstLdc() {
        int i = -1; // iconst_m1 iconst_n中n为-1 ~ 5
        int a = 5; // iconst_5
        int b = 6; // bipush 6 超过5的int常量，需要用bipush，范围为 5 - 127
        int c = 127; // bipush 127
        int d = 128; //sipush 128 sipush范围 128 ~ 32767
        int e = 32767; // sipush 32767
        int f = 32768; // ldc #7
    }

    /**
     * 测试long、float、double的常量入栈
     */
    public void constLdc() {
        long a1 = 1;
        long a2 = 2;
        float f1 = 2;
        float f2 = 3;
        double d1 = 1;
        double d2 = 2;
        Date date = null;
    }

    /**
     * 测试出栈装入局部变量表指令
     * @param k
     * @param d
     */
    public void store(int k, double d) {
        int m = k + 2;
        long l = 12;
        String str = "aaa";
        float f = 10.0f;
        d = 10;
    }

    /**
     * 查看slot复用
     * @param l
     * @param f
     */
    public void foo(long l, float f) {
        {
            int i = 0;
        }
        {
            String s = "abc";
        }
    }
}
