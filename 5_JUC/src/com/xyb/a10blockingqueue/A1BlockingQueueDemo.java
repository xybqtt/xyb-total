package com.xyb.a10blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 演示阻塞队列的核心方法
 */
public class A1BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        // 创建阻塞队列
        BlockingQueue bq = new ArrayBlockingQueue(2);

        // 查看第一组"插入、取出、取出队首元素"的方法，不成功抛出异常
        f1(bq);

        // 第2组，不成功返回null或false
        f2(bq);

        // 第3组，不成功就阻塞直到成功
//        f3(bq);

        // 第3组，设置超时时间
        f4(bq);

    }

    /**
     * 查看第4组插入、取出
     * 第3组方法：
     * boolean offer(e, time时间, unit时间的单位)：向队列添加元素，如果队列已满，则在time过后，还是无法插入队列，则返回false，生产者线程即出。
     * E poll(time, unit)：从队列获取元素，如果队列已空，则阻塞消费者线程直到设定的time，则返回null，消费者线程即出。
     */
    public static void f4(BlockingQueue bq) throws InterruptedException {

        System.out.println("offer(e, time, unit)方法向阻塞队列添加元素(不成功，就阻塞，一直到成功或设定的超时时间返回false)：");
        System.out.println("    向阻塞队列添加元素，超时时间3s：" + bq.offer("a", 3, TimeUnit.SECONDS));
        System.out.println("    向阻塞队列添加元素，超时时间3s：" + bq.offer("b", 3, TimeUnit.SECONDS));
        System.out.println("    向阻塞队列添加元素，超时时间3s：" + bq.offer("c", 3, TimeUnit.SECONDS));
        // bq.put("c"); // 这次插入会阻塞


        System.out.println("-------------------------------------------------");
        System.out.println("poll方法向阻塞队列取出并移除队首元素(不成功，就阻塞，一直到成功或设定的超时时间)：");
        System.out.println("    从阻塞队列获取元素，超时时间3s：" + bq.poll(3, TimeUnit.SECONDS));
        System.out.println("    从阻塞队列获取元素，超时时间3s：" + bq.poll(3, TimeUnit.SECONDS));
        System.out.println("    从阻塞队列获取元素，超时时间3s：" + bq.poll(3, TimeUnit.SECONDS));

    }



    /**
     * 查看第3组插入、取出
     * 第3组方法：
     * put()：向队列添加元素，如果队列已满，则阻塞生产者线程直到数据被put进去，或超出设置时间。
     * take()：从队列获取元素，如果队列已空，则阻塞消费者线程直到队列中有数据。
     */
    public static void f3(BlockingQueue bq) throws InterruptedException {

        System.out.println("put方法向阻塞队列添加元素(不成功，就阻塞，一直到成功)：");
        bq.put("a");
        bq.put("b");
        System.out.println("    再次put时会阻塞");
        // bq.put("c"); // 这次插入会阻塞


        System.out.println("-------------------------------------------------");
        System.out.println("take方法向阻塞队列取出并移除队首元素(不成功，就阻塞，一直到成功)：");
        System.out.println("    从阻塞队列获取元素：" + bq.take());
        System.out.println("    从阻塞队列获取元素：" + bq.take());
        System.out.println("    从阻塞队列获取元素，此时会阻塞消费者，因为现在是空队列：" + bq.take());

    }


    /**
     * 查看第2组插入、取出、查看队首元素的方法
     * 第2组方法：offer、poll、peek
     * boolean offer()：添加成功返回true，向满队列中添加时返回false。
     * E poll()：返回并移除队首元素，若移除前为空队列，则返回null
     * E peek()：返回队首元素，若为空队列，则返回null。
     */
    public static void f2(BlockingQueue bq) {

        System.out.println("offer方法向阻塞队列添加元素，(成功返回true，队列已满返回false)：");
        System.out.println("    向阻塞队列添加元素a：" + bq.offer("a"));
        System.out.println("    向阻塞队列添加元素b：" + bq.offer("b"));
        System.out.println("    向阻塞队列添加元素c：" + bq.offer("c"));


        System.out.println("-------------------------------------------------");
        System.out.println("poll方法向阻塞队列取出并移除队首元素，(成功返回true，空队列，则返回null)：");
        System.out.println("    从阻塞队列获取元素：" + bq.poll());
        System.out.println("    从阻塞队列获取元素：" + bq.poll());
        System.out.println("    从阻塞队列获取元素：" + bq.poll());

        System.out.println("-------------------------------------------------");

        System.out.println("peek方法查看队首元素，(空队列，则返回null)：");
        bq.offer("a");
        System.out.println("    从阻塞队列查看队首元素：" + bq.peek());
        bq.poll();
        System.out.println("    从阻塞队列查看队首元素：" + bq.peek());

        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }

    /**
     * 查看第1组插入、取出、查看队首元素的方法
     * boolean add()：添加成功返回true，向满队列中添加时抛出异常。
     * E remove()：返回并移除队首元素，若移除前为空队列，则抛出异常。
     * E element()：返回队首元素，若为空队列，则抛出异常。
     */
    public static void f1(BlockingQueue bq) {

        // 第1组方法：add、element、remove
        // bq.add()方法，将元素插入队列，true成功，false失败，如果无空闲位置，抛异常
        System.out.println("add方法向阻塞队列添加元素，成功返回true，超过限制抛出异常：");
        System.out.println("    向阻塞队列添加元素a：" + bq.add("a"));
        System.out.println("    向阻塞队列添加元素b：" + bq.add("b"));
        try {
            System.out.println("向阻塞队列添加元素c：" + bq.add("c"));
        } catch (Exception e) {
            System.out.println("    当队列已满时再添加，会抛出异常");
        }


        System.out.println("-------------------------------------------------");
        System.out.println("remove方法获取并移除队首元素(队列为空，则会抛出异常)：");
        System.out.println("    获取并移除队首元素：" + bq.remove());
        System.out.println("    获取并移除队首元素：" + bq.remove());
        try {
            System.out.println("获取并移除队首元素(队列已空，则会抛出抛异常)：" + bq.remove());
        } catch (Exception e) {
            System.out.println("    当队列为空时，再次remove会抛出异常");
        }

        System.out.println("-------------------------------------------------");

        System.out.println("element方法查看队首元素(队列为空，则抛出异常)：");
        bq.add("a");
        System.out.println("    查看队首元素：" + bq.element());
        bq.remove();
        try {
            System.out.println("    查看空队列队首元素：" + bq.element());
        } catch (Exception e) {
            System.out.println("    查看空队列队首元素时会抛出异常。");
        }

        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }

}
