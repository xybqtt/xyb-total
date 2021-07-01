package com.xyb.vms4;

/**
 * 栈帧测试
 */
public class StackFrameTest2 {

    public static void main(String[] args) {

        StackFrameTest2 test = new StackFrameTest2();
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
