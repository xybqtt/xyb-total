package com.xyb.basic1;

/**
 * 只讲下loop中的label的用法：
 * 1.break可以用来跳出一层循环。
 * 2.如果加上标签，在break后添上标签名，可以用来跳出任意层循环。
 * 3.标签与for语句之间不能有其他任何语句，即使是另一个for循环也不行。
 * 4.java中的break不能像C语言中的goto一样使用。
 * 5.for循环与while循环中的标签同样使用方法。
 *  */
public class Loop3 {

    public static void main(String[] args) {


        for(int i = 1; i <= 5; i++){
            // 此处的label代表label下面的循环体，break label是指跑出这个label代表的循环，不是从label再开始运行。
            // 但是continue，是跳出此次循环，进行下一次循环
            label:
            for(int j = 0; j <= 10; j++){
                System.out.println(i * j);
                if(j == 3)
                    break label;
            }
        }


    }

}
