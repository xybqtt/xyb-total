package com.xyb.a5thdsafeoflist;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 演示HashSet的线程不安全，并解决
 */
public class A2NotSafeThdHashSet {

    public static void main(String[] args) {

        // Set<String> set = new HashSet<>(); // 会出现并发插入问题
        Set<String> set = new CopyOnWriteArraySet<>(); // 用这个类可以解决set的并发插入问题

        for(int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

    }

}
