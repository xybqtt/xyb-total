package com.xyb.heap6;

import java.util.ArrayList;
import java.util.List;

/**
 * Minor GC和Major GC测试
 *
 * jvm参数设置：-Xms20m -Xmx20m -XX:-UseAdaptiveSizePolicy
 */
public class HeapGC3 {

    public static int youngSize;

    public static int survivorSize;

    public static int edenSize;

    public static List list = new ArrayList();

    public static void main(String[] args) throws InterruptedException {
        // 1、获取生存区、eden区的大小
        getYoungSize();

        // 2、验证0.75survivorSize大小数据分配在Eden区，且YGC后在S区
        verifyLowerSurvivorSize();

        Thread.sleep(200000000);


    }

    /**
     * 验证当new 1个大小小于S区的数据如何分配
     */
    private static void verifyLowerSurvivorSize() {
        byte[] bytes1 = new byte[(int)(0.75 * survivorSize)];
        System.out.println("查看数据是否存入Eden区");
        list.add(bytes1);
        exeYGC();
        System.out.println("查看数据是否从Eden区存入了S区");
    }

    /**
     * 让jvm执行一次YGC，即new 1个占用内存 > Eden区的变量。
     */
    private static void exeYGC() {
        byte[] bytes = new byte[edenSize + survivorSize];
    }


    /**
     * 新生代占1/3，eden区点8/10
     */
    private static void getYoungSize() {
        // 获取新生区的大小
        youngSize = (int)((double)Runtime.getRuntime().maxMemory() / 3);

        // 获取eden区的大小
        edenSize = (int)((double)youngSize * 8 / 10);

        // 获取单个生存区的大小
        survivorSize = (int)((double)youngSize / 10);
        System.out.println("youngSize = " + ((double)youngSize / 1024 / 1024) + "m");
        System.out.println("edenSize = " + ((double)edenSize / 1024 / 1024) + "m");
        System.out.println("survivorSize = " + ((double)survivorSize / 1024 / 1024) + "m");
    }

}
