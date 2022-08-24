package com.xyb.a3linkedlist;

import java.util.Stack;

/**
 * 判断一个单链表(无环)是否是回文
 */
public class A1Palindromic {

    public static void main(String[] args) {

        SingleLinkedList list = SingleLinkedList.createSingleList(4, 20);
        System.out.println("原链表：" + list.print());

        // 用栈，最简单
        stackMethod(list);

        // 使用快慢指针
        fastAndSlowPointer(list);

    }

    /**
     * 使用快慢指针，且空间复杂度为O(1)
     */
    private static void fastAndSlowPointer(SingleLinkedList oriList) {

        SingleLinkedList list = oriList.copy();

        if(list.headNode == null || list.headNode.next == null)
            return;

        SingleNode fastPos = list.headNode.next;
        SingleNode slowPos = list.headNode;
        boolean oddFlag = true; // list是否是奇数个长度，false：不是。

        // 查找中间位置
        while (fastPos != null) {
            if (fastPos.next == null) { // 偶数个节点，此时fast在末尾，slow在中间前一个。
                oddFlag = false;
                break;
            }
            fastPos = fastPos.next;
            slowPos = slowPos.next; // 写在这，如果是偶数个，则fast到终点时，slow在中点前一个。是奇数个，则fast到中点时，slow中最中间那个。
            if (fastPos.next == null) { // 奇数个节点，此时fast在末尾，slow在正中间。
                oddFlag = true;
                break;
            }
            fastPos = fastPos.next;
        }
        SingleNode headNode = list.headNode;
        SingleNode tailNode = fastPos;

        // 后半截链表反转，如果是偶数个，则后半截第一个重新指向null
        SingleNode tmpNode;
        SingleNode nowNode = slowPos.next;
        SingleNode preNode = (oddFlag ? slowPos : null);
        slowPos.next = null;

        while (nowNode != null) {
            tmpNode = nowNode.next;
            nowNode.next = preNode;
            preNode = nowNode;
            nowNode = tmpNode;
        }

        System.out.println("fastAndSlowPointer，打印前半截链表：" + headNode.print());
        System.out.println("fastAndSlowPointer，打印后半截链表：" + tailNode.print());

        // 判断是否回文
        while (headNode != null) {
            if(!headNode.data.equals(tailNode.data)) {
                System.out.println("fastAndSlowPointer不是回文：" + oriList.print());
                return;
            }
            headNode = headNode.next;
            tailNode = tailNode.next;
        }

        System.out.println("fastAndSlowPointer是回文：" + oriList.print());


    }

    /**
     * 把 node 压栈，再弹栈和原链表做比较
     *
     * @param list
     */
    private static void stackMethod(SingleLinkedList oriList) {

        SingleLinkedList list = oriList.copy();

        Stack<SingleNode> stack = new Stack<>();

        // 所有node压栈
        SingleNode tmpNode = list.headNode;
        while (tmpNode != null) {
            stack.push(tmpNode);
            tmpNode = tmpNode.next;
        }

        // 比较是否是回文
        tmpNode = list.headNode;
        while (tmpNode != null) {
            if (!tmpNode.data.equals(stack.pop().data)) {
                System.out.println("stackMethod不是回文：" + oriList.print());
                return;
            }
            tmpNode = tmpNode.next;
        }
        System.out.println("stackMethod是回文：" + oriList.print());

    }

    private static void f3() {
    }

    private static void f2() {
    }

    /**
     * 单链表压栈，再从链表头和弹栈的数据比较是否完全一致，一致则是回文
     *
     * @param list
     */
    private static void f1(SingleLinkedList list) {


    }
}

