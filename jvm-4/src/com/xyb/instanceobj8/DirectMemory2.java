package com.xyb.instanceobj8;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * 直接内存的使用
 */
public class DirectMemory2 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024); // 1G
        System.out.println("直接内存已分配完毕，查看任务管理器此进程id所用内存");

        new Scanner(System.in).next();

        System.out.println("关闭直接内存");
        byteBuffer = null;
        System.gc();
        new Scanner(System.in).next();
    }

}
