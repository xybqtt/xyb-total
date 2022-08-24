package com.xyb.a3linkedlist;

/**
 * 链表相交：
 * 如果是无环链表，那么先遍历2个链表，查看最后一个Node是否是同一个，如果是，则再次从头遍历，长链表先走n(n为长链表 - 短链表 个数)个，再
 * 依次比较长短链表的Node是不是同一个，如果是同一个，则为2个链表的第一个交点。
 * <p>
 * 如果是有环链表相交，则2个链表必定都有环，且共用同一个环。
 * 先找到2个链表各自的环起点，查看是不是同一个，
 * 是：则证明在还没有到环或刚到环的时候已经相交，像上面无环链表的方式就可以查到交点。
 * 不是：则必定2个链表的所有相交点都在环上(不可能在非环部分)，则遍历一个链表的环，查看另一个链表的环起点是否在环上，在则2链表相交，不在则不相交。
 */
public class A5TwoSingleListMeet {

    public static void main(String[] args) {
        SingleLinkedList[] lists = SingleLinkedList.createTwoMeetSingleList(20, 15, 20, true);
        System.out.println("原链表：\n" + lists[0].print() + "\n" + lists[1].print());

        SingleNode meetNode = getMeetBegin(lists[0], lists[1]);

        System.out.println(meetNode.data);


    }

    /**
     * 得到相交点
     *
     * @param list
     * @param list1
     * @return
     */
    private static SingleNode getMeetBegin(SingleLinkedList list1, SingleLinkedList list2) {

        // 得到2个链表中每个链表环的起点
        SingleNode circle1BeginNode = A4ContainCircle.getCircleBeginNode(list1);
        SingleNode circle2BeginNode = A4ContainCircle.getCircleBeginNode(list2);

        if ((circle1BeginNode == null && circle2BeginNode != null) || (circle1BeginNode != null && circle2BeginNode == null)) {
            // 一个有环，一个无环，肯定不相交。
            return null;
        }

        if (circle1BeginNode == null) {
            // 2个无环单链表单相交问题
            return dealNoCircleMeet(list1, list2);
        }

        if (circle1BeginNode != null) {
            // 2个有环单链表相交问题
            return dealHasCircleMeet(list1, list2);
        }

        return null;
    }

    /**
     * 2个有环单链表相交问题
     *
     * @param list1
     * @param list2
     * @return
     */
    private static SingleNode dealHasCircleMeet(SingleLinkedList list1, SingleLinkedList list2) {
        SingleNode circle1BeginNode = A4ContainCircle.getCircleBeginNode(list1);
        SingleNode circle2BeginNode = A4ContainCircle.getCircleBeginNode(list2);


        if (circle1BeginNode == circle2BeginNode) {
            // 环起点相同，证明在环起点及之前就有可能相遇，那这就是2个无环单链表相交问题
            SingleNode list1TmpNode = circle1BeginNode.next;
            SingleNode list2TmpNode = circle2BeginNode.next;

            circle1BeginNode.next = null;
            circle2BeginNode.next = null;

            return dealNoCircleMeet(list1, list2);
        }

        SingleNode tmpNode = circle1BeginNode;
        circle1BeginNode = circle1BeginNode.next;
        while (true) {
            if (tmpNode == circle1BeginNode)
                return null;

            if (circle1BeginNode == circle2BeginNode)
                return circle1BeginNode;

            circle1BeginNode = circle1BeginNode.next;
        }

    }

    /**
     * 2个无环链表相交问题
     *
     * @param list1 第一个链表
     * @param list2 第2个链表
     * @return 交点，无交点返回null。有交点返回交点。
     */
    private static SingleNode dealNoCircleMeet(SingleLinkedList list1, SingleLinkedList list2) {
        SingleNode tailNode1 = list1.headNode;
        SingleNode tailNode2 = list2.headNode;

        int list1Len = 1;
        int list2Len = 1;

        while (tailNode1.next != null) {
            tailNode1 = tailNode1.next;
            list1Len++;
        }

        while (tailNode2.next != null) {
            tailNode2 = tailNode2.next;
            list2Len++;
        }

        if (tailNode1 != tailNode2) {
            // 证明没有相交点
            return null;
        }

        // 证明有相交点
        boolean list1Long = list1Len > list2Len ? true : false;
        int tmp = list1Long ? list1Len - list2Len : list2Len - list1Len;


        if (list1Long) {
            // list1更长
            tailNode1 = list1.headNode;
            for (int i = 1; i < (list1Len - list2Len); i++) {
                tailNode1 = tailNode1.next;
            }

            tailNode2 = list2.headNode;
            tailNode1 = tailNode1.next;
        } else { // list2更长
            tailNode2 = list2.headNode;
            for (int i = 1; i < (list2Len - list1Len); i++) {
                tailNode2 = tailNode2.next;
            }
            tailNode1 = list1.headNode;
            tailNode2 = tailNode2.next;
        }

        if (tailNode1 == tailNode2)
            return tailNode1;

        while (tailNode1 != tailNode2) {
            tailNode1 = tailNode1.next;
            tailNode2 = tailNode2.next;
        }
        return tailNode1;
    }

    /**
     * 另一种方法
     *
     * @param list
     */
    public static SingleNode getCircleBeginNode(SingleLinkedList list) {
        SingleNode head = list.headNode;

        SingleNode fast = head.next;
        SingleNode slow = head;

        boolean circleFlag = false;

        while (fast.next != null && fast.next.next != null) {
            if (fast == slow) {
                circleFlag = true;
                break;
            }
            fast = fast.next.next;
            slow = slow.next;

        }

        if (!circleFlag) // 不是环
            return null;

        fast = head;
        slow = slow.next;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

}
