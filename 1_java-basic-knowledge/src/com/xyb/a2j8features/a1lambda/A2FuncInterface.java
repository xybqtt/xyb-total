package com.xyb.a2j8features.a1lambda;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 函数式接口，及function包下的常用函数式接口
 * Consumer<T>消费型接口    void accept(T t)|
 * Supplier<T>供给型接口    T get()|
 * Function<T, R>函数型接口    R apply(T t)|
 * Predicate<T>断定型接口    test(T t)|
 */
public class A2FuncInterface {

    public static void main(String[] args) {
        happyTime(400, cost -> System.out.println("Consumer：爽一下，花了" + cost + "元。"));
        filterString("abc", str -> ((String) str).length() <= 3);
    }

    public static void happyTime(double money, Consumer con) {
        con.accept(money);
    }

    /**
     *
     * @param str
     * @param p 根据p.test方法，符合返回true，否则false。
     */
    public static void filterString(String str, Predicate p) {
        boolean b = p.test(str);
        System.out.println("Predicate：查看字符串长是否<=3，" + str + "-" + b);
    };

}
