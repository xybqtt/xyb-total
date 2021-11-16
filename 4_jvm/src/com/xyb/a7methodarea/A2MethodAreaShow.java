package com.xyb.a7methodarea;

/**
 * 查看方法区中有哪些内容，即查看cls字节码文件中有哪些内容：
 * 类的信息；
 * 属性的信息；
 * 所有方法的信息；
 * 运行时常量池。
 */
public class A2MethodAreaShow extends java.lang.Object implements Comparable<String> {

    public static final String FIELD_A = "a";

    public static String fieldB = "b";

    private String fieldC = "c";

    static {
        fieldB = "aaa";
    }

    public static void main(String[] args) {
        A2MethodAreaShow.fieldB = "ccc";
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
