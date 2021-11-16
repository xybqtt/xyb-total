package com.xyb.a15classinstruction;

/**
 * 操作数栈操作指令
 */
public class A6StackOperateTest {

    private long index = 0;

    public void print() {

        Object obj = new Object();
        String info = obj.toString();

    }

    public void foo() {
        bar();
    }

    public long bar() {
        return 0;
    }

    /**
     * 里面使用到了dup2_x1操作
     * @return
     */
    public long nextIndex() {
        return this.index++;
    }
}
