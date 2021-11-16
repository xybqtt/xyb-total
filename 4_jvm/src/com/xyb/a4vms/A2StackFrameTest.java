package com.xyb.a4vms;

/**
 * 栈帧测试
 */
public class A2StackFrameTest {

    public static void main(String[] args) {

        A2StackFrameTest test = new A2StackFrameTest();
        test.method1();

    }

    public void method1(){
        System.out.println("进入method1");
        method2();
        System.out.println("退出method1");
    }

    public void method2(){
        System.out.println("进入method2");
        method3();
        System.out.println("退出method2");
    }

    public void method3(){
        System.out.println("进入method3");
        System.out.println("退出method3");
    }
}
