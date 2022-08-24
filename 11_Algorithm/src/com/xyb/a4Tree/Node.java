package com.xyb.a4Tree;

import com.xyb.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
class Node {

    public int value;

    public Node left;

    public Node right;

    public Node(int data) {
        this.value = data;
    }

    /**
     * 宽度优先遍历
     */
    public static void widthTraversal(Node node) {
        Queue<Node> queue = new LinkedList();

        queue.add(node);
        Node tmpNode;
        while (queue.peek() != null) {
            tmpNode = queue.poll();
            System.out.print(tmpNode.value + ", ");
            queue.add(tmpNode.left);
            queue.add(tmpNode.right);
        }
    }

    /**
     * 递归序遍历
     *
     * @param model 1、2、3：分别在第1位、第2位、第3位打印head节点。即先、中、后序遍历。
     */
    public static void recursiveTraversal(Node node, int model) {
        if (node == null)
            return;
        if (model == 1) // 先打印头，先序遍历
            System.out.print(node.value + ", ");

        recursiveTraversal(node.left, model);

        if (model == 2) // 先打印左，再打印头。中序遍历。
            System.out.print(node.value + ", ");

        recursiveTraversal(node.right, model);

        if (model == 3) // 先左、再右、后头。后序遍历。
            System.out.print(node.value + ", ");
    }

    /**
     * 创建一个普通的树
     *
     * @param high      树高
     * @param numRanger 数范围
     * @return
     */
    public static Node createByHigh(int high, int numRanger) {

        int count = 0;
        for (int i = 0; i < high; i++) {
            count += Math.pow(2, i);
        }

        int[] arr = Utils.createNoRepeatArr(count, numRanger);
        System.out.println(Utils.printlnArr(arr));

        Node headNode = new Node(arr[0]);

        findChild(headNode, arr, 0);

        return headNode;
    }

    private static void findChild(Node parentNode, int[] arr, int nowPos) {

        int leftPos = nowPos * 2 + 1;
        int rightPos = nowPos * 2 + 2;

        if (leftPos < arr.length) {
            Node leftNode = new Node(arr[leftPos]);
            parentNode.left = leftNode;
            findChild(leftNode, arr, leftPos);
        }

        if (rightPos < arr.length) {
            Node rightNode = new Node(arr[rightPos]);
            parentNode.right = rightNode;
            findChild(rightNode, arr, rightPos);
        }
    }

}
