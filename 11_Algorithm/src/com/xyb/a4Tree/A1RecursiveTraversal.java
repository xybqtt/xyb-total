package com.xyb.a4Tree;

/**
 * 树的递归遍历。前、中、后序的递归遍历。
 * 宽度优先遍历。
 */
public class A1RecursiveTraversal {

    public static void main(String[] args) {

        Node node = Node.createByHigh(4, 20);

        // 宽度优先遍历
        Node.widthTraversal(node);

        // 前序遍历(递归)
        Node.recursiveTraversals(node, 1);

        // 中序遍历(递归)
        Node.recursiveTraversals(node, 2);

        // 后序遍历(递归)
        Node.recursiveTraversals(node, 3);
    }

}
