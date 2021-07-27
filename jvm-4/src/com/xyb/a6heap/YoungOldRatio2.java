package com.xyb.a6heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 新生代和老年代的所占堆内存比例；
 * 新生代中Eden、Survivor所占比例
 * -XX:NewRatio=3  设置 新生代:老年代 = 1:3；
 * -XX:SurvivorRatio=1  设置 Eden区:S0:S1 = 1:1:1。
 * 用jvisualvm查看，或打印GC日志查看。
 *
 * 本类的jvm启动参数：-Xms60m -Xmx60m -XX:NewRatio=3 -XX:SurvivorRatio=1 -XX:-UseAdaptiveSizePolicy -XX:+PrintGCDetails
 */
public class YoungOldRatio2 {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(200000000);
    }

}
