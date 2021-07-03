package com.xyb.vms4;

/**
 * 栈帧中的局部变量表测试
 */
public class LocalVars3 {

    public static void main(String[] args) {

        LocalVars3 localVars3 = new LocalVars3();
        localVars3.test1("a", 5);

    }

    public void test1(String a, int c){

        a = "5";

        String b = "a";

        System.out.println(a + b);

    }

}
