package com.xyb.a14clsstructure;

/**
 * 查看字节码和Integer源码
 */
public class A1CompareTest {

    public static void main(String[] args) {
        integerCompTest();
        StrCompTest();
        clsTest();
    }

    /**
     * 子类如果重写了父类的方法和属性，则在new的时候，使用的是谁的属性和方法？
     * 这个和被new的对象有关，其实是和方法中被传的this相关，this是谁，就是调用谁的属性和方法。
     * 构造方法里面的执行顺序：
     *      1、(非显式代码)父类构造方法；
     *      2、(非显式代码)子类所有成员变量赋值，如int x = 30;，则在构造方法的class文件中会自动在构造方法中将x = 30；
     *      3、显式代码。
     */
    private static void clsTest() {

        Father f = new Son();
        System.out.println(f.x);

    }

    private static void StrCompTest() {

        String str = new String("a") + new String("b");
        String str1 = "ab";
        System.out.println(str == str1); // false

        String str2 = new String("ab");
        System.out.println(str == str2); // false
        System.out.println(str1 == str2); // false

    }

    public static void integerCompTest() {
        /**
         * Integer和int相比，会取出Integer中的value和int作比较(Integer的自动拆箱)，所以只要值相等，
         * 返回结果就是true。
         */
        Integer x = 5; // 相当于Integer.valueOf(5);
        int y = 5;
        System.out.println(x == y); // true

        /**
         * 为true的原因，Integer里面有个Integer数组，在静态块中，对-128 ~ 127的所有数据都
         * 进行了存储，当new一个Integer在此范围内时，其实是找到数组中对应位置的对象，并返回。
         * 所以此处为true。
         */
        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println(i1 == i2); // true

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4); // false

        System.out.println(i3 == 128); // true
    }

}

class Father {

    int x = 10;

    public Father() {
        this.print();
        x = 20;
    }

    public void print() {
        System.out.println("Father.x = " + x);
    }
}

class Son extends Father{

    int x = 30;

    public Son() {
        // 父类方法中也调用了print()，但是如果是new Son()的话，其实是调用的子类Son的print()方法，此时还没有给x赋值为30，所以x = 0。
        this.print();
        x = 40;
    }

    public void print() {
        System.out.println("Son.x = " + x);
    }
}