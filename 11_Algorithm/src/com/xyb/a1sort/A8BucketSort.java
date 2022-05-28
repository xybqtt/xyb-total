package com.xyb.a1sort;

import com.xyb.utils.Utils;

import java.util.Arrays;
import java.util.Random;

/**
 * 桶排序：举例
 * 举例数组a：211、112、121、123、142、133、256、089
 * 1、先按个位数放桶：先放10个桶，0 - 9，按照顺序放，先放进去的要先出来
 *     |     |211  |112  |123  |     |     |256 |     |     |089
 *     |     |121  |142  |133  |     |     |    |     |     |
 *     |     |     |     |     |     |     |    |     |     |
 *     |0    |1    |2    |3    |4    |5    |6   |7    |8    |9  ：这是桶
 * 从左到右，每个桶按按先进先出的规则放出来的结果是：这样的结果保证了个位数小的在前
 *     211、121、112、142、123、133、256、089
 *
 *     此处可以再优化下：
 *         b = new int[10]：用于存入元素个位的个数(计数排序法)，b[i]代表a中个位是i的有多少个。
 *         结果如下：表示个数数是0的有0位、个位是1的有2个
 *              b：[0, 2, 2, 2, 0, 0, 1, 0, 0, 1];
 *         此时，再次从左往右，把前一个值加到后一个上，结果如下：
 *              b：[0, 2, 4, 6, 6, 6, 7, 7, 7, 8];
 *              当i = 2，b[i] = 4代表个位 <= 2的有4个；
 *              当i = 3，b[i] = 5代表个位 <= 3的有6个。
 *         然后，再将a数组 "从后往前" 插入备份数组c(c和a一样大小，就是用来备份a的)中：
 *             我们先处理a[7] = 089，因为其个位是9，找到b[9] = 8，8表示个位 <= 9的最大位置，
 *             此时我们将a[7] 插入 c[8]，
 *             然后让b[9--]，表示 个位 <= 9的最大位置现在只能放到第7位了。
 *             为什么要"从后往前"遍历a？
 *             看上面的图，自己想一下，就知道，从前往后遍历为什么不行了。
 *
 *         当准备插入 a[7] = 089时，此时其个位是9，查询b[9--]的值，此值就是a[9]要插入到c中的位置。
 *              因为b[i] 代表了 个位 <= i的最大下标，又因为我们根据a从后往前遍历，所以这个a[9]就应该是
 *              其个位对应位置的最后一个。
 *              下面十位、百倍都可以用这样的方法
 *
 * 2、再将1中的结果按10位数放桶，
 *     |     |211  |121  |133  |142  |256  |    |     |089  |
 *     |     |112  |123  |     |     |     |    |     |     |
 *     |     |     |     |     |     |     |    |     |     |
 *     |0    |1    |2    |3    |4    |5    |6   |7    |8    |9  ：这是桶
 * 从左到右，每个桶按按先进先出的规则放出来的结果是：这样的结果保证了十位数小的在前，但
 *     同时是根据个位排序的结果放的，所以只要按顺序放，先进先出，就能保证10位数相同的情况下，
 *     个位数小的先进桶，倒出来后，也会在前面。
 *     211、112、121、123、133、142、256、089

 * 3、再将2中的结果按百位数放桶，
 *     |089  |112  |211  |     |     |     |    |     |     |
 *     |     |121  |256  |     |     |     |    |     |     |
 *     |     |123  |     |     |     |     |    |     |     |
 *     |     |133  |     |     |     |     |    |     |     |
 *     |     |142  |     |     |     |     |    |     |     |
 *     |0    |1    |2    |3    |4    |5    |6   |7    |8    |9  ：这是桶
 *    倒出来后：
 *    089、112、121、123、133、142、211、256。原理同前面一样。
 *
 */
public class A8BucketSort {

    public static void main(String[] args) {

        // 对数器
//        compareTest();

        int[] arr = Utils.createRandomIntArr(7, 20);
        System.out.println("原数组打印：\n" + Utils.printlnArr(arr) + "\n");

        int[] result = bucketSort(Arrays.copyOf(arr, arr.length));
        System.out.println("桶排序后结果：\n" + Utils.printlnArr(result) + "\n");

        Arrays.sort(arr);
        System.out.println("Arrays排序序后结果：\n" + Utils.printlnArr(result) + "\n");
    }

    /**
     * 对数器测试
     */
    private static void compareTest() {

        for (int i = 0; i < 10000; i++) {
            int[] arr = Utils.createRandomIntArr(new Random().nextInt(20), 20);
            int[] result1 = bucketSort(Arrays.copyOf(arr, arr.length));
            int[] result2 = Arrays.copyOf(arr, arr.length);
            Arrays.sort(result2);
            boolean flag = Utils.compareTwoArr(result1, result2, arr);
            System.out.println(i + "：" + (flag ? "" : flag) );
        }
    }

    /**
     * 桶排序
     * @param a
     * @return
     */
    private static int[] bucketSort(int[] a) {

        int[] help = new int[a.length]; // 弄一个数组副本
        int maxbit = maxBits(a); // 获取源数组中，最大一位数据包含了多少位10进制
        for (int i = 0; i < maxbit; i++) { // 有多少位，就进行多少次循环
            int[] bitMaxIndex = new int[10]; // 查找a元素在可排序的位置上的最后一位
            for (int j = 0; j < a.length; j++) {
                // 取出a[j]的某一位上的数字，将这个数字作为bitMaxIndex[index]，将上面的数加1
                int bitNum = (a[j] / ((int) Math.pow(10, i))) % 10;
                bitMaxIndex[bitNum]++;
            }

            // 将bitMaxIndex从前往后，依次将当前下标(当前下标对应的值会变化的，是本位值加上一位的值)数字加到后一位上
            // bitMaxIndex里面只保存了 <= 下标的数有多少个，具体要根据此下标对应的元素设置副本值时，需要 - 1
            for(int j = 1; j < bitMaxIndex.length; j++)
                bitMaxIndex[j] += bitMaxIndex[j - 1];

            // 将a中的数字按此位(如个位)的排序结果从后往前遍历插入副本数组中
            for (int j = a.length - 1; j >= 0; j--) {
                int bitNum = (a[j] / ((int) Math.pow(10, i))) % 10;
                help[--bitMaxIndex[bitNum]] = a[j];
            }


            for (int j = 0; j < a.length; j++) {
                a[j] = help[j];
            }
        }

        return help;
    }

    /**
     * 找出数组中最大元素有几个10进制位。
     * 如[5, 10, 200]，200最大，有3个10进制位。
     * @param a
     */
    private static int maxBits(int[] a) {

        int max = Integer.MIN_VALUE;

        // 找出最大值
        for (int i = 0; i < a.length; i++)
            max = Math.max(max, a[i]);

        int maxbit = 0;

        // 找出最大值的最大的10进制位。
        while (max != 0) {
            maxbit++;
            max /= 10;
        }

        return maxbit;
    }

}
