package com.xyb.a16detailclsloader.a3initialization;

import java.util.Random;

/**
 * 类的被动使用测试：
 *      静态字段：当通过子类引用父类的静态变量，不会导致子类初始化，只有真正声明这个字段的类才会被初始化。
 *      数组定义：通过数组定义类引用，不会触发此类的初始化
 *      引用常量：引用常量不会触发此类或接口的初始化。因为常量在链接阶段就已经被显式赋值了。
 *      loadClass方法：调用ClassLoader类的loadClass()方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
 */
public class A6PassiveUseTest {

    public static void main(String[] args) throws ClassNotFoundException {

        // 1、静态字段：当通过子类引用父类的静态变量，不会导致子类初始化，只有真正声明这个字段的类才会被初始化。
        int a = PassiveUse1.a;

        // 2、数组定义：通过数组定义类引用，不会触发此类的初始化
        PassiveUse1[] passiveUse1s = new PassiveUse1[10];
        System.out.println(passiveUse1s.getClass());
        System.out.println(passiveUse1s.getClass().getSuperclass());


        // 3、引用常量：引用常量不会触发此类或接口的初始化。因为常量在链接阶段就已经被显式赋值了。
        int b = PassiveUse1.A; // 是在链接的准备阶段赋值的，不涉及java指令执行，不会触发<clinit>()
        b = PassiveUse1Interface.B; // 同上

        // 4、loadClass方法：调用ClassLoader类的loadClass()方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class cls = classLoader.loadClass("com.xyb.a16detailclsloader.a3initialization.PassiveUse1");

    }

}

class PassiveUse1Father {

    public static int a = 1;

    static {
        System.out.println("PassiveUse1Father的初始化，子类调用父类静态变量，且不会初始化子类。");
    }

}

class PassiveUse1 extends PassiveUse1Father {

    public static final int A = 5;

    static {
        System.out.println("PassiveUse1的引用父类变量，不会初始化本类");
    }

}

/**
 * 41、读接口的常量，且常量非字面量赋值
 */
interface PassiveUse1Interface {

    /**
     * 接口中的变量只有可能是public static final。
     */
    public static final int B = 1;

    /**
     * 使用这种方式，可以在<clinit>()方法中加载new Thread，并且执行其中的静态代码块，只要这个执行了，就
     * 能证明接口的new Thread()执行了，再证明了接口的<clinit>()方法执行了。
     */
    public static final Thread T = new Thread(){

        {
            System.out.println("PassiveUse1Interface初始化：读接口的非字面量赋值的常量。");
        }

    };

}
