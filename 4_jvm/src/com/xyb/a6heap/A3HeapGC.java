package com.xyb.a6heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Minor GC和Major GC测试
 *
 * jvm参数设置：-Xms40m -Xmx40m -XX:NewRatio=3 -XX:SurvivorRatio=2 -XX:-UseAdaptiveSizePolicy -XX:+UseTLAB
 */
public class A3HeapGC {

    public static final int NEW_RATIO = 3;

    public static final int SURVIVOR_RATIO = 2;

    public static int youngSize;

    public static int survivorSize;

    public static int edenSize;

    public static int oldSize;

    public static List list = new ArrayList();

    public static Scanner scanner = new Scanner(System.in);

    public static double scanNum;

    public A3HeapGC(){
        getAllAreaSize();
    }

    public static void main(String[] args) throws InterruptedException {

        // 1、获取生存区、eden区的大小
        getAllAreaSize();

        System.out.println("请输入想要验证的内容：1、验证0.75S在YGC时，数据是否落入S区；2、验证1.5S在YGC时，是否直接落入old区；");
        scanNum = scanner.nextDouble();

        switch ((int) scanNum){
            case 1:
                verifyGC();
                break;
            default:
                break;
        }




    }

    /**
     * 验证当new 一个0.75S大小的数据时，是否先存入Eden区，触发YGC的时候，存入S区
     */
    private static void verifyGC() throws InterruptedException {

        while (true){
            System.out.println("请输入在eden区想放多少M的数据：");
            scanNum = scanner.nextDouble(); // 可以输入-1，只new，不引用
            byte[] bytes = new byte[(int) (Math.abs(scanNum) * 1024 * 1024)];
            if(scanNum == 0)
                break;
            if(scanNum > 0)
                list.add(bytes);
        }
    }

    /**
     * 让jvm执行一次YGC，即new 1个占用内存 > Eden区的变量。
     */
    private static void exeYGC() {
        System.out.println("准备执行YGC");
        list.add(new byte[(int) (0.9 * edenSize)]);
        System.out.println("YGC调用完成");
    }

    /**
     * 让jvm执行一次FullGC，即new 1个占用内存 > old区的变量。
     */
    private static void exeFullGC() {
        list.add(new byte[oldSize]);
    }


    /**
     * 获取所有区的大小
     */
    private static void getAllAreaSize() {

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
