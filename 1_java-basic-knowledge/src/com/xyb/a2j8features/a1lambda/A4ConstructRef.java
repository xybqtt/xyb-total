package com.xyb.a2j8features.a1lambda;

/**
 * 构造器引用：和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致。
 * 抽象方法的返回值类型即为构造器所属的类的类型。
 * 例：
 *     i1接口方法 ConstructCls f1();
 *     ConstructCls的空参构造方法，相当于：ConstructCls ConstructCls();2个方法参数签名相同。
 *     其它2个类似
 *     i2接口方法 ConstructCls f1(int a);
 *     ConstructCls的单参构造方法，相当于：ConstructCls ConstructCls(int a);2个方法参数签名相同。
 */
public class A4ConstructRef {

    public static void main(String[] args) {
        IConstruct1 i1 = ConstructCls::new;
        IConstruct2 i2 = ConstructCls::new;
        IConstruct3 i3 = ConstructCls::new;

        System.out.println("构造器引用演示：");
        i1.f1();
        i2.f1(1);
        i3.f1(2, "b");

    }

}

class ConstructCls {

    private int a;

    private String b;

    public ConstructCls() {
        System.out.println("调用了空参构造器，" + this.toString());
    }

    public ConstructCls(int a) {
        this.a = a;
        System.out.println("调用了单参构造器，" + this.toString());
    }

    public ConstructCls(int a, String b) {
        this.a = a;
        this.b = b;
        System.out.println("调用了全参构造器，" + this.toString());
    }


    @Override
    public String toString() {
        return "ConstructCls{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }
}

interface IConstruct1 {
    public ConstructCls f1();
}

interface IConstruct2 {
    public ConstructCls f1(int a);
}

interface IConstruct3 {
    public ConstructCls f1(int a, String b);
}

