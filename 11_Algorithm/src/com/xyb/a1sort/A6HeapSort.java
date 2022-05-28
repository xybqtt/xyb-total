package com.xyb.a1sort;

import com.xyb.utils.Utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 堆排序
 * 我们可以把数组想你成堆，且每一个小三角形的顶点是父节点，2个底角是子节点。
 * 图中的数字是在数组中的下标。
 * 大根堆的意思是，父 >= 子。小根堆相反。
 *                   A0
 *          A1                  A2
 *      A3       A4        A5        A6
 *   A7   A8  A9   A10  A11  A12  A13  A14
 * 如何根据父位置确定子位置，
 *      左子i = 父i * 2 + 1；
 *      右子i = 父i * 2 + 2；
 *      父i = (左子i - 1) / 2;
 *      父i = (右子i - 2) / 2 = (右子i - 1) / 2;
 *      注意(int)(6 - 2) / 2 = (int)(6 - 1) / 2 = 2所以，知道左右子的位置，都可以通过(int)(子i - 1) / 2计算
 *
 * 堆(大根堆举例)有2个重要操作：查看下面代码
 *      堆插入：即在堆最后插入一个数字时，将堆数据位置进行变换，使之再次符合大根堆。
 *      堆化 heapify：即在堆的任一位置(除了末尾)删除一个数字后，将堆数据位置进行变换，使之再次符合大根堆。
 *
 * 堆排序(大根堆举例)：
 *      当已经是大根堆后，可以保证，堆顶的数字必然是最大的，
 *      将堆顶数字先存储起来，然后就当堆顶数字不见了，进行 heapify 操作，完成后，又是大根堆，
 *      再按上面操作，当堆中没有数字后，就排序结束了。
 */
public class A6HeapSort {

    public static void main(String[] args) {
        int[] arr = Utils.createRandomIntArr(7, 20);
        System.out.println("原数组打印：" + Utils.printlnArr(arr));

        int[] result = heapSort(Arrays.copyOf(arr, arr.length));
        System.out.println("排序后结果(自己实现的堆)：\n" + Utils.printlnArr(result));


        // java自带的小根堆用法
        result = priorityQueue(Arrays.copyOf(arr, arr.length));
        System.out.println("排序后结果(java自带的堆实现)：\n" + Utils.printlnArr(result));

    }

    /**
     * java自带的小根堆的用法。
     * PriorityQueue默认是小根堆，导致poll()、peek()出的元素，只能是最小值，
     * 给构造方法中传入比较器，可以决定是最小堆，还是最大堆，当变成最大堆后，poll()、peek()
     * 的元素就是最大值。
     *
     * @param ints
     */
    private static int[] priorityQueue(int[] a) {

        // 这样写就是大根堆了，就可以倒序输出
//        PriorityQueue priorityQueue = new PriorityQueue(new Comparator() {
//            /**
//             * 我们可以认为 o1 < o2 (即升序)，想让谁放在前面，就用  这个 - 另一个
//             * @param o1
//             * @param o2
//             * o1 = o2，则2者无顺序。
//             * @return
//             */
//            @Override
//            public int compare(Object o1, Object o2) {
//                return (int)o2 - (int)o1;
//            }
//        });

        PriorityQueue priorityQueue = new PriorityQueue();


        for (int i = 0; i < a.length; i++)
            priorityQueue.add(a[i]); // 把元素都给添加进去

        int i = 0;
        while (priorityQueue.peek() != null) // peek()：检索但不删除此队列的头，如果此队列为空，则返回null
            a[i++] = (int) priorityQueue.poll(); // poll()：返回队列头元素，如果队列为空，返回null

        /**
         * 其它方法：
         *      remove()：检索并删除此队列的头，如果此队列为空会出现异常
         *      comparator()：返回用于排序此队列中元素的比较器，如果此队列是根据其元素的自然顺序排序的，则返回null
         *      contains()：返回队列中是否包含所给元素
         *      size()：返回所含的元素数量
         *
         */

        return a;
    }

    /**
     *
     * @param arr
     */
    private static int[] heapSort(int[] arr) {

        int heapSize = 0; // 堆大小
        // 将堆排成大根堆
        for(int i = 0; i < arr.length; i++)
            heapInsert(arr, heapSize++, arr[i]);

        int largest;
        // 依次从堆头中取数，直到没有堆
        for(int i = arr.length - 1; i >= 0; i--) {
            largest = arr[0];
            heapify(arr, heapSize--, 0);
            arr[i] = largest;
        }

        return arr;

    }

    /**
     * 大根堆的堆化。即在堆内的任一位置(除最后)删除一个数时，剩下的数据如果变化使之符合大根堆。
     *                   A0
     *          A1                  A2
     *      A3       A4        A5        A6
     *   A7   A8  A9   A10  A11  A12  A13  A14
     * 思想：以删除 A2 举例
     *      1、因为不管A2下所有子树怎么变幻，都不会改变A0的位置，所以不用考虑A2的父节点；
     *      2、将堆中最后一位数A14放到被删除的位置A2上。
     *      3、接着我们只要把A2与下面的子节点进行比较，将其放到合适的位置上，这个就又符合大根堆了。
     *
     * @param a 数组
     * @param heapSize 堆大小
     * @param num
     */
    private static void heapify(int[] a, int heapSize, int index) {

        a[index] = a[--heapSize]; // 将堆中最右的数据替换到被index位，并让 heapSize - 1

        int leftChild = (index * 2 + 1); // 被删除节点的左下节点

        int largest; // 小堆中最大的数的下标
        // 当 leftChild < heapSize时，证明有子节点存在
        while (leftChild < heapSize) {

            largest = leftChild + 1 < heapSize && a[leftChild] < a[leftChild + 1] ? // 有右节点更在
                    leftChild + 1 : // 返回右节点
                    leftChild; // 返回左节点

            largest = a[index] >= a[largest] ? index : largest; // 选出"父"和"2个子节点中的较大值"中的较大值

            if(index == largest) // 父节点就是此堆中最大节点，就不用往下跑了
                break;

            Utils.swap(a, largest, index);

            index = largest;
            leftChild = index * 2 + 1;
        }

    }

    /**
     * 大根堆的堆插入。即从后面新增一位时，如何使之再符合大根堆。
     * 思想：每个插到最后的数和它的父做比较，如果比它父大，那么交换之后，这个小堆依然是大根堆，
     * 再将 爷爷和父比，同样的操作，直到有一层父比子大，或者，到达了堆根节点。
     * 第2个参数的调用方，需要在调用方法结束后，将heapSize + 1。
     * @param arr 数组
     * @param heapSize 堆现在大小。类似数组长度，不是数组最后一位。
     * @param num 被插入的数
     */
    private static void heapInsert(int[] a, int heapSize, int insNum) {

        a[heapSize++] = insNum;

        int largest = heapSize - 1;

        while (a[largest] > a[(largest - 1) / 2]) {
            // 当前节点大于父，则交换二者位置，或当前节点是数组0位置(a[0] > a[-1 / 2] = a[0]也会地跳出循环)
            Utils.swap(a, largest, (largest - 1) / 2);
            largest = (largest - 1) / 2;
        }
    }


}













