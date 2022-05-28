package com.xyb.a1sort;

import com.xyb.utils.Utils;

import java.util.Arrays;

/**
 * 快速排序。
 * 在数组中随便选一个数(假设是最后一个，下标 = k = a.length - 1)，进行分区操作：荷兰国旗问题。
 * 复杂度：O(n * logn)
 */
public class A5QuickSort {

    public static void main(String[] args) {
        int[] arr = Utils.createRandomIntArr(7, 20);
        System.out.println("原数组打印：" + Utils.printlnArr(arr));
        quickSort(arr);
        System.out.println("排序后结果：\n" + Utils.printlnArr(arr));
    }

    private static void quickSort(int[] a) {
        if(a.length <= 1)
            return;
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int[] a, int l, int r) {

        if(l >= r)
            return;

        int[] arr = partition(a, l, r);
        quickSort(a, l, arr[0]);
        quickSort(a, arr[1], r);
    }

    /**
     *
     * @param a
     * @param l 需要处理数据的左侧边界
     * @param r 需要处理数据的右侧回轮
     * @return int[]{小于基准的右侧边界, 大于基准的左侧边界}
     */
    private static int[] partition(int[] a, int l, int r) {

        // 选基准，并将基准放到边上(左右都行，但写法不一样)，下面以放到右侧为例
        Utils.swap(a, r, l + (int)(Math.random() * (r - l + 1)));

        int less = l - 1; // "<区"的边界为 l - 1，因为不知道l与a[r]的大小关系
        int more = r; // ">区"的边界为r，因为r已经被设置为基准数了，后面只要把基准和more的位置一换就可以了

        // 被判断的那位要小于">区"的左边界，因为">区"内的数据一定是被判断过的
        while (l < more) {
            if(a[l] < a[r])
                Utils.swap(a, ++less, l++);// 如果小于基准，就把 l、less + 1的值互换，less边界右移1
            else if(a[l] == a[r])
                l++; // 相等的话，处理下一位就行，
            else
                // 如果 > 基准，就把 l、more - 1的值互换，但是 l不能加1了，因为这个值还没有结过判断
                // 但为什么 a[l] < a[r]时，也经过互换，但进行了 l++？
                // 因为 less + 1 <= l，l之前的数据是经过判断是，所以不用再判断一次了。
                Utils.swap(a, l, --more);
        }

        Utils.swap(a, more++, r); // 替换more、r的值

        // 此处的less和more可能已经下标越界，但是没关系，在quickSort()中有l < r的判断，
        return new int[]{less, more};

    }

}









