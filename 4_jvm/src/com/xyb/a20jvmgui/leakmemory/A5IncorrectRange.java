package com.xyb.a20jvmgui.leakmemory;

/**
 * 内存泄漏5：变量不合理的作用域
 *      一般而言，一个变量的定义的作用范围大于其使用范围，很有可能造成内存泄漏。另一方面，如果没有及时地把对象设置为null，很有可能导致内存泄漏的发生。
 *
 */
public class A5IncorrectRange {

    private String msg;

    public void receiveMsg(String msg) {
        this.msg = msg;
        // 保存msg到数据库中
        // 完成后msg已经没有用了，但是如果A5IncorrectRange的这个实例一直存活，则msg字符串所占的内存一直被占用，导致内存泄漏。
    }

}
