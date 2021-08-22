package com.xyb.a15classinstruction;

/**
 * 指令9：同步控制指令
 */
public class A9SynchronizedTest {

    private int i = 0;
    private Object obj = new Object();


    /**
     * 方法是否添加synchronized不在方法的code属性中显示，而是在方法的访问标志中显示。
     */
    public synchronized void add() {
        i++;
    }


    public void substract() {
        synchronized (this.obj) {
            i--;
        }
    }

}
