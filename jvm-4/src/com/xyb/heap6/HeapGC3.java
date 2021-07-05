package com.xyb.heap6;

import java.util.ArrayList;
import java.util.List;

/**
 * Minor GC和Major GC测试
 *
 * jvm参数设置：-Xms40m -Xmx40m -XX:NewRatio=3 -XX:SurvivorRatio=8 -XX:-UseAdaptiveSizePolicy
 */
public class HeapGC3 {

    public static final int NEW_RATIO = 3;

    public static final int SURVIVOR_RATIO = 1;

    public static int youngSize;

    public static int survivorSize;

    public static int edenSize;

    public static int oldSize;

    public static List list = new ArrayList();

    public static void main(String[] args) throws InterruptedException {

        // 1、获取生存区、eden区的大小
        getYoungSize();

        Thread.sleep(10000);
        System.out.println("开始执行");
        // 2、验证0.25survivorSize大小数据分配在Eden区，且YGC后在S区
        verifyThreeQuarSArea();
        System.out.println("new 0.75S数据");
        Thread.sleep(10000);



        exeYGC(); // 数据进入S区
        Thread.sleep(10000);
        System.out.println("ygc执行完毕");
        list.clear();
        exeYGC(); // 数据从S区清除
        exeFullGC();
        Thread.sleep(100000);

        // 3、验证当对象 > 1.0S时，YGC后应该直接分配在Old
        verifyMulSizeGC(1.0, survivorSize);
        exeYGC(); // 数据进入old区
        Thread.sleep(10000);



    }

    /**
     * 验证当new 一个0.75S大小的数据时，是否先存入Eden区，触发YGC的时候，存入S区
     */
    private static void verifyThreeQuarSArea() {
    }


    /**
     * 验证当new 1个大小小于S区的数据如何分配
     */
    private static void verifyMulSizeGC(double mul, int size) {
        byte[] bytes1 = new byte[(int)(mul * size)];
        System.out.println("new 了个对象");
        list.add(bytes1);
    }

    /**
     * 让jvm执行一次YGC，即new 1个占用内存 > Eden区的变量。
     */
    private static void exeYGC() {
        System.out.println("执行YGC");
        list.add(new byte[edenSize + survivorSize]);
    }

    /**
     * 让jvm执行一次FullGC，即new 1个占用内存 > old区的变量。
     */
    private static void exeFullGC() {
        list.add(new byte[oldSize]);
    }


    /**
     * 新生代占1/3，eden区点8/10
     */
    private static void getYoungSize() {

        // 此最大内存只包含1个S区大小
        long maxMemory = Runtime.getRuntime().maxMemory();

        // 获取jvm的真实内存 = maxMemory + S
        long realMaxMemory = (long)((double)maxMemory / (1.0 - (1.0 / ((NEW_RATIO + 1.0) * (SURVIVOR_RATIO + 2.0)))));

        // 获取新生区的大小
        youngSize = (int)(realMaxMemory / (NEW_RATIO + 1));

        // 获取老年代的大小
        oldSize = (int)(realMaxMemory * NEW_RATIO / (NEW_RATIO + 1));

        // 获取eden区的大小
        edenSize = (int)((double)youngSize * SURVIVOR_RATIO / (SURVIVOR_RATIO + 2));

        // 获取单个生存区的大小
        survivorSize = (int)((double)youngSize / (SURVIVOR_RATIO + 2));

        System.out.println("maxMemory = " + ((double)maxMemory / 1024 / 1024) + "m");
        System.out.println("realMemory = " + ((double)realMaxMemory / 1024 / 1024) + "m");
        System.out.println("youngSize = " + ((double)youngSize / 1024 / 1024) + "m");
        System.out.println("old = " + ((double)oldSize / 1024 / 1024) + "m");
        System.out.println("edenSize = " + ((double)edenSize / 1024 / 1024) + "m");
        System.out.println("survivorSize = " + ((double)survivorSize / 1024 / 1024) + "m");
    }

}
