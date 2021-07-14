package com.xyb.string10;

public class StringOpera3 {

    public static void main(String[] args) {

        // 1、验证字面量的拼接
        verifyConstantCombin();
        
        // 2、变量拼接：拼接前后至少有1个变量出现，结果就在堆中。原理是用StringBuilder拼接。
        verifyVariCombin();

        // 3、比较字符串拼接和StringBuilder拼接的速度
        compareCombinSpeed();


    }

    /**
     * 比较 + 和StringBuilder的速度：
     * 使用sb.append()的方式：自始至终只创建1个sb对象。使用字符串+的拼接方式，每次循环会创建1个sb和String对象。
     * 可以通过确定字符串最终长度的方式，给sb构造方法赋值，减少字符串扩容带来的复制时间。
     */
    private static void compareCombinSpeed() {

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
     * 验证字符串变量的拼接：拼接前后至少有1个变量出现，结果就在堆中。原理是用StringBuilder拼接。
     * 注意用final修饰的字面量变量在编译期已经确定了，被当作常量；非字面量变量，即final String a = new String("a"); 需要运行期间确定，就不是常量了。
     * */
    private static void verifyVariCombin() {

        String s1 = "javaee";
        String s2 = "hadoop";

        String s3 = "javaeehadoop"; // 常量池
        String s4 = "javaee" + "hadoop"; // 常量池，编译期优化
        String s5 = s1 + "hadoop"; // 堆
        String s6 = "javaee" + s2; // 堆
        String s7 = s1 + s2; // 堆
        /**
         * s1 + s2的细节。
         * 相当于：StringBuilder s = new StringBuilder();
         * s.append("javaee");
         * s.append("hadoop");
         * s.toString()。
         *
         * 在jdk5之后使用的是StringBuilder，在之前使用的是StringBuffer
         */

        System.out.println("验证字符串的拼接：");
        System.out.println(s3 == s4); // true，常量拼接是在常量池中
        System.out.println(s3 == s5); // false：拼接中有变量，在堆中；
        System.out.println(s3 == s6); // false：同上；
        System.out.println(s3 == s7); // false：同上；
        System.out.println(s4 == s5); // false：同上；
        System.out.println(s4 == s6); // false：同上；
        System.out.println(s4 == s7); // false：同上；
        System.out.println(s5 == s6); // false：是堆中的2个对象；
        System.out.println(s5 == s7); // false：同上，2个堆对象；
        System.out.println(s6 == s7); // false：同上，2个堆对象

        // intern()：判断字符串常量池中是否存在，如果存在，返回常量池中的地址；不存在，则加载一份到常量池，再返回地址。
        String s8 = s6.intern(); // 常量池
        System.out.println(s3 == s8); // true：都在常量池

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
     * 验证字面量的拼接
     */
    public static void verifyConstantCombin(){
        // 1、验证字符串常量拼接(拼接的都是字面量)，结果在常量池，是编译期优化，可查看class文件。
        // 2、常量池中不会存在相同内容的常量。
        String s1 = "a" + "b" + "c"; // class文件中直接写的是 s1 = "abc";
        String s2 = "abc"; // 字面量字符串一定在常量池中
        System.out.println(s1 == s2);
    }

}
