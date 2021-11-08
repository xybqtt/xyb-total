package com.xyb.a5thdsafeoflist;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 不安全集合线程演示
 * 下面的代码可能会报异常，并发修改问题
 * 解决方法：
 *      1、使用Vector实现类，代替ArrayList，Vector.add()方法由synchronized修饰，但会降低效率；
 *      2、
 */
public class A1NotSafeThdList {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(); // 会出现并发插入问题
        // List<String> list = new Vector<>(); // 解决方法1：不会出现并发插入问题，但效率差
        // List<String> list = Collections.synchronizedList(new ArrayList<>()); // 解决方法2：通过Collections工具类，效率差
        // List<String> list = new CopyOnWriteArrayList<>(); // 解决方法3：通过JUC包下的这个类去解决，效率好。

        for(int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

    }

}
