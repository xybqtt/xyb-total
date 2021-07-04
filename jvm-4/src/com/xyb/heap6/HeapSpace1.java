package com.xyb.heap6;

/**
 * 查看堆初始化、最大扩展大小
 *
 * 本类jvm启动参数： -Xms600m -Xmx600m -XX:+PrintGCDetails
 */
public class HeapSpace1 {

    public static void main(String[] args) {
        // 1、查看堆内存初始化大小、最大大小
        getHeapSpaceSize();

        // 2、产生一个OOM: Java heap space
        createOOM();

    }

    /**
     * 生成一个堆溢出异常
     */
    private static void createOOM() {
        int byteLength = (int) Runtime.getRuntime().maxMemory();
        byte[] bytes = new byte[byteLength];
    }

    /**
     * 查看堆内存的大小，
     * 设置-Xms600m -Xmx600m，可改变heap大小
     */
    private static void getHeapSpaceSize() {

        // 获取jvm堆内存初始大小
        Long initHeapSize = Runtime.getRuntime().totalMemory() / 1024 / 1024;

        // 获取jvm堆内存可扩展到的最大大小
        Long maxHeapSize = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms：" + initHeapSize + "m，即" + (double) initHeapSize / 1024 + "g。");
        System.out.println("-Xmx：" + maxHeapSize + "m，即" + (double) maxHeapSize / 1024 + "g。");

    }


}
