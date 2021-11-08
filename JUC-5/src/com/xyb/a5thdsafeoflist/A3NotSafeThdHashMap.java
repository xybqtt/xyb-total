package com.xyb.a5thdsafeoflist;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 演示HashMap的线程不安全，并解决
 */
public class A3NotSafeThdHashMap {

    public static void main(String[] args) {

         // Map<String, String> map = new HashMap<>(); // 会出现并发插入问题
        Map<String, String> map = new ConcurrentHashMap<>(); // 用这个类可以解决Map的并发插入问题

        for(int i = 0; i < 30; i++) {
            String key = i + "";
            new Thread(()->{
                map.put(key, UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }

}
