package com.xyb.a4vms;

/**
 * 栈帧中的局部变量表测试
 */
public class A3LocalVars {

    public static void main(String[] args) {

        A3LocalVars a3LocalVars = new A3LocalVars();
        a3LocalVars.test1("a", 5);

    }

    public void test1(String a, int c){
        a = "5";
        String b = "a";
        {
            String d = "5";
        }
        int d = 5;

        d = test2();
        System.out.println(a + b);
    }

    private int test2() {
        int a = 5;
        return a;
    }


}
