package com.xyb.a12forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 实现1 + 2 + 3 + ... + 100
 * 要求，拆分任务的时候，第1个值与最后1个值的差值不能大于10。
 * 用ForkJoin是将任务拆分，并对拆分的任务使用多线程去处理，不是简单的递归操作。
 */
public class A1ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建MyTask对象
        MyTask myTask = new MyTask(0, 100);

        // 创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);

        // 获取最终合并之后的结果
        Integer result = forkJoinTask.get();
        System.out.println(result);

        // 关闭池对象
        forkJoinPool.shutdown();
    }

}

class MyTask extends RecursiveTask<Integer> {

    // 拆分差值不能超过10，计算10以内运算
    private static final Integer VALUE = 10;

    // 拆分开始值
    private int begin;

    // 拆分结束值
    private int end;

    // 返回结果
    private int result;


    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    /**
     * 在此处实现拆分和合并过程
     * @return
     */
    @Override
    protected Integer compute() {
        // 判断相加的2个数值是否大于10
        if(this.end - begin <= VALUE) {

            System.out.println("计算" + this.begin + "~~~" + this.end + "的和");
            // 相加
            for(int i = this.begin; i <= this.end; i++)
                result += i;

        } else {
            // 继续拆分
            // 获取中间值
            int middle = (this.begin + this.end) / 2;

            // 拆分中间值的左边
            MyTask leftTask = new MyTask(this.begin, middle);

            // 拆分中间值的右边
            MyTask rightTask = new MyTask(middle + 1, this.end);

            System.out.println("这次Fork的左任务是：开始=" + this.begin + "，middle=" + middle);
            System.out.println("这次Fork的右任务是：middle+1=" + (middle + 1) + "，end=" + this.end);
            System.out.println();
            System.out.println();

            // 调用方法拆分，此处不用这个也能用递归实现，但这样做会是多线程同时处理，速度快。
            leftTask.fork();
            rightTask.fork();

            // 合并结果
            this.result = leftTask.join() + rightTask.join();

        }

        return this.result;
    }
}















