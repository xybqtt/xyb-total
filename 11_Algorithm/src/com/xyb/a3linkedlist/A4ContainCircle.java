package com.xyb.a3linkedlist;

/**
 * 判断单链表有无环，并找到环最开始的节点。
 * 方法一：O(N) O(N) Map<Node, int>，当某一个Node在Map中已存在时，就是环起点，不演示了。
 * 方法二：O(N) O(1) 快慢指针，当第一次指针相交时，将快指针放到开头，慢指针前进一格，之后2个指针一次走一个节点，再次相遇时，就是环起点。
 *
 */
public class A4ContainCircle {

    public static void main(String[] args) {
        SingleLinkedList list = SingleLinkedList.createCircleSingleList(20, 20);
        System.out.println("复制前：\n" + list.print());

        SingleNode circleNode = getCircleBeginNode(list);
        if(circleNode == null)
            System.out.println("不是环");
        else
            System.out.println("是环，环起点：" + circleNode.data + "(" + circleNode.hashCode() + ")");
        
    }

    /**
     * 快慢指针。
     *
     * @param list
     */
    public static SingleNode getCircleBeginNode(SingleLinkedList list) {
        SingleNode head = list.headNode;

        SingleNode fast = head.next;
        SingleNode slow = head;

        boolean circleFlag = false;

        while (fast.next != null && fast.next.next != null) {
            if(fast == slow) {
                circleFlag = true;
                break;
            }
            fast = fast.next.next;
            slow = slow.next;

        }

        if(!circleFlag) // 不是环
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
