package com.xyb.a2j8features.a2stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 创建Stream的4种方式：
 * 方式1：通过集合
 *    Java 8的 Collection 接口被扩展，提供了两个获取流的方法：
 *    default Stream<E> stream()：返回一个顺序流，即按集合中的元素顺序操作；
 *    default Stream<E> parallelStream(): 返回一个并行流，即操作的时候，启动多点线程去处理，处理顺序就不一定是集合中的顺序了。
 *
 * 方式2：通过数组
 *    Java 8中的 Arrays 的静态方法 stream() 可以获取数组流
 *    调用 Arrays 类的 static <T> Stream<T> stream(T[] array): 返回一个流
 *    重载形式，能够处理对应基本类型的数组：
 *    public static IntStream stream（int[] array）
 *    public static LongStream stream（long[] array）
 *    public static DoubleStream stream（double[] array）
 *
 * 方式3：通过Stream的of()方法
 *    可以调用Stream类静态方法of()，通过显示值创建一个流。可以用于接收任意数量的参数：public static <T> Stream<T> of(T...values):返回一个流
 *
 * 方式4：创建无限流
 *    迭代: public static <T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
 *    生成: public static <T> Stream<T> generate(Supplier<T> s)
 */
public class A1CreateStream {

    public static void main(String[] args) {
        // 1、通过集合创建流
        createByCollect();

        // 2、通过数组创建流
        createByArrays();

        // 3、通过Stream.streamOf()
        createByStream();

        // 4、创建无限流
        createNoneLimitStream();

    }

    /**
     * 创建无限流，即如果没有限制可以一直添加元素的流
     * 迭代：
     *     Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);
     *     查看源码可知，return t = (t == Streams.NONE) ? seed : f.apply(t);如果t是null，第一次给t赋值seed，
     *         如果t不是null，则证明已经赋值过seed了，则调用f.apply(t)，即Lambda表达式 t = t + 2;，再返回给t，
     *         从开始到结束，seed是没有修改过的。
     */
    private static void createNoneLimitStream() {
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println); // 注意必须操作的是同一个类型

        Stream.generate(Math::random).limit(10).forEach(System.out::println);


    }

    /**
     * 通过stream创建流
     */
    private static void createByStream() {
        Stream stream = Stream.of(new int[]{1, 2, 3, 4});
        Stream stream2 = Stream.of(1, 2, 3, 4);
    }

    /**
     * 通过数组创建流
     */
    private static void createByArrays() {
        String[] strs = new String[]{"1", "2", "3", "4"};
        Stream<String> stream = Arrays.stream(strs); // 通过数组创建流

        IntStream intStream = Arrays.stream(new int[]{1, 2, 3, 4}); // 对于基本类型，有对应的基本类型流
    }

    /**
     * 通过jdk8新增的Collections.
     */
    public static void createByCollect() {
        String[] strs = new String[]{"1", "2", "3", "4"};
        List<String> list =  Arrays.asList(strs);
        Stream<String> stringStream = list.stream(); // 获取顺序流
        Stream<String> parallStream = list.parallelStream(); // 获取并行流
    }

}