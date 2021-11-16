package com.xyb.a20jvmgui.leakmemory;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 内存泄漏7：缓存泄漏
 *      内存泄漏的另一个觉来源是缓存，一旦将对象引用放入缓存中，就很容易遗忘。比如在程序开始时从数据库中加载了一个几百万数据的数据，并存放到Map中。
 *
 *      对于这个问题，可以使用WeakHashMap代表缓存，引种Map的特点是，当除了自身有对key的引用外，此key没有其它引用那么此map会自动丢弃此值。
 *
 */
public class A7CacheLeak {

    static Map wMap = new WeakHashMap<>();
    static Map map = new HashMap();

    public static void main(String[] args) throws InterruptedException {
        init();
        testWeakHashMap();
        System.out.println("-----------------------");
        testHashMap();
    }

    public static void testHashMap() throws InterruptedException {

        System.out.println("HashMap GC之前");
        for(Object o : map.entrySet())
            System.out.println(o);

        System.gc();
        TimeUnit.SECONDS.sleep(5);

        System.out.println("HashMap GC之后");
        for(Object o : map.entrySet())
            System.out.println(o);

    }

    public static void testWeakHashMap() throws InterruptedException {

        System.out.println("WeakHashMap GC之前");
        for(Object o : wMap.entrySet())
            System.out.println(o);

        System.gc();
        TimeUnit.SECONDS.sleep(5);

        System.out.println("WeakHashMap GC之后");
        for(Object o : wMap.entrySet())
            System.out.println(o);

    }

    public static void init() {

        String ref1 = new String("1");
        String ref2 = new String("2");
        String ref3 = new String("3");
        String ref4 = new String("4");

        wMap.put(ref1, "cacheObject1");
        wMap.put(ref2, "cacheObject2");
        map.put(ref3, "cacheObject3");
        map.put(ref4, "cacheObject4");

        System.out.println("string引用ref1、2、3、4消失");

    }




}
