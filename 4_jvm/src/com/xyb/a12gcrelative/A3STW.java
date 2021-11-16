package com.xyb.a12gcrelative;

import java.util.ArrayList;
import java.util.List;

/**
 * stw测试，gc会使项目暂时停止测试。
 */
public class A3STW {


    public static void main(String[] args) {
        // 每隔1s打印一次，如果进行了stw，则2次打印时间的间隔会大于1s
        new Thread(new Runnable() {
            long startTime = System.currentTimeMillis();


            @Override
            public void run() {
                try {
                    while (true) {
                        long t = System.currentTimeMillis() - startTime;
                        System.out.println(t / 1000 + "." + t % 1000);

                        Thread.sleep(1000);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 在这块new一个经常调用gc的线程，则上面的打印就不能按时打印了。
        new Thread(new Runnable() {
            public List list = new ArrayList();
            @Override
            public void run() {
                try{
                    while (true){
                        for (int i = 0; i < 1000; i++) {
                            byte[] bytes = new byte[1024];
                            list.add(bytes);
                        }

                        if(list.size() > 100000){
                            list.clear();
                            System.gc();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}
