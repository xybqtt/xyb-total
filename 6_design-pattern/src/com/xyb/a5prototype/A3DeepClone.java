package com.xyb.a5prototype;

import java.io.*;

/**
 * 深拷贝(浅拷贝com\xyb\a5prototype\A1PrototypeCls.java)，
 * 使用序列化，
 * 需要实现Serializable接口。
 */
public class A3DeepClone implements Serializable {

    public String name;
    public A3DeepClone friend;

    public static void main(String[] args) {
        A3DeepClone a3 = new A3DeepClone("第一层", new A3DeepClone("第二层", null));

        A3DeepClone a4 = a3.deepClone();
        System.out.println(a3);
        System.out.println(a4);
        System.out.println(a3 == a4);
    }

    public A3DeepClone(String name, A3DeepClone friend) {
        this.name = name;
        this.friend = friend;
    }

    public A3DeepClone deepClone() {
        //创建流对象
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this); //当前这个对象以对象流的方式输出
            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            A3DeepClone copyObj = (A3DeepClone)ois.readObject();
            return copyObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //关闭流
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

}
