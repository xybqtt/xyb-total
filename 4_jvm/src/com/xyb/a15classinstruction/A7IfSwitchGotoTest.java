package com.xyb.a15classinstruction;

/**
 * 指令7：控制转移指令
 */
public class A7IfSwitchGotoTest {

    public void compare1() {
        int a = 0;
        if(a == 0)
            a = 10;
        else
            a = 20;
    }

    public boolean compareNull(String str) {
        if(str == null)
            return true;
        else
            return false;
    }

    /**
     * 结合比较指令
     */
    public void compare2() {
        float f1 = 9;
        float f2 = 10;
        System.out.println(f1 < f2);
    }

    public void compare3() {
        int i1 = 10;
        long l1 = 20;
        System.out.println(i1 < l1); // 比较的时候会将int转为long
    }

    public int compare4(double d) {
        if(d > 50.0)
            return 1;
        else
            return 2;
    }

    // 2、比较条件跳转指令
    public void ifCompare1() {
        int i = 10;
        int j = 20;
        System.out.println(i < j);
    }

    public void ifCompare2() {
        short s1 = 9;
        byte b1 = 10;
        System.out.println(s1 > b1);
    }

    public void ifCompare3() {
        Object o1 = new Object();
        Object o2 = new Object();
        System.out.println(o1 == o2);
        System.out.println(o1 != o2);
    }


    // 3、多条件分支跳转
    /**
     * 使用tableswitch，根据索引跳转，速度快，时间复杂度为O(1)
     * @param a
     */
    public void switch1(int a) {
        int num;
        switch (a) {
            case 1:
                num = 10;
                break;
            case 2:
                num = 20;
                // break;
            case 3:
                num = 30;
                break;
            default:
                num = 40;
        }
    }

    /**
     * lookupswitch，不根据索引跳转，扫描所有case或使用二分法查找，时间复杂度为O(n)或O(logn)
     * @param a
     */
    public void switch2(int a) {
        int num;
        switch (a) {
            case 100:
                num = 10;
                break;
            case 500:
                num = 20;
                // break;
            case 30:
                num = 30;
                break;
            default:
                num = 40;
        }
    }

    /**
     * lookupswitch，在jdk7之后，case可以是字符串，但在编译时，会被转化为其对应的hashcode，时间复杂度同lookupswitch
     * @param a
     */
    public void switch3(String a) {
        switch (a) {
            case "asdkfi":
                break;
            case "ddssdb":
                 break;
            case "dfewg":
                break;
            default:
                break;
        }
    }


    // 4、无条件跳转指令
    public void whileInt() {
        int i = 0;
        while (i < 100) {
            String s = "aaa";
            i++;
        }
    }

    public void whileDouble() {
        /*
        使用double++ 和 int++的区别，int++不用将变量从局部变量表取出到栈操作，直接在局部变量表操作，double则需要先加载到栈，最后再写回局部变量表
         */
        double d = 0.0;
        while (d < 100.1) {
            String s = "aaa";
            d++;
        }
    }

    /**
     * 相比用int计数，每次都需要出入栈操作，还需要数据窄化
     */
    public void printFor() {
        short i = 1;

        for (i = 0; i < 100; i++) {
            String str = "aaa";

        }
    }

    public void printFor2() {
        int i = 1;
        for (i = 0; i < 100; i++) {

        }

        while (i <= 100) {
            i++;
        }
    }


}
