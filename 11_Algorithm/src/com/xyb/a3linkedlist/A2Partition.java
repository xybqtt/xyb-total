package com.xyb.a3linkedlist;

/**
 * 把单链表根据某个值划分为 < 、= 、> 区。
 * 不考虑空间复杂度不写了，用3个List即可。
 * 考虑空间复杂度，用3对head、tail节点去保存这3个区域的头尾，遍历后，组合即可，注意有些区域可能为空，空指针。
 */
public class A2Partition {

    public static void main(String[] args) {
        SingleLinkedList list = SingleLinkedList.createSingleList(10, 20);
        System.out.println("分区前：" + list.print());

        int partNum = 10;
        // 开始分区
        SingleNode h1 = null;
        SingleNode t1 = null;
        SingleNode h2 = null;
        SingleNode t2 = null;
        SingleNode h3 = null;
        SingleNode t3 = null;

        SingleNode headNode = list.headNode;
        SingleNode tmpNode = null;
        while (headNode != null) {
            if(Integer.parseInt(headNode.data) == partNum) {
                if(h2 == null) {
                    h2 = headNode;
                    t2 = headNode;
                } else {
                    t2.next = headNode;
                    t2 = t2.next;
                }
            }

            if(Integer.parseInt(headNode.data) < partNum) {
                if(h1 == null) {
                    h1 = headNode;
                    t1 = headNode;
                } else {
                    t1.next = headNode;
                    t1 = t1.next;
                }
            }

            if(Integer.parseInt(headNode.data) > partNum) {
                if(h3 == null) {
                    h3 = headNode;
                    t3 = headNode;
                } else {
                    t3.next = headNode;
                    t3 = t3.next;
                }
            }
            tmpNode = headNode.next;
            headNode.next = null;
            headNode = tmpNode;
        }

        System.out.println("h1：" + (h1 == null ? null : h1.print()));
        System.out.println("h2：" + (h2 == null ? null : h2.print()));
        System.out.println("h3：" + (h3 == null ? null : h3.print()));

        if(h1 != null) {
            if(h2 != null) {
                t1.next = h2;
                t2.next = h3;
            } else {
                t1.next = h3;
            }
            list.headNode = h1;
        } else {
            if(h2 != null) {
                t2.next = h3;
                list.headNode = h2;
            } else {
                list.headNode = h3;
            }
        }

        System.out.println("以" + partNum + "分区后：" + list.print());


    }

}
