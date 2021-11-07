package com.xyb.a1sync;

/**
 * 线程间通信，即当对象的A线程完成时，唤醒对象的B线程。
 * 交替执行2个线程，A：当为0时加1，B：当为1时-1，C同A，D同B。
 */
public class A2SyncThreadCorrespond {

    // 第3步，创建多个线程，调用资源类的操作方法；
    public static void main(String[] args) {

        Share share = new Share();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    share.inc();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "aa").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    share.dec();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "bb").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    share.inc();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "cc").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    share.dec();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "dd").start();

    }
}

/**
 * 第1步，创建资源类，在资源类创建属性和操作方法；
 */
class Share {

    private int number = 0;

    // +1的方法
    public synchronized void inc() throws InterruptedException {
        // 第2步 判断 干活 通知
        /*
        * 注意，此处不能用if，必须用while，因为wait会释放锁，且被唤醒时，接着从此处运行，如果是if，在此处被唤醒时，就不符合number != 0 的条件了(这就是虚假唤醒)，
        * 所以每次被唤醒时，必须重新进行判断，就像飞机上的安检一样，每次有事下飞机，再次上飞机都要重新安检。
        * */
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "：" + number);

        // 通知其它线程
        this.notifyAll();
    }

    // -1的方法
    public synchronized void dec() throws InterruptedException {
        // 第二步 判断 干活 通知
        while (number != 1) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "：" + number);

        // 通知其它线程
        this.notifyAll();
    }


}
