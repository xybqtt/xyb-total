package com.xyb.a4Tree;

/**
 * 树的递归遍历。前、中、后序的递归遍历。
 * 宽度优先遍历。
 */
public class A1RecursiveTraversal {

    public static void main(String[] args) {

        Node node = Node.createByHigh(4, 20);

        System.out.println("宽度优先遍历：");
        Node.widthTraversal(node);

        System.out.println("\n前序遍历：");
        Node.recursiveTraversal(node, 1);

        System.out.println("\n中序遍历：");
        Node.recursiveTraversal(node, 2);

        System.out.println("\n后序遍历：");
        Node.recursiveTraversal(node, 3);
    }

}
