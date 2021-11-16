package com.xyb.a17clsloader2;

/**
 * 类的显式和隐式加载
 */
public class A1ClassLoad {

    public static void main(String[] args) throws ClassNotFoundException {

        User user = new User(); // 隐式加载
        User.a = 6; // 隐式加载2

        Class clazz = Class.forName("com.xyb.a17clsloader2.User"); // 类的显式加载

        ClassLoader.getSystemClassLoader().loadClass("com.xyb.a17clsloader2.User"); // 类的第2种显式加载
    }

}

class User {
    public static int a = 5;
}
