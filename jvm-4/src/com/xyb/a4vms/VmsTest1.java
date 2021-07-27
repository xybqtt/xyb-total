package com.xyb.a4vms;

/**
 * java virtual machine stack：虚拟机栈测试
 */
public class VmsTest1 {

    public static int a = 0;

    /**
     * 查看栈需要多少次会报StackOverFlowError
     * -Xss256k查看不同栈大小下，栈溢出需要的次数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(a++);
        main(args);
    }

}
