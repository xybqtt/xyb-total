package com.xyb.a16detailclsloader.a3initialization;

import java.io.*;
import java.util.Random;

/**
 * 类的主动使用测试
 */
public class A5AcitveUseTest {

    static {
        System.out.println("A5AcitveUseTest初始化：当虚拟机启动时，用户需要指定一个要执行的主类（包含main()方法的那个类），虚拟机会先初始化这个主类。");
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {

        // 0、main方法所在类的初始化


        // 1、实例化：当创建一个类的实例时，比如使用new关键字，或者通过反射、克隆、反序列化。查看有无调用<clinit>()方法。
        ActiveUse1 activeUse1 = new ActiveUse1();

        // 2、反射
        Class.forName("com.xyb.a16detailclsloader.a3initialization.ActiveUse2");

        // 3、反序列化(需要先把一个类进行序列化为一个文件)
        // 反序列化需要读取序列化后的文件，下面这个方法就是序列化类
        // ActiveUse3.serialCls();
        // parseSerialCls();

        // 4、调用getstatic或putstatic指令，即读写静态变量(非final)、或给常量通过非字面量赋值(字面量赋值不会触发<clinit>()方法)
        int a = ActiveUse4.a; // 调用了常量a，则必定会调用其通过非字面量方法设置值的方法，会运行<clinit>()
        int b = ActiveUse4.b; // 读取静态变量a、设置静态变量a，都会触发<clinit>()
        int c = ActiveUse4.C; // 读取通过字面量设置的常量，不用触发<clinit>()，因为没有java代码
        int d = ActiveUse4Interface.b; // 读取接口的非字面量定义的常量b，会触发<clinit>()

        // 5、调用invokestatic指令，即调用类的静态方法，
        ActiveUse5.f1();

        // 6、当子类初始化时，分先初始化父类，但并不会初始化接口。当初始化接口时，也不会初始化其父接口
        ActiveUse6Son son = new ActiveUse6Son();
        int e = ActiveUse6Interface.B;

        // 7、接口中定义了default方法，则其子孙类实现类的初始化，会导致此接口初始化。
        ActiveUse7 activeUse7 = new ActiveUse7();


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
 * 3、反序列化，类需要实现
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

/**
 * 4、读写类的静态变量，即调用getstatic、putstatic指令，final修饰特殊考虑
 */
class ActiveUse4 {

    public static final int a = Integer.valueOf("1");

    public static int b = 2;

    public static final int C = 0;

    static {
        System.out.println("ActiveUse4初始化：读写静态变量。");
    }

}

/**
 * 41、读接口的常量，且常量非字面量赋值
 */
interface ActiveUse4Interface {

    /**
     * 接口中的变量只有可能是public static final。
     */
    public static final int b = new Random().nextInt(10);

    /**
     * 使用这种方式，可以在<clinit>()方法中加载new Thread，并且执行其中的静态代码块，只要这个执行了，就
     * 能证明接口的new Thread()执行了，再证明了接口的<clinit>()方法执行了。
     */
    public static final Thread T = new Thread(){

        {
            System.out.println("ActiveUse4Interface初始化：读接口的非字面量赋值的常量。");
        }

    };

}

/**
 * 5、调用invokestatic指令，即调用类的静态方法
 */
class ActiveUse5 {

    static {
        System.out.println("ActiveUse5初始化：调用类的静态方法。");
    }

    public static void f1() {

    }

}

/**
 * 6、子类初始化必定调用父类初始化；
 *  子类初始化时，不会先初始化其接口；
 *  子接口初始化时，不会先初始化其接口；
 */
class ActiveUse6Parent {

    static {
        System.out.println("ActiveUse6Parent初始化：当子类初始化时，会先初始化父类。");
    }

}

interface ActiveUse6InterfaceParent {

    /**
     * 接口中的变量只有可能是public static final。
     */
    public static final int B = new Random().nextInt(10);

    /**
     * 使用这种方式，可以在<clinit>()方法中加载new Thread，并且执行其中的静态代码块，只要这个执行了，就
     * 能证明接口的new Thread()执行了，再证明了接口的<clinit>()方法执行了。
     */
    public static final Thread T = new Thread(){

        {
            System.out.println("ActiveUse6InterfaceParent初始化：子接口初始化并不会导致接口初始化。");
        }

    };
}

interface ActiveUse6Interface extends ActiveUse6InterfaceParent {

    /**
     * 接口中的变量只有可能是public static final。
     */
    public static final int B = new Random().nextInt(10);

    /**
     * 使用这种方式，可以在<clinit>()方法中加载new Thread，并且执行其中的静态代码块，只要这个执行了，就
     * 能证明接口的new Thread()执行了，再证明了接口的<clinit>()方法执行了。
     */
    public static final Thread T = new Thread(){

        {
            System.out.println("ActiveUse6Interface初始化：接口初始化并不会导致父接口初始化。");
        }

    };
}

class ActiveUse6Son extends ActiveUse6Parent implements ActiveUse6Interface {

    static {
        System.out.println("ActiveUse6Son初始化：子类初始化。");
    }

}


/**
 * 7、default方法：如果一个接口定义了default方法，那么直接实现或者间接实现该接口的类的初始化，该接口要在其之前被初始化。
 */
interface ActiveUse7Interface {

    /**
     * 接口中的变量只有可能是public static final。
     */
    public static final int B = new Random().nextInt(10);

    /**
     * 使用这种方式，可以在<clinit>()方法中加载new Thread，并且执行其中的静态代码块，只要这个执行了，就
     * 能证明接口的new Thread()执行了，再证明了接口的<clinit>()方法执行了。
     */
    public static final Thread T = new Thread(){

        {
            System.out.println("ActiveUse7Interface初始化：接口中定义了default方法，则其子孙类实现类的初始化，会导致此接口初始化。");
        }

    };

    public default void f1() {
        System.out.println("接口默认方法");
    }
}

class ActiveUse7 implements ActiveUse7Interface {

}