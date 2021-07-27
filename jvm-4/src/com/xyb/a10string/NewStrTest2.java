package com.xyb.a10string;

/**
 * new String("a")到底创建了几个对象：
 *      如果"a"已经加载到了字符串常量池，则只会创建1个对象，就是new的String；
 *      如果没有加载到字符串常量池，则是2个，1个在堆中，1个在字符串常量池中。
 *
 * new String("1") + new String("2")创建了几个对象：
 *      new StringBuilder
 *      new String()
 *      "1" // 如果已经在字符串常量池，则不会生成此对象
 *      new String()
 *      "2" // 如果已经在字符串常量池，则不会生成此对象
 *      StringBuilder.toString()：里面还有一个new String()。
 *
 */
public class NewStrTest2 {

    public static void main(String[] args) {
        String s = new String("1"); // new 了个String放入堆中地址1，再将1个"1"进行ldc放入字符串常量池地址2
        s.intern(); // 此时"1"已经在字符串常量池了，应该返回地址2，但没有接收
        String s2 = "1"; // 进行ldc，但发现已经在字符串常量池了，返回了此字符串的地址2
        System.out.println(s == s2); // 地址1 != 地址2

        String s3 = new String("1") + new String("1"); // s3 = new StringBuilder("").append(new String("1")).append(new String("1")).toString()，堆中为地址3
        s3.intern(); // 此时地址3进入字符串常量池，即字符串常量池存入的是s3的引用(地址)。
        String s4 = "11"; // 进行ldc，但发现已经在字符串常量池了，返回了此字符串的地址3
        System.out.println(s3 == s4); // 地址3 == 地址3
        String stringBuilder = new StringBuilder("").toString();
    }





}
