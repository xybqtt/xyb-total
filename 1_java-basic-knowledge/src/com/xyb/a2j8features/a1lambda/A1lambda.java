package com.xyb.a2j8features.a1lambda;

/**
 * lambda表达式总结：
 * 1、只能用于创建"函数式接口(只包含一个抽象方法的接口)"的实例；
 * 2、举例：(o1, o2) -> System.out.println("这是FunctionalInterface1.f1()方法。");
 *      ->：lambda 操作符 或 箭头操作符；
 *      ->左边：代表唯一抽象方法的形参列表，可以不用写类型，因为只有一个抽象方法，编译器自己能判断
 *          没有参数：必须写"()"；
 *          仅有一个参数：括号可以省略，可以写"o1"或"(o1)"；
 *          有多个参数："(o1, o2)"。
 *      ->右边：
 *          多行方法体："{ 方法体 }";
 *          单行方法体：
 *              有返回值："{}、return"必须一起存在或省略；
 *              无返回值：可以省略"{}"。
 */
public class A1lambda {

    public static void main(String[] args) throws Exception {

        // 正常形式
        FunctionalInterface1 f1 = new FunctionalInterface1() {
            @Override
            public void f1() {
                System.out.println("这是FunctionalInterface1.f1()方法。");
            }
        };

        FunctionalInterface2 f2 = new FunctionalInterface2() {
            @Override
            public String f1(int a, String b) {
                System.out.println("这是FunctionalInterface2.f1()方法，参数为：" + a + "、" + b);
                return a + b;
            }
        };

        f1.f1();
        f2.f1(4, "a");

        System.out.println("----------------------------------------");
        System.out.println("使用lambda表达式");

        f1 = () -> System.out.println("这是FunctionalInterface1.f1()方法的lambda表达式形式。");
        f2 = (a, b) -> {
            System.out.println("这是FunctionalInterface2.f1()方法的lambda表达式形式，参数为：" + a + "、" + b);
            return a + b;
        };

        f1.f1();
        f2.f1(4, "a");

    }

}


/**
 * 自定义函数式接口，可以不加此注解，依然可以使用lambda表达式
 */
@FunctionalInterface
interface FunctionalInterface1 {

    public void f1() throws Exception;

}


/**
 * 自定义函数式接口
 */
@FunctionalInterface
interface FunctionalInterface2 {

    public String f1(int a, String b);

}

