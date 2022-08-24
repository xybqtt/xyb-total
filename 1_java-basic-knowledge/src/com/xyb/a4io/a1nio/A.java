package com.xyb.a4io.a1nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class A {

    public static void main(String[] args) throws Exception {

        /**
         * 2、找出围棋中黑子与白子的活子数。所谓活子数，就是有大于2个气的棋子。一个棋子在棋盘上，与它直线紧邻的空点是这个棋子的“气”。直线紧邻的点上如果有同色棋子存在，这些棋子就相互连接成一个不可分割的整体。直线紧邻的点上如果有异色棋子存在，此处的气便不存在。棋子如失去所有的气，就不能在棋盘上存在。
         *         输入说明：输入只有一行，第一个字符输入的棋盘大小，后续按行列输入棋子情况，其中W为白子，B为黑子，N为空。
         *         样例输入：
         * 3WWWNBBBNN
         */




    }

    public static void f1(int arrLength, int[] arr) {

        int nextCheckPos = arrLength - 1;

        int smallerCount = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int nowNum = arr[i];
            for(int j = i + 1; j < arr.length; j++) {
                if(nowNum > arr[j])
                    smallerCount++;
            }
            System.out.println(smallerCount);
            smallerCount = 0;
        }

    }

}






















