package com.xyb.a15classinstruction;

import java.util.Date;

/**
 * 指令5：方法调用与返回指令
 */
public class A5MethodInvokeReturnTest {

    /**
     * 方法调用指令：invokespecial，不会在调用时进行动态派发的方法的调用，即在代码编写时就可以确定调用哪些方法，即独一无二的方法。
     *  实例初始化方法（构造器）：调用的是new Aclass();的构造器方法，此类的构造器是确定的。
     *  私有方法：私有方法不会被其它类调用到包括子类，所以在本类中调用的时候是唯一确定的方法；
     *  父类方法：super.method，父类的方法，子类虽然可以重写父类的方法，但此处明确要求的是调用父类的这个方法，所以也是独一无二的。
     */
    public void invoke1() {

        Date date = new Date(); // 构造器方法<init>()
        Thread t1 = new Thread(); // 构造器方法<init>()
        super.toString(); // 调用父类的方法
        methodPrivate(); // 调用本类的私有方法

    }

    /**
     * 方法调用指令：invokestatic，调用静态方法
     */
    public void invoke2() {
        methodStatic();
    }

    /**
     * 方法调用指令：invokeinterface
     */
    public void invoke3() {
        Thread t1 = new Thread();
        ((Runnable)t1).run();

        Comparable<Integer> com = null;
        com.compareTo(123);
    }

    /**
     * 方法调用指令：invokevirtual
     */
    public void invoke4() {
        System.out.println("hello");

        Thread t1 = null;
        t1.run();
    }

    /**
     * 方法返回值指令：ireturn
     * 返回byte、short、char、boolean基本类型，也是用ireturn
     * @return
     */
    public int returnInt() {
        return 500;
    }

    /**
     * 方法返回值指令：lreturn
     * @return
     */
    public long returnLong() {
        return 500L;
    }

    /**
     * 方法返回值指令：freturn
     * @return
     */
    public float returnFloat() {
        return 500.0f;
    }

    /**
     * 方法返回值指令：dreturn
     * @return
     */
    public double returnDouble() {
        return 500.0;
    }

    /**
     * 方法返回值指令：areturn，返回引用类型
     * @return
     */
    public String returnAObj() {
        return "a";
    }

    /**
     * 方法返回值指令：areturn，返回数组类型
     * @return
     */
    public int[] returnArr() {
        return new int[10];
    }

    /**
     * 方法返回值指令：return，返回void
     * @return
     */
    public void returnVoid() {

        return;
    }


    public static void methodStatic() {

        System.out.println();

    }

    private void methodPrivate() {
        System.out.println("");
    }
}
