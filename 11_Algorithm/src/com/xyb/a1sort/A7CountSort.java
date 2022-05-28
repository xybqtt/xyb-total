package com.xyb.a1sort;

import com.xyb.utils.Utils;

import java.util.Arrays;

/**
 * 计数排序：
 *     1、先遍历一遍数组arr，把出最大值max和最小值min；
 *     2、开辟长度为 max 的数组arr2；
 *     3、再遍历一遍数组，此时遍历第i个元素，则 arr2[i - 1]++；
 *     4、等arr遍历完成后，就可以人arr2上知道，每个数字对应的个数，arr2的下标就是
 *     arr的元素值。
 * 复杂度O(n + k)
 * 只适合极值较小的情况。
 */
public class A7CountSort {

    public static void main(String[] args) {
        int[] arr = Utils.createRandomIntArr(7, 20);
        System.out.println("原数组打印：\n" + Utils.printlnArr(arr) + "\n");

        int[] result = countSort(Arrays.copyOf(arr, arr.length));
        System.out.println("排序后结果(自己实现的堆)：\n" + Utils.printlnArr(result) + "\n");
    }

    /**
     * 计数排序
     * @param copyOf
     * @return
     */
    private static int[] countSort(int[] a) {

        int max = a[0];
        int min = a[0];
        // 获取最大最小值
        for (int i = 0; i < a.length; i++) {
            max = (max > a[i] ? max : a[i]);
            min = (min < a[i] ? min : a[i]);
        }

        // 计数
        int[] help = new int[max + 1];
        for (int i = 0; i < a.length; i++)
            help[a[i]]++;

        // 返回原数组中
        int aPos = 0;
        for (int i = 0; i < help.length; i++) {
            if(help[i] == 0)
                continue; // 证明a中元素 = i的有0个。

            int endPos = aPos + help[i];
            for (; aPos < endPos; aPos++) {
                a[aPos] = i;
            }
        }

        return a;

    }

}
