package com.xyb.a9executeengine;

/**
 * 执行引擎3种模式速度比较：
 * -Xint：仅解释执行； 7650ms
 * -Xcomp：仅编译执行； 1315ms
 * -Xmixed：混合执行； 1228ms
 */
public class ExeEngineModel1 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        testPrimeNumber(1000000);

        System.out.println(System.currentTimeMillis() - start);

    }

    public static void testPrimeNumber(int count){
        for (int i = 0; i < count; i++) {
            label:
            for (int j = 2; j <= 100; j++) {
                for (int k = 2; k <= Math.sqrt(j); k++) {
                    if(j % k == 0)
                        continue label;
                }
                
            }
        }
    }

}
