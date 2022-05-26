package com.xyb.a2bitopera;

/**
 * 异或运算
 */
public class A1XOR {


    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 1, 2, 3, 4, 4, 4};
        // 查找数组中出现奇数次的数(只有一个出现奇数次)
        f1(arr);

        arr = new int[]{1, 1, 2, 2, 2, 3, 3, 4, 4, 4};
        // 查找数组中出现奇数次的数(只有2个出现奇数次)
        f2(arr);

    }

    /**
     * 查找数组中出现奇数次的数(只有2个出现奇数次，且这2个数不相等)，
     * 如[a, a, a, b, b, b, c, c, d, d, d, d]
     * 称这2个出现奇数次的数为a、b。
     * @param arr
     */
    private static void f2(int[] arr) {

        int eor = 0;
        int onlyOne = 0;
        /**
         * 计算出 a ^ b = c，且因为 a != b(题中已知)，所以c ！= 0。
         */
        for(int i = 0; i < arr.length; i++)
            eor ^= arr[i];

        /**
         * 因为 a ^ b = c != 0，所以必定至少在某一位i上， a[i] != b[i]，即
         * c[i] = 0
         * 我们根据i上位数为1的所有数据进行异或，就能得出a：
         *     同一种数的i位，必定相等，所以相同的数必定会分在同一组中，偶数个相同的数异或结果是0。
         * 再将i上位数为0的所有数据进行异或，就能得出b；
         */
        // 提取出最右的1，如 1100 1100取出后变为 0000 0100
        int rightOne = eor & (~eor + 1);

        //
        for(int cur : arr) {
            // 将 cur 和 rightOne进行与操作，将结果为0和不为0的分组，计算其中异或
            if((cur & rightOne) == 0)
                onlyOne ^= cur;
        }

        // 计算另一个
        eor ^= onlyOne;
        System.out.println("2个出现奇数次的数为：" + eor + "，" + onlyOne);

    }


    /**
     * 数组中有1种数字出现了奇数次，其它数字出现了偶数次，找出出现奇数次的数字。
     * 空间O(1)
     */
    public static void f1(int[] arr) {

        int eor = 0;
        /**
         * 原理 a ^ a = 0，0 ^ 0 = 0
         * 出现偶数次则为相当于有 每一对 a ^ a = 0，但不管多个0 ^ 0 = 0。
         * 所以偶数个 a 异或，结果是0。
         * 同理，奇数个a 异或，结果是 a ^ 0 = a。
         *
         */
        for(int i = 0; i < arr.length; i++)
            eor ^= arr[i];

        System.out.println("出现奇数次的数为：" + eor);

    }

}
