package com.xyb.a5prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * 带原型管理器的原型模式
 */
public class A2PrototypeManager {

    private static Map<String, A2MyPrototype> map = new HashMap<String, A2MyPrototype>();

    static {
        map.put("1", new A2RealizeType1("a1"));
        map.put("2", new A2RealizeType2("a2"));
    }

    public static void main(String[] args) {
        A2RealizeType1 a2RealizeType1 = (A2RealizeType1) A2PrototypeManager.getClonePrototype("1");
        System.out.println(a2RealizeType1);

        A2RealizeType2 a2RealizeType2 = (A2RealizeType2) A2PrototypeManager.getClonePrototype("2");
        System.out.println(a2RealizeType2);

    }

    /**
     * 设置原型对象
     */
    public static void setPrototype(String id, A2MyPrototype prototype) {
        map.put(id, prototype);
    }

    /**
     * 根据id获取原型对象的克隆对象
     */
    public static A2MyPrototype getClonePrototype(String id) {
        return map.get(id).clone();
    }

}

/**
 * 原型
 */
abstract class A2MyPrototype extends Object implements Cloneable {

    public abstract String getName();

    public A2MyPrototype clone() {
        try {
            return (A2MyPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}

class A2RealizeType1 extends A2MyPrototype {

    private String name;

    public A2RealizeType1(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "A2RealizeType1{" +
                "name='" + name + '\'' +
                '}';
    }
}

class A2RealizeType2 extends A2MyPrototype {

    private String name;

    public A2RealizeType2(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "A2RealizeType2{" +
                "name='" + name + '\'' +
                '}';
    }
}
