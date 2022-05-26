package com.xyb.utils;

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
