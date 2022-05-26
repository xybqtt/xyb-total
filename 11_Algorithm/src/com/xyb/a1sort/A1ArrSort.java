package com.xyb.a1sort;

import com.xyb.utils.Utils;

import java.util.Arrays;

/**
 * 数组排序
 */
public class A1ArrSort {

    public static void main(String[] args) {

        int[] arr = Utils.createRandomIntArr(7, 20);
        System.out.println("原数组打印：" + Utils.printlnArr(arr));
        // 选择排序
        selectionSort(Arrays.copyOf(arr, arr.length));

        // 冒泡排序
        bubbleSort(Arrays.copyOf(arr, arr.length));

        // 插入排序
        insertSort(Arrays.copyOf(arr, arr.length));




    }

    /**
     * 插入排序，
     * 从前往后遍历，
     * 把当前位置的数依次与前一个作比较，如果比前面小，就交换2个位置，再与前面一个作比较，
     * 重复此步骤，直到，前面一个比当前数小。
     * 接着开始遍历下一个。
     *
     * @param arr
     */
    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--)
                Utils.swap(arr, j, j - 1);

        }

        System.out.println(Utils.printlnArr(arr) + "。插入排序。");


    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1])
                    Utils.swap(arr, j, j + 1);
            }
        }
        System.out.println(Utils.printlnArr(arr) + "。冒泡排序。");
    }

    /**
     * 选择排序：
     * 将当前位置的数与后面所有数依次比较，选出其中最小的数，
     * 与当前位置的数交换。
     * 依次对后面的每个数据进行同样操作。
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        int minPos;
        for (int i = 0; i < arr.length - 1; i++) {
            minPos = i;

            // 从arr下标大于等于i的所有数据中，找出最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minPos] > arr[j])
                    minPos = j;
            }

            if (minPos == i)
                continue;

            // 交换minPos、i位置上的值
            Utils.swap(arr, minPos, i);
        }

        System.out.println(Utils.printlnArr(arr) + "。选择排序。");

    }


}
