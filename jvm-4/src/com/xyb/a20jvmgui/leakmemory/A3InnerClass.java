package com.xyb.a20jvmgui.leakmemory;

/**
 * 内存泄漏3：内部类持有外部类
 *      如果一个外部类的实例对象的方法返回了一个内部类的实例对象。
 *      这个内部类对象被长期引用了，即使那个外部类实例对象不再被使用，但由于内部类持有外部类的实例对象，外部类对象将不会被回收，造成内存泄漏。
 *
 */
public class A3InnerClass {


    public static void main(String[] args) {
        A3InnerClass a3 = new A3InnerClass();
        InnerClass ic = a3.f1();
        // 如果ic被其它对象长期引用，则无法回收a3。
    }


    public InnerClass f1() {
        return new InnerClass();
    }


    class InnerClass {



    }

}
