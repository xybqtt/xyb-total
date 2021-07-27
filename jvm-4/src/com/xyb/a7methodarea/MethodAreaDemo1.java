package com.xyb.a7methodarea;

import java.util.Scanner;

/**
 * 设置方法区的大小：
 * -XX:Permsize=40m -XX:MaxPermsize=40m
 *
 * -XX:MetaspaceSize=40m -XX:MaxMetaspaceSize=40M
 *
 * 可用java命令查看方法区大小。
 * */
public class MethodAreaDemo1 {

    public static void main(String args[]) throws InterruptedException {
        Thread.sleep(200000);
        Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
    }

}
