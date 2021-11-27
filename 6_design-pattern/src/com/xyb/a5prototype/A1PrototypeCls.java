package com.xyb.a5prototype;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 原型模式
 */
public class A1PrototypeCls implements Cloneable {

    private int intField1;
    private String strfield2;
    public List<String> list;

    public final Aclass aclass;

    public static void main(String[] args) {
        A1PrototypeCls a1 = new A1PrototypeCls(1, new String("2"), new ArrayList<>(), new Aclass("a"));
        A1PrototypeCls a2 = a1.clone();
        A1PrototypeCls a3 = a1.clone();

        System.out.println(a1);
        System.out.println(a2);

        System.out.println("a1 == a2：" + (a1 == a2));
        System.out.println("a1 == a3：" + (a1 == a3));

        // 查看克隆对象的原型对象的属性是否是同一个
        System.out.println("查看克隆对象的原型对象的属性是否是同一个实例(==)：");
        System.out.println("    字符串类型a1.strfield2 == a2.strfield2：" + (a1.getStrfield2() == a2.getStrfield2()));
        System.out.println("    引用类型a1.list == a2.list：" + (a1.list == a2.list));
        System.out.println("    final修饰a1.aclass == a2.aclass：" + (a1.aclass == a2.aclass));


    }

    public A1PrototypeCls(int intField1, String strfield2, List<String> list, Aclass aclass) {
        this.intField1 = intField1;
        this.strfield2 = strfield2;
        this.list = list;
        this.aclass = aclass;
    }

    public String getStrfield2() {
        return strfield2;
    }

    @Override
    public A1PrototypeCls clone() {
        try {
            return (A1PrototypeCls)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            System.out.println("克隆失败");
        }
        return null;
    }

    @Override
    public String toString() {
        return "A1PrototypeCls{" +
                "intField1=" + intField1 +
                ", strfield2='" + strfield2 + '\'' +
                ", list=" + list +
                ", aclass=" + aclass +
                '}';
    }
}

class Aclass {
    public String name;

    public Aclass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Aclass{" +
                "name='" + name + '\'' +
                '}';
    }
}

