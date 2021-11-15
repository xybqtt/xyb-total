package com.xyb.a8assistcls;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CyclicBarrier的用法：
 * 一组线程达到某一个公共屏障点(common barrier point)时，唤醒相关线程。
 * 集齐7龙珠召唤神龙。
 * 实现例子：lol要10个人点击确定后，进入选人界面，再等10个人选完英雄后，再进入战斗界面，如果在前面任一环节超时，则退出。
 */
public class A2CyclicBarrier {

    // 创建固定值
    private static final int NUMBER = 5;

    //  记录CyclicBarrier的执行顺序
    private static int order = NUMBER - 1;

    private static int runOrder = 0;

    public static void main(String[] args) {
        // 创建CyclicBarrier，这个第一个number参数代表有几个线程达到标时，运行后面的Runnable。
        CyclicBarrier cb = new CyclicBarrier(NUMBER, () -> {

            int a = 5 / 0;
            System.out.println();
            System.out.println();
            try {
                order += 10;
                runOrder++;
                System.out.println("流程" + (order / NUMBER) + "：第" + runOrder + "次运行CyclicBarrier的runnable参数。");
                TimeUnit.SECONDS.sleep(3);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
            System.out.println();
        });


        for (int i = 0; i < NUMBER; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    // 每个人停止 i * 2的时间，
                    TimeUnit.SECONDS.sleep(finalI * 2);
                    synchronized (A2CyclicBarrier.class) {
                        System.out.println("流程" + (++order / NUMBER) + "：" + Thread.currentThread().getName() + "'点击确定'，当前有" + cb.getNumberWaiting() + "个线程到达'点击确定'栅栏(除自己，因为没触发await())");
                    }

                    if ("召唤师1".equals(Thread.currentThread().getName())) {
                        // 测试各种异常情况
                        Scanner scanner = new Scanner(System.in);
                        switch (scanner.nextLine()) {
                            case "1":
                                cb.reset(); // 测试当前线程重置，其它线程是否会抛出BrokenBarrierException异常。
                                break;
                            case "2":
                                cb.await(2, TimeUnit.SECONDS); // 测试当前线程等待超时后，是否会抛出TimeoutException，其它线程是否会抛出BrokenBarrierException
                                break;
                            case "3":
                                break;
                            default:
                                cb.wait();
                        }

                    } else {
                        cb.await();
                    }


                    if ("召唤师1".equals(Thread.currentThread().getName())) {
                        order += NUMBER;
                        System.out.println("流程" + (order / NUMBER) + "：当前已经有" + cb.getParties() + "人冲破'点击确定'栅栏，已重置到达栅栏人数为" + cb.getNumberWaiting());
                    }


                    // 停6s，保证在进行任何一个人到达'选人完成'栅栏前，让召唤师1输出上面那句话，且此时的cb.getNumberWaiting()为0，即没人到达'选人完成'栅栏。
                    TimeUnit.SECONDS.sleep(6);


                    // '选人'，每个人随机时间确定
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    synchronized (A2CyclicBarrier.class) {
                        System.out.println("流程" + (++order / NUMBER) + "：" + Thread.currentThread().getName() + "'选人完成'，当前有" + cb.getNumberWaiting() + "个人到达'选人完成'栅栏(除自己，因为没触发await()))");
                    }
                    cb.await();

                    TimeUnit.SECONDS.sleep(5);
                    if ("召唤师1".equals(Thread.currentThread().getName())) {
                        order += NUMBER;
                        System.out.println("流程" + (order / NUMBER) + "：当前已经有" + cb.getParties() + "人冲破'选定英雄'栅栏，已重置到达栅栏人数为" + cb.getNumberWaiting());
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    System.err.println(Thread.currentThread().getName() + "：" + cb.isBroken() + "---" + cb.getNumberWaiting());
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    System.err.println(Thread.currentThread().getName() + "：" + cb.isBroken() + "---" + cb.getNumberWaiting());
                    e.printStackTrace();
                }
            }, "召唤师" + i).start();
        }
    }

}
