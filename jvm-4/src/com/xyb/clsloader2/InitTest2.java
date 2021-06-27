package com.xyb.clsloader2;

/**
 * classLoader的初始化阶段
 * 初始化阶段就是执行类构造器方法<clinit>()的过程；
 * 　　2.此方法不需定义，是javac编译器自动收集类中的所有类变量的赋值动作和静态代码块中的语句合并而来；
 */
public class InitTest2 {

    public static int b = 10;

    public static String str = "abc";
    public ClassLoader appClassLoader;
    static {
        a = 5;
    }

    // 注意，类变量是可以声明在static下面的，因为在链接的prepare阶段，会将所有的类变量的默认初始值，即零值，所以，当static执行时
    // a已经存在了。
    public static int a;


}
