package com.xyb.clsloader2;

/**
 * classLoader和链接阶段测试
 */
public class LinkingTest1 {

    public static void main(String[] args) {
        System.out.println("a");
        Cls2 cls2 = new Cls2();
        cls2.fn1();
    }

}

class Cls2 {
    public void fn1(){
        System.out.println("fn1");
    }
}
