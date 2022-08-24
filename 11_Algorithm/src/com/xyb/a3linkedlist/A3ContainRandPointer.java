package com.xyb.a3linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 单链表的Node，不仅指向下一个Node，还会指向此链表随机一个Node或null。
 * 复制这个链表。
 */
public class A3ContainRandPointer {

    public static void main(String[] args) {
        SingleLinkedList list = SingleLinkedList.createContainRandomSingleList(10, 20);
        System.out.println("复制前：\n" + list.printWithRandom());

        copyWithMap(list);

        method2(list);

    }

    /**
     * 另一种方法
     *
     * @param list
     */
    private static void method2(SingleLinkedList list) {
        SingleNode head = list.headNode;

        while (head != null) {
            // 原来是 1 > 2，现在是 1 > 1` > 2
            SingleNode headCopyNode = new SingleNode(head.data);
            headCopyNode.next = head.next;
            head.next = headCopyNode;
            head = headCopyNode.next;
        }

        head = list.headNode;
        while (head != null) {
            // 将随机指针也给赋值
            head.next.random = head.random.next;
            head = head.next.next;
        }

        // 把复制链表给拆解出来
        head = list.headNode;
        SingleNode copyHead = head.next;
        SingleNode tmpNode = null;
        while (head != null) {
            // 将随机指针也给赋值
            if(tmpNode == null) {
                tmpNode = head.next;
            } else {
                tmpNode.next = head.next;
                tmpNode = tmpNode.next;
            }
            // 18  18-  11 11-  12  12-
            // copyhead：18- > 11-
            // head：18 > 11

            if(head.next == null)
                break;
            head = head.next.next;
        }

        System.out.println("method2 复制含随机指针链表结果：\n" + copyHead.printWithRandom());

    }

    private static void copyWithMap(SingleLinkedList list) {

        SingleNode head = list.headNode;

        // 把节点先复制一遍，并放到map中
        Map<SingleNode, SingleNode> map = new HashMap<>();
        while (head != null) {
            SingleNode copyNode = new SingleNode(head.data);
            map.put(head, copyNode);
            head = head.next;
        }

        head = list.headNode;
        while (head != null) {
            map.get(head).next = map.get(head.next);
            if (map.get(head.random) != null)
                map.get(head).random = map.get(head.random);
            head = head.next;
        }

        SingleNode copyHead = map.get(list.headNode);

        System.out.println("copyWithMap复制后：\n" + copyHead.printWithRandom());

    }

}
