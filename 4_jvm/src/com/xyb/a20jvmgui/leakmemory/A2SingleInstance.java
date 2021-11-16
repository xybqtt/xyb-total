package com.xyb.a20jvmgui.leakmemory;

/**
 * 内存泄漏2：单例模式
 *      和静态集合导致内存泄漏的原因类似，因为是单例的静态特性，它的生命周期和JVM的生命周期一样长，所以如果单例对象持有外部对象的引用，那么这个外部对象也不会被回收，造成内存泄漏
 *
 */
public class A2SingleInstance {

    private static A2SingleInstance single;

    private Object obj;

    public static void main(String[] args) {
        A2SingleInstance single = A2SingleInstance.getSingle();
        single.setObj(new Object());
    }

    public static A2SingleInstance getSingle() {

        if(single != null)
            return single;
        single = new A2SingleInstance();
        return single;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
