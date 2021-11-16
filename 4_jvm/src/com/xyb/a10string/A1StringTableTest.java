package com.xyb.a10string;

import java.util.Scanner;

/**
 * 测试StringTable字符串常量池数组的长度对性能的影响。
 * 分别在：
 *   221ms
 *  默认60013：51ms
 *
 */
public class A1StringTableTest {

    public static void main(String[] args) {

        // 1、测试"字符串常量池"大小对性能的影响
        performOfstrTableSize("");

        // 2、查看几种生成字符串的方式
        compareNewStr("");

        // 3、验证字符串何时会进入常量池
        verifyStrToConstantPool("-");

        // 4、比较字符串的拼接速度
        compareCombinSpeed("");

    }

    /**
     * 比较 + 和StringBuilder的速度：
     * 使用sb.append()的方式：自始至终只创建1个sb对象。使用字符串+的拼接方式，每次循环会创建1个sb和String对象。
     * 可以通过确定字符串最终长度的方式，给sb构造方法赋值，减少字符串扩容带来的复制时间。
     */
    private static void compareCombinSpeed(String showFlag) {
        if("".equals(showFlag))
            return;

        long startTime = System.currentTimeMillis();
        String a = "";
        for(int i = 0; i < 10000; i++){
            a += "a"; // 每次都要new StringBuilder()、再sb.toString()。
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用+拼接的时间：" + (endTime - startTime));

        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < 10000; i++){
            sb.append("a");
        }
        System.out.println("使用StringBuilder拼接的时间：" + (System.currentTimeMillis() - endTime));



    }

    /**
     * 比较几种生成字符串的方式，
     * 结果：
     *      1、字面量的拼接，编译器会优化为1个字符串，同样，被拼接的字面量不会在常量池出现；
     *      2、多个字符串拼接，只要有1个变量，则会调用StringBuilder和其append方法，最后再调用toString()方法，会触发其中字面量的ldc，但最后的toString()方法不会调用intern()，即
     *      多个字符串的拼接，不会进入字符串常量池
     *      3、new String：不会触发自身的ldc，但如果参数中有字面量，则会触发这个字面量的ldc。
     *      注意：new String(byte[], "utf-8")，通过这种形式的，不会查看字符串常量池
     *      注意用final修饰的字面量变量在编译期已经确定了，被当作常量；非字面量变量，即final String a = new String("a"); 需要运行期间确定，就不是常量了。
     * @param showFlag
     */
    private static void compareNewStr(String showFlag) {
        if("".equals(showFlag))
            return;

        // 查看下面几种生成字符串的方式的字节码，#n之类的全部用字符串替换

        /**
         *  ldc <1>
         *  astore_1
         */
        String str1 = "1";

        /**
         *  ldc <12>
         *  astore_2
         *  说明：像这样的全是由字面量相加的字符串，编译器会直接优化为 12
         */
        String str2 = "1" + "2";


        /**
         *  new #10 <java/lang/StringBuilder>
         *  dup
         *  invokespecial #11 <java/lang/StringBuilder.<init>>
         *  aload_1
         *  invokevirtual #12 <java/lang/StringBuilder.append>
         *  ldc <3>
         *  invokevirtual #12 <java/lang/StringBuilder.append>
         *  invokevirtual #14 <java/lang/StringBuilder.toString>
         *  astore_3
         *  说明：只要字符串是由多个字符串拼接成，且其中至少有1个变量，则会用StringBuilder拼接，并最终调用toString()
         */
        String str3 = str1 + "3";

        /**
         *  new #16 <java/lang/String>
         *  dup
         *  ldc <3>
         *  invokespecial #17 <java/lang/String.<init>>
         *  astore 6
         */
        String str6 = new String("3");

        /**
         * new #16 <java/lang/String>
         * dup
         * new #10 <java/lang/StringBuilder>
         * dup
         * invokespecial #11 <java/lang/StringBuilder.<init>>
         * ldc #18 <7>
         * invokevirtual #12 <java/lang/StringBuilder.append>
         * aload_1
         * invokevirtual #12 <java/lang/StringBuilder.append>
         * invokevirtual #14 <java/lang/StringBuilder.toString>
         * invokespecial #17 <java/lang/String.<init>>
         * astore 7
         * 说明：会先new String，再new StringBuilder，将字符串拼接，再toString()返回给new String()的参数。
         */
        String str7 = new String("7" + str1);

        String s3 = "javaeehadoop"; // 常量池
        System.out.println("验证被fianl修饰的变量是如何存储的：");
        final String a = "javaee";
        final String b = "hadoop";
        final String c = new String("hadoop"); // 这样的不是编译期确定的。
        String d = a + b;
        System.out.println(s3 == d);
        String e = a + c;
        System.out.println(s3 == e);
    }

    /**
     * 验证字符串何时会进入常量池。
     * 要想让字符串进入常量池，只有2种方法：
     *  1、调用str.intern()方法；
     *  2、运行时常量池的字符串，执行了ldc指令
     *      此类中所有的"字符串字面量"在编译时都会进入"class文件的常量池"；
     *      在将类装载到方法区时，所有的"字符串字面量"都会进入"方法区此class的运行时常量池"；
     *      按理说在resolve阶段，会将"字符串字面量"转换为字符串，存入"字符串常量池"，但是对于字符串，jvm进行了lazy加载，
     *      当执行ldc指令时，才会在堆中new String("字符串");，再将此对象的引用存入"字符串常量池"。
     *      注意，类似String a = "ab" + "cd"; 编译器会自动替换为String a = "abcd";，所以在"class文件常量池中"不会有ab字面量。
     */
    private static void verifyStrToConstantPool(String showFlag) {
        if("".equals(showFlag))
            return;

        Scanner scanner = new Scanner(System.in); // 从控制台输入，避免进行ldc操作进入字符串常量池
        // 1、验证intern() 输入aabbcc
        System.out.println("输入aabbcc");
        String str1 = scanner.nextLine().intern(); // 输入aabbcc，此时返回的是字符串常量池的引用
        String str2 = "aabbcc"; // 此时会先查看aabbcc在字符串常量池是否存在，如果存在同返回字符串常量池此字符串的引用
        System.out.println(str1 == str2);



        // 2、验证ldc
        System.out.println("输入abc");
        String str3 = "abc"; // 此时，会执行ldc指令，将此字面量的产生的字符串对象的引用存入"字符串常量池"。
        String str4 = "abc"; // 此时，会执行ldc指令，查看字符串常量池中有无abc，有则直接返回的此字符串的引用。
        System.out.println(str3 == str4);








    }

    /**
     * 1、测试"字符串常量池StringTable"的大小对性能的影响。
     * 即当table的size很大时，一直调用intern()，通过hash算法定位字符串位置时，
     * 此位置产生链表的可能性就小，产生链表的长度也会小，那我们遍历链表的时间就少，效率就高。
     *
     * 2、验证从"字符串常量池"取字符串的速率；
     *
     * 3、验证不从"字符串常量池"取字符串的速率。
     */
    private static void performOfstrTableSize(String showFlag) {
        if("".equals(showFlag))
            return;

        /**
         * 测试一直向"字符串常量池"插入字符串对象时，插入性能的影响。
         * 分别设置参数：
         * -XX:StringTableSize=10009(jdk8可设置的最小值)，这个值要较小于下面的插入的数量，为了测试"字符串常量池"产生链表，造成的影响。
         * -XX:StringTableSize=1000000下测试，这个值要大于下面的插入的数量，为了测试"字符串常量池"不产生链表，造成的影响。
         */
        int strNum = 500000;

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < strNum; i++)
            // 将字符串存入字符串常量池，当StringTableSize 较小时，每个位置产生的链表就会很大，先遍历链表查看有没有，就会影响效率
            new String(i + "").intern();

        long endTime = System.currentTimeMillis();
        System.out.println("存入字符串常量池所用时间：" + (endTime - startTime) + "ms");

        // 测试，从"字符串常量池"加载这些数据的时间
        startTime = System.currentTimeMillis();
        for(int i = 0; i < strNum; i++)
            new String(i + "");
        endTime = System.currentTimeMillis();
        System.out.println("new已经存在于'字符串常量池'的字符串的时间：" + (endTime - startTime) + "ms");

        // 测试，不在字符串常量池，new对象的时间
        startTime = System.currentTimeMillis();
        for(int i = 0; i < strNum; i++)
            new String(i + "a").intern();
        endTime = System.currentTimeMillis();
        System.out.println("new不存在于'字符串常量池'的字符串的时间：" + (endTime - startTime) + "ms");

    }

}
