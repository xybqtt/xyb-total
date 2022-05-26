package com.xyb.a1sort;

import com.xyb.utils.Utils;

import java.util.Arrays;

/**
 * 归并排序，二分排序，流程如下：1、2、9、8、5、4、3
 * 二分为2组：               1、2、9、8     5、4、3
 * 再次2分：              1、2   9、8    5、4   3
 * 一直分到不能2分：      1   2   9   8   5   4   3
 * 再次每组合并并排序，如：
 *      1   2合并为 1、2
 *      9  8合并为 8、9
 *      1、2   8、9 合并为 1、2、8、9
 *
 *      5  4 合并为 4、5
 *      4、5   3 合并为3、4、5
 *
 *      1、2、8、9   3、4、5合并为1、2、3、4、5、8、9
 * 时间复杂度O(N*logN)，额外空间复杂度O(N)。
 * 注意，在处理小和问题是，左右2侧数组合并排序时，如果左右两侧的某个数相等，先把右侧放到临时数组中。
 *
 *
 */
public class A4MergeSort {

    public static void main(String[] args) {
        int[] arr = Utils.createRandomIntArr(7, 20);
        System.out.println("原数组打印：" + Utils.printlnArr(arr));
        // 归并排序、小和问题
        mergeSort(Arrays.copyOf(arr, arr.length));

        // 小和问题
    }

    private static void mergeSort(int[] arr) {
        int smallSum = process(arr, 0, arr.length - 1);
        System.out.println(Utils.printlnArr(arr) + "。归并排序。小和：" + smallSum);
    }

    public static int process(int[] arr, int leftPos, int rightPos) {
        if (leftPos == rightPos)
            return 0;

        int midPos = leftPos + ((rightPos - leftPos) >> 1);

        int smallSum = 0;

        // 将左侧排序，并求出小和
        smallSum += process(arr, leftPos, midPos);

        // 将右侧排序，并求出小和
        smallSum += process(arr, midPos + 1, rightPos);

        // 合并左侧和右侧进行排序，并求出小和
        smallSum += merge(arr, leftPos, midPos, rightPos);
        return smallSum;
    }

    /**
     * 将左右的有有序排列合并，例：
     *  1、2、7  与  3、5、6  合并后是 1、2、3、5、6、7
     * @param arr
     * @param leftPos
     * @param midPos
     * @param rightPos
     * @return 合并过程中产生的小和
     */
    private static int merge(int[] arr, int leftPos, int midPos, int rightPos) {
        int[] tmpArr = new int[rightPos - leftPos + 1];
        // 左右2部分数组的最左位置下标
        int p1 = leftPos;
        int p2 = midPos + 1;

        int i = 0;

        int smallSum = 0;
        while (p1 <= midPos && p2 <= rightPos) {
            //
            /**
             * 处理小和问题，1、2、4    3、5、6，只要左侧的a比右侧b小，那么就a就小于b及其后面所有的数。
             * 如 2比3小，那么右侧大于 2的数的个数就有 从3开始到结束所有的数，3、5、6。
             * 如果 4比3大，那么就不加了，等到 4 < 5时处理。
             */
            smallSum += arr[p1] < arr[p2] ? arr[p1] * (rightPos - p2 + 1) : 0;

            // 进行排序，把arr[p1]、arr[p2]较小值放到 i 位上。注意如果处理小和问题，
            // 如果左右2侧数相等，必须先把右侧放到tmpArr中。
            // 如 1122  11122，计算"合并"小和是1 * 2 + 1 * 2，如果先把左侧放进去，则计算小和的结果是 1 * 5 + 1 * 5。
            tmpArr[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p2 <= rightPos) {
            tmpArr[i++] = arr[p2++];
        }

        while (p1 <= midPos) {
            tmpArr[i++] = arr[p1++];
        }

        for(i = 0; i < tmpArr.length; i++) {
            arr[leftPos++] = tmpArr[i];
        }

        return smallSum;

    }

}
