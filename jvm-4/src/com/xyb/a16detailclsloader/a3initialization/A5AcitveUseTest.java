package com.xyb.a16detailclsloader.a3initialization;

import java.io.*;

public class A5AcitveUseTest {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        // 1、实例化：当创建一个类的实例时，比如使用new关键字，或者通过反射、克隆、反序列化。查看有无调用<clinit>()方法。
        ActiveUse1 activeUse1 = new ActiveUse1();

        // 2、反射
        Class.forName("com.xyb.a16detailclsloader.a3initialization.ActiveUse2");

        // 3、反序列化(需要先把一个类进行序列化为一个文件)
        // 反序列化需要读取序列化后的文件，下面这个方法就是序列化类
        // ActiveUse3.serialCls();
        parseSerialCls();


    }

    public static void parseSerialCls() {
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream("ActiveUse3.dat"));
            ActiveUse3 a3 = (ActiveUse3) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

/**
 * 1、实例化：当创建一个类的实例时，比如使用new关键字，或者通过反射、克隆、反序列化。
 */
class ActiveUse1 {

    static {
        System.out.println("ActiveUse1初始化：实例化。");
    }

}

/**
 * 2、反射
 */
class ActiveUse2 {

    static {
        System.out.println("ActiveUse2初始化：反射。");
    }

}

/**
 * 2、反序列化，类需要实现
 */
class ActiveUse3 implements Serializable {

    static {
        System.out.println("ActiveUse3初始化：反序列化。");
    }

    /**
     * 序列化本类
     */
    public static void serialCls() throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("ActiveUse3.dat"));
            oos.writeObject(new ActiveUse3());
        } catch (Exception e) {

        } finally {
            if (oos != null)
                oos.close();
        }
    }

}
