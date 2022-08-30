package com.xyb.a4Tree;

import com.xyb.utils.Utils;

import java.util.*;

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
     * 后序遍历，非递归
     * @param node
     * @return
     */
    public static void headTailNoRecusive(Node head) {
        /**
         * 中序遍历的递归形式，所以我们要按 右、中、左的顺序压栈
         * f(node) {
         *      f(node.left);
         *      f(node.right)
         *      sout(node.val);
         * }
         */
        StringBuilder sb = new StringBuilder("后序遍历(非递归)：\n    ");
        if(head == null)
            return;
        // 什么时候进行打印需要一个标准，就是弹出栈的时候进行打印
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node tmp;
        while (!stack.isEmpty()) {
            if (head.left == null && head.right == null) {
                head = stack.pop();
                sb.append(head.value);
            }

            if (head.right != null) {
                stack.push(head.right);
            }

            if (head.left != null) {
                stack.push(head.left);
            }

        }
        System.out.println(sb.toString());
    }


    /**
     * 中序遍历，非递归
     * @param node
     * @return
     */
    public static void headMiddleNoRecusive(Node head) {
        /**
         * 中序遍历的递归形式，所以我们要按 右、中、左的顺序压栈
         * f(node) {
         *      f(node.left);
         *      sout(node.val);
         *      f(node.right)
         * }
         */
        StringBuilder sb = new StringBuilder("中序遍历(非递归)：\n    ");
        if(head == null)
            return;
        // 什么时候进行打印需要一个标准，就是弹出栈的时候进行打印
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                sb.append(head.value + ", ");
                head = head.right;
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * 中序遍历，非递归
     * @param node
     * @return
     */
    public static void headMiddleNoRecusive2(Node head) {
        /**
         * 中序遍历的递归形式，所以我们要按 右、中、左的顺序压栈
         * f(node) {
         *      f(node.left);
         *      sout(node.val);
         *      f(node.right)
         * }
         */
        StringBuilder sb = new StringBuilder("中序遍历(非递归)：\n    ");
        if(head == null)
            return;
        // 什么时候进行打印需要一个标准，就是弹出栈的时候进行打印
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {

            stack.push(head);

            if (head.left == null) {
                head = stack.pop();
                sb.append(head.value);
                if (head.right == null) {

                }
            }

            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                sb.append(head.value + ", ");
                head = head.right;
            }
        }
        System.out.println(sb.toString());
    }


    /**
     * 先序遍历，非递归
     * @param node
     * @return
     */
    public static void headFirstNoRecusive(Node head) {
        /**
         * 先序遍历的递归形式
         * f(node) {
         *      sout(node.val);
         *      f(node.left);
         *      f(node.right)
         * }
         */
        StringBuilder sb = new StringBuilder("先序遍历(非递归)：\n    ");
        if(head == null)
            return;
        // 什么时候进行打印需要一个标准，就是弹出栈的时候进行打印
        Stack<Node> stack = new Stack<>();
        stack.push(head);

        while (!stack.isEmpty()) {
            head = stack.pop();
            // 然后要先处理left，再处理right，因为left要先处理，所以要放到上面，即后压栈。
            if(head.right != null)
                stack.push(head.right);

            if(head.left != null)
                stack.push(head.left);

            sb.append(head.value + ", ");
        }
        System.out.println(sb.toString());
    }

    /**
     * 宽度优先遍历
     */
    public static void widthTraversal(Node node) {
        Queue<Node> queue = new LinkedList();

        StringBuilder sb = new StringBuilder("宽度优先遍历：\n    ");

        queue.add(node);
        Node tmpNode;
        while (queue.peek() != null) {
            tmpNode = queue.poll();
            sb.append(tmpNode.value + ", ");
            queue.add(tmpNode.left);
            queue.add(tmpNode.right);
        }
        System.out.println(sb);
    }

    public static void recursiveTraversals(Node node, int model) {
        StringBuilder sb = new StringBuilder("");

        switch (model) {
            case 1:
                sb.append("先序遍历(递归)：\n    ");
                sb.append(recursiveTraversal(node, 1));
                break;
            case 2:
                sb.append("中序遍历(递归)：\n    ");
                sb.append(recursiveTraversal(node, 2));
                break;
            case 3:
                sb.append("后序遍历(递归)：\n    ");
                sb.append(recursiveTraversal(node, 3));
                break;
        }
        System.out.println(sb.toString());
    }

    /**
     * 递归序遍历，递归序遍历，每个节点都可以被操作3次，但其中2次有可能会什么也不做。
     * 如headNode，会在其中调用leftNode和rightNode的递归，在操作leftNode前、
     * leftNode和rightNode中、rightNode后，都可以操作headNode，所以我们有3次操作
     * headNode的机会，在这3次机会中挑一次打印，就会成为先、中、后序遍历。
     *
     * 对于叶节点也是一样的。
     *
     * @param model 1、2、3：分别在第1位、第2位、第3位打印head节点。即先、中、后序遍历。
     */
    private static String recursiveTraversal(Node node, int model) {
        if (node == null)
            return "";

        StringBuilder sb = new StringBuilder("");
        if (model == 1) // 先打印头，先序遍历
            sb.append(node.value + ", ");

        sb.append(recursiveTraversal(node.left, model));

        if (model == 2) // 先打印左，再打印头。中序遍历。
            sb.append(node.value + ", ");

        sb.append(recursiveTraversal(node.right, model));

        if (model == 3) // 先左、再右、后头。后序遍历。
            sb.append(node.value + ", ");

        return sb.toString();
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
//        System.out.println(Utils.printlnArr(arr));

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
