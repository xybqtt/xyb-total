package com.xyb.a3linkedlist;

import com.xyb.utils.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * 单链表，其实一个headNode就是一个单链表，此处为了生成链表方便，
 * 创建了一个SingleLinkedList.
 * 
 */
class SingleLinkedList {
    
    public SingleNode headNode;

    public SingleNode tailNode;

    private int length;

    public SingleLinkedList copy() {
        SingleNode tmpNode = headNode;
        SingleLinkedList list = new SingleLinkedList();

        while (tmpNode != null) {
            list.addNode(tmpNode.data);
            tmpNode = tmpNode.next;
        }
        return list;
    }

    public SingleLinkedList addNode(SingleNode singleNode) {

        if (headNode == null)
            headNode = singleNode;
        if (tailNode == null)
            tailNode = singleNode;

        if (tailNode != null) {
            tailNode.next = singleNode;
            tailNode = tailNode.next;
        }
        length++;
        return this;
    }

    public SingleLinkedList addNode(String val) {
        SingleNode singleNode = new SingleNode(val);
        return addNode(singleNode);
    }

    public SingleLinkedList addNode(int val) {
        SingleNode singleNode = new SingleNode(val + "");
        return addNode(singleNode);
    }

    /**
     * 可打印带环的单链表
     *
     * @return
     */
    public String print() {
        StringBuilder sb = new StringBuilder();
        SingleNode tmpNode = headNode;
        Map<SingleNode, Integer> map = new HashMap<>();
        while (tmpNode != null) {
            if (map.get(tmpNode) != null) {
                sb.append("这是环起点：" + tmpNode.data + "(" + tmpNode.hashCode() + ")");
                break;
            } else {
                map.put(tmpNode, 1);
            }
//            sb.append(tmpNode.data + "(" + tmpNode.hashCode() + "), ");
            sb.append(tmpNode.data + ", ");
            tmpNode = tmpNode.next;
        }
        return sb.toString();
    }

    /**
     * 创建2个单链表，长度分别为num和num2，让这2个单链表相交。
     *
     * @param num       链表1长度
     * @param num2      链表2长度
     * @param numRange  链表每个Node元素范围
     * @param hasCircle 2个链表是否有环。true：有环。false：无环。
     * @return
     */
    public static SingleLinkedList[] createTwoMeetSingleList(int num, int num2, int numRange, boolean hasCircle) {
        int max = num >= num2 ? num : num2;
        int min = num < num2 ? num : num2;

        int meet = new Random().nextInt(min);
        meet = min - meet;

        int[] meetArr = Utils.createRandomIntArr(meet, numRange);
        SingleLinkedList meetList = new SingleLinkedList();
        for (int i = 0; i < meetArr.length; i++) {
            meetList.addNode(meetArr[i]);
        }
        if (hasCircle)
            meetList.tailNode.next = meetList.headNode;

        int[] longArr = Utils.createRandomIntArr(max - meet, numRange);
        SingleLinkedList longList = new SingleLinkedList();
        for (int i = 0; i < longArr.length; i++) {
            longList.addNode(longArr[i]);
        }

        int[] shortArr = Utils.createRandomIntArr(min - meet, numRange);
        SingleLinkedList shortList = new SingleLinkedList();
        for (int i = 0; i < shortArr.length; i++) {
            shortList.addNode(shortArr[i]);
        }

        shortList.tailNode.next = meetList.headNode;
        longList.tailNode.next = meetList.headNode;

        return new SingleLinkedList[]{longList, shortList};
    }


    /**
     * 创建有环单链表，链表长度为 num，每个节点的数字范围 为 numRange
     *
     * @param num      链表长度
     * @param numRange 链表每个Node元素范围
     * @return
     */
    public static SingleLinkedList createCircleSingleList(int num, int numRange) {
        int[] arr = Utils.createRandomIntArr(num, numRange);

        SingleLinkedList list = new SingleLinkedList();
        Arrays.stream(arr).forEach(i -> {
            list.addNode(i);
        });

        int tmp = new Random().nextInt(num - 1);
        SingleNode tmpNode = list.headNode;
        for (int i = 0; i <= tmp; i++) {
            tmpNode = tmpNode.next;
        }

        list.tailNode.next = tmpNode;

        return list;
    }

    /**
     * 创建单链表，链表长度为 num，每个节点的数字范围 为 numRange
     *
     * @param num      链表长度
     * @param numRange 链表每个Node元素范围
     * @return
     */
    public static SingleLinkedList createSingleList(int num, int numRange) {
        int[] arr = Utils.createRandomIntArr(num, numRange);

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        Arrays.stream(arr).forEach(i -> {
            singleLinkedList.addNode(i);
        });
        return singleLinkedList;
    }

    /**
     * 创建节点包含随机指针的单链表
     *
     * @param num
     * @param numRange
     * @return
     */
    public static SingleLinkedList createContainRandomSingleList(int num, int numRange) {
        int[] arr = Utils.createRandomIntArr(num, numRange);
        SingleNode[] nodes = new SingleNode[arr.length];

        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new SingleNode(arr[i]);
        }

        for (int i = 1; i < nodes.length; i++) {
            nodes[i - 1].next = nodes[i];
        }


        for (int i = 0; i < nodes.length; i++) {
            nodes[i].random = nodes[new Random().nextInt(num)];
        }

        SingleLinkedList list = new SingleLinkedList();
        list.headNode = nodes[0];

        return list;
    }

    /**
     * 打印包含随机指针的链表
     *
     * @return
     */
    public String printWithRandom() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        SingleNode tmpNode = headNode;
        while (tmpNode != null) {
            sb.append(tmpNode.data + ", ");
            sb1.append(tmpNode.random.data + ", ");
            tmpNode = tmpNode.next;
        }
        sb.append("\n").append(sb1);
        return sb.toString();
    }

    /**
     * 创建回文单链表
     *
     * @param num
     * @param numRange
     * @return
     */
    public static SingleLinkedList createPalindromicSingleList(int num, int numRange) {
        int[] arr = Utils.createRandomIntArr(num / 2, numRange);

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        Arrays.stream(arr).forEach(i -> {
            singleLinkedList.addNode(i);
        });

        if (num % 2 == 1)
            singleLinkedList.addNode(Utils.createRandomIntArr(1, numRange)[0]);

        for (int i = arr.length - 1; i >= 0; i--)
            singleLinkedList.addNode(arr[i]);

        return singleLinkedList;
    }
}

