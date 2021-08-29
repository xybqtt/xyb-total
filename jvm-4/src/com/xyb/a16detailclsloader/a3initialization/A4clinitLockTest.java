package com.xyb.a16detailclsloader.a3initialization;

/**
 * <clinit>()方法带有隐式锁(即不在方法的标志中)，下面演示如何触发死锁
 */
public class A4clinitLockTest {

    /**
     * 2个类的<clinit>()方法中互相调用对方类的<clinit>()方法，且暂停1s，就会造成死锁
     * @param args
     */
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ClinitLock1 c1 = new ClinitLock1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ClinitLock2 c2 = new ClinitLock2();
            }
        }).start();

        System.out.println("全部初始化完毕");

    }


}

/**
 * 2个类在静态块中互相调用对应的类<clinit>()方法
 */
class ClinitLock1 {

    static {
        try {
            Thread.sleep(1000);
            System.out.println("c1调用c2前");
            Class cls1 = Class.forName("com.xyb.a16detailclsloader.a3initialization.ClinitLock2");
            System.out.println("c1调用c2后");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

class ClinitLock2 {

    static {
        try {
            Thread.sleep(1000);
            System.out.println("c2调用c1前");
            Class cls1 = Class.forName("com.xyb.a16detailclsloader.a3initialization.ClinitLock1");
            System.out.println("c2调用c1后");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
