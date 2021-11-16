package com.xyb.basic1;

/**
1、变量： 数据类型 变量名 = 变量值；
概念：
    内存中的一个存储区域；
    该区域的数据可以在同一类型范围内不断变化；
    是程序中最基本的存储单元。包含"变量类型、变量名、存储的值"。
作用：
    在内存中保存数据；
注意：
    先声明且赋值后才能使用；
    使用变量名来访问区域的数据；
    作用域一一对{}内；
    变量只有在其作用域内才有效；
    同一作用域不能定义重名变量。

2、数据类型：
    基本类型：
    1)整型：byte、short、int、long；
    2)浮点型：float、double；
    3)字符型：char；
    4)布尔型：boolean；

    引用类型：
    1)class；
    2)interface；
    3)array。

 */
public class Variable1 {

    public static void main(String[] args) {

        basicTypeUse();
        basicTypeTrans();
        StringUse();



    }

    /**
     * String引用类型的使用：
     *      1、可以和基本类型做 "+" 运算
     */
    private static void StringUse() {

        String s1 = "Hello world";

        String s2 = new String("abc");

    }

    /**
     * 基本类型转换：
     *  1、自动类型提升：
     *      byte、short、int、long、float、double的容量一个比一个大，当容量小的数值与容量大的数值进行运算时，最少都需要使用容量大的类型去接收。
     *      byte、short、char进行运算时，是先将数据转换为int，即当作int来计算，所以最终的结果最少是int型。因为java在做运算的时候，如果操作
     *      数均在int范围内，那么一律按int空间内运算。
     *      容量不是指字节数，是指能表示
     *  的最大值。
     *  还有注意char类型，
     *  2、强制类型转换：将字符数多的类型转换为字节数少的类型，会损失精度。大转小，需要强转。
     */
    private static void basicTypeTrans() {

        // 自动类型提升
        int a = (byte)1 + (byte)2;

        // 强制类型转换
        double d1 = 5.5;
        a = (int)d1;

    }

    /**
     * 基本类型使用
     */
    private static void basicTypeUse() {

        // 基本类型变量使用
        byte b = 127;
        short s = 32665;
        int i = 1;
        /*
            以l或L结尾，则jvm认为此数是个long类型；如果不以f或F结尾，则jvm认为此数是个int，再赋值给long，此时的赋值不能超过int最大值。
            小转大可以自动转
         */
        long l = 1;


        // 浮点型
        float f1 = 3.14f; // 后面加必须加f或F；不加的话，默认有小数的double，double转float为大转小，需要强转。
        double d1 = 3.14d; // 小数如果不加d、D、f、F，则默认是double

        // 字符型 char(2字节)，包含65536个unicode字符，也可以声明转义字符，类似\n
        char c1 = 'a';
        char c2 = '\n';
        char c3 = '\u0123'; // 数字是16进制的


        // 布尔型
        boolean bo1 = true;


        // 2、8、16进制表示
        int a = 0b110; // 2进制，以0b或0B开头，jdk5后可以使用
        a = 110;
        a = 0110; // 8进制，以0开头
        a = 0xaf; // 16进制，以0x或0X开头



    }


}



















