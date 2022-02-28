package com.xyb.a8assistcls;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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

    private static Scanner scanner = new Scanner(System.in);

    // 用于线程的wait()和notifyAll()
    private static Object obj = new Object();

    public static void main(String[] args) {
        // 创建CyclicBarrier，这个第一个number参数代表有几个线程达到标时，运行后面的Runnable。
        CyclicBarrier cb = new CyclicBarrier(NUMBER, () -> {

            System.out.println();
            System.out.println();
            try {
                order += NUMBER;
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
                    // 锁住当前类，让所有的输出语句不要乱。
                    synchronized (A2CyclicBarrier.class) {
                        System.out.println("流程" + (++order / NUMBER) + "：" + Thread.currentThread().getName() + "'点击确定'，当前有" + cb.getNumberWaiting() + "个线程到达'点击确定'栅栏(除自己，因为没触发await())");
                    }

                    if ("召唤师1".equals(Thread.currentThread().getName())) {
                        // 测试各种异常情况

                        System.out.println("输入想测试的异常：1、当前线程进行reset()；2、当前线程设置等待时间上限；3、当前线程进行interrupt()；其它、进行await()。");
                        switch (scanner.nextLine()) {
                            case "1":
                                /**
                                 * 当前线程重置栅栏(其它线程执行cb.reset()效果相同)，所有已经await()的线程会抛出BrokenBarrierException异常，
                                 * 在捕获异常后，会继续向下运行，但会造成非预期的情况。
                                 * 预期如下：
                                 *      1、2、3、4、5线程运行，在栅栏A等待，
                                 *      全部到达A后，5个线程再运行，到达栅栏B，
                                 *      全部到达B后，再往后运行。
                                 *
                                 * 调用reset()的异常情况：注意等待数不区分是哪个栅栏
                                 *      操作      A上的等待线程   B上的等待线程     总等待线程     后续操作
                                 *               1、2                          2
                                 *      reset()
                                 *               3、4、5        1、2            5
                                 *      注意第3行await()的数量达到了5，但是有2个是到达了B，3个到达了A，与
                                 *      我们的预期1、2、3、4、5同时到达A或B不一致。
                                 */
                                cb.reset();
                                break;
                            case "2":
                                // 当前线程等待超时后，抛出TimeoutException，且其它所有线程抛出BrokenBarrierException，并继续执行后面流程
                                cb.await(2, TimeUnit.MICROSECONDS);
                                break;
                            case "3":
                                // 当前线程中断，抛出InterruptedException异常，对其它线程无影响。
                                Thread.currentThread().interrupt();
                                break;
                            default:
                                // 正常情况
                                cb.await();
                        }
                    } else {
                        cb.await();
                    }

                } catch (InterruptedException e) {
                    showCBInfo(cb, e);
                } catch (BrokenBarrierException e) {
                    showCBInfo(cb, e);
                } catch (TimeoutException e) {
                    showCBInfo(cb, e);
                }


                try {
                    if ("召唤师1".equals(Thread.currentThread().getName())) {
                        TimeUnit.SECONDS.sleep(5); // 停几s，让其它所有线程先wait()，查看此时的cb.getNumberWaiting()是否为0，即已经被重置。
                        System.out.println();
                        System.out.println();
                        order += NUMBER;
                        System.out.println("流程" + (order / NUMBER) + "：当前已经有" + cb.getParties() + "人冲破'点击确定'栅栏，当前等待人数为" + cb.getNumberWaiting() + "。是否唤醒全部线程：是1，没有否。");
                        notifyAllThread();
                    } else {
                        // 除了'召唤师1'，其它线程先暂停，为了查看冲破'点击确定'栅栏后，的状态
                        waitThread();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                try {
                    // '选人'
                    TimeUnit.SECONDS.sleep(finalI * 2);
                    synchronized (A2CyclicBarrier.class) {
                        System.out.println("流程" + (++order / NUMBER) + "：" + Thread.currentThread().getName() + "'选人完成'，当前有" + cb.getNumberWaiting() + "个人到达'选人完成'栅栏(除自己，因为没触发await()))");
                    }
                    cb.await();

                    TimeUnit.SECONDS.sleep(5);
                    if ("召唤师1".equals(Thread.currentThread().getName())) {
                        order += NUMBER;
                        System.out.println("流程" + (order / NUMBER) + "：当前已经有" + cb.getParties() + "人冲破'选定英雄'栅栏，当前等待人数为" + cb.getNumberWaiting());
                    }

                } catch (InterruptedException e) {
                    showCBInfo(cb, e);
                } catch (BrokenBarrierException e) {
                    showCBInfo(cb, e);
                }
            }, "召唤师" + i).start();
        }
    }

    private static void showCBInfo(CyclicBarrier cb, Exception e) {
        System.out.println(Thread.currentThread().getName() + "：栅栏破坏=" + cb.isBroken() + "；当前等待人数=" + cb.getNumberWaiting() + "；异常：" + getStackTraceInfo(e));
    }

    private static void waitThread() throws InterruptedException {
        synchronized (obj) {
            obj.wait();
        }
    }

    private static void notifyAllThread() {
        synchronized (obj) {
            if(scanner.nextInt() == 1) {
                obj.notifyAll();
                System.out.println("已经唤醒其它所有线程");
                System.out.println();
                System.out.println();
            }
        }
    }

    /**
     * 将e.printStackTrace()的内容输出为字符串
     * @param e
     * @return
     */
    public static String getStackTraceInfo(Exception e) {

        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);//将出错的栈信息输出到printWriter中
            pw.flush();
            sw.flush();

            return sw.toString();
        } catch (Exception ex) {

            return "发生错误";
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }

    }


}
