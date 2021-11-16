package com.xyb.basic1;

/**
 * 运算符
 * 运算符有：+、-、*、/、%、++、--。
 * 1、注意int类型的除法，会只有整数，如果想要小数，需要在计算前把其中最少一个转换为double。
 * 2、取余%
 * 3、a++(先运算，再++)、和++a(先++，再运算)，++不会改变数据类型。--同理。
 */
public class Arithmetic2 {

    public static void main(String[] args) {

        useArith();
        assignment();
        compare();
        logical();
        bitOperator();

        label:
        for(int i = 0; i < 10; i++){

            break label;

        }

    }

    /**
     * 位运算符：<<左移、>>右移、>>>无符号右移、&与、|或、!非、^异或、~取反
     * >>、<<：右移的话，最高位拿原来的最高位补，即不改变原来的符号。
     * >>>：最高位拿0补。
     * &、|、!、^：对2个数的相同位数上的位进行相关运算，并得到结果。
     * ^：a ^ b = c，则a、b、c任意2个进行^，都会得到剩下的一个结果。
     * 位运算是对数的每一位进行运算。
     * 注意：位运算符操作的都是整型的数据。
     */
    private static void bitOperator() {

        int a = 3 << 2; // 12
        a = -3 >> 1; // -2，a = floor(-3 / 2) = -2
        System.out.println(a);
        a = 3 >>> 1; // 1，a = 3 / 2 = 1
        a = 6 & 3; // 2， 0110 & 0011 = 0010
        a = 6 | 3; // 7
        a = 6 ^ 3; // 5
        a = ~6; // -7， 0110 取反为 1001


    }

    /**
     * 逻辑运算符：&与、|或、!非、&&短路与、||短路或、^异或，参与逻辑运算的都是boolean类型
     * &&：有一个为false，则结果为false；&与&&结果相同，只不过&&在第1个运行为false时，不会再去判断后面那个是否为true了。
     * ||：有一个为true，则结果为true；
     * ^：2个boolean，相同为假，相反为真
     */
    private static void logical() {

        System.out.println("异或：" + (true ^ false));

    }

    /**
     * 比较运算符：==、!=、<、>、<=、>=、instanceof。
     * 没什么可写的。
     */
    private static void compare() {
    }

    /**
     * 赋值运算符的练习：=、+=、-=、*=、、=、%=。
     * 这些赋值运算依然不会改变原本的数据类型。
     *
     */
    private static void assignment() {
        int a = 10;
        a += 2; // a = 12，其它类似

        short s = 1;
        s += 1; // 不会改变s的数据类型

    }

    /**
     * 算术运算符的练习
     * 运算符有：+、-、*、/、%、++、--。
     * 1、注意int类型的除法，会只有整数，如果想要小数，需要在计算前把其中最少一个转换为double。
     * 2、取余%
     * 3、a++(先运算，再++)、和++a(先++，再运算)，++不会改变数据类型。--同理。
     */
    private static void useArith() {

        System.out.println("1111");
        // 注意int类型的除法，会只有整数，如果想要小数，需要在计算前把其中最少一个转换为double。
        double d1 = (double)5 / 2; // 2.5
        d1 = 5 / 2; // 2.0，此处不是2.5，是因为2个int先计算完后是2，再转换成了double。


        // 取余%
        int i = 12 % 7; // 5

        // a++、和++a，++不会改变数据类型。--同理。
        int a = 10;
        int b = a++; // a = 11， b = 10
        System.out.println(a + "-" + b);
        b = ++a; // a = 12, b = 12
        System.out.println(a + "-" + b);

        short s1 = 1;
        s1++; // ++不会改变数据类型，比s1 = (short)(s1 + 1)速度快，因为不用改变类型。

    }


}
