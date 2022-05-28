package com.xyb.utils;

import java.util.Arrays;
import java.util.Random;

public class Utils {

    public static Random random = new Random();

    public static void main(String[] args) {
        Utils.createRandomIntArr();
    }

    /**
     * 交换arr数组中，下标为a、b的2个位置上的值。
     * 注意a、b不能是同一块内存，在本方法中，即a不能 = b。
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        if(a == b)
            return;
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

    /**
     *
     * @param a 排序后的数组
     * @param b 排序后的数组
     * @param c 排序前的数组
     */
    public static boolean compareTwoArr(int[] a, int[] b, int[] c) {
        if(a.length != b.length) {
            System.out.println("数组长度不同");
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if(a[i] == b[i])
                continue;

            System.out.println("有值不同：\n    原数组：" + printlnArr(c) + "；\n"
                    + "    排序1：" + printlnArr(a) + "；\n"
                    + "    排序2：" + printlnArr(b) + "。");
            return false;
        }
        return true;
    }


    public static int[] createRandomIntArr(int arrLength, int numRange) {

        int[] arr = new int[arrLength];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(numRange);
        }
        return arr;
    }

    public static int[] createRandomIntArr() {
        return createRandomIntArr(random.nextInt(100), 100);
    }

    public static String printlnArr(int[] arr) {
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if(i != arr.length - 1)
                sb.append(", ");
        }
        return sb.toString();
    }

}
