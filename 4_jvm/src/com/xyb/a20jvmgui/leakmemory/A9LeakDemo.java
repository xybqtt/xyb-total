package com.xyb.a20jvmgui.leakmemory;

/**
 * 内存泄漏demo1：
 *      比如Stack的弹出操作，如果只弹出，但是不将被弹出的位置的元素置为null，则此位置所引用的元素直到下一个此位置被重新push前不会被GC。
 */
public class A9LeakDemo {



}
