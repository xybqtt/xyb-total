package com.xyb.string10;

/**
 * 字符串intern()方法的使用
 *
 */
public class InternUse4 {

    public static void main(String[] args) {
        // 如何保证变量s指向的是字符串常量池中的数据
        // 2种方式
        String s = "abc"; // 字面量定义
        String a = new String("aaa").intern(); // 调用intern()方法
    }

}
