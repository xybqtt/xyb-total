package com.xyb.a7adapter;

/**
 * 接口适配器模式
 */
public class A3InterfaceAdapter {


}

interface A3I1 {
    public void m1();
    public void m2();
    default void m3() {

    }
}

abstract class A3I1Adapter implements A3I1 {
    public void m1() {
    }

    public void m2() {
    }
}

/**
 * A3Cls可以只实现m1()，而不是实现m2()，用default也可以实现
 */
class A3Cls extends A3I1Adapter {

    @Override
    public void m1() {

    }

}
