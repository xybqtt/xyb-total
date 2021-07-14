package com.xyb.string10;

import java.util.Scanner;

/**
 * 测试StringTable字符串常量池数组的长度对性能的影响。
 * 分别在：
 *  -XX:StringTableSize=1009(jdk8可设置的最小值)； 221ms
 *  默认60013：51ms
 *  -XX:StringTableSize=100000下测试 48ms
 */
public class StringTableTest1 {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 100009; i++){
            // 将字符串存入字符串常量池
            new String(i + "").intern();
        }

        System.out.println(System.currentTimeMillis() - startTime + "ms");
    }

}
