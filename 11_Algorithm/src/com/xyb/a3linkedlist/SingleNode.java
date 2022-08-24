package com.xyb.a3linkedlist;

class SingleNode {
    public String data;

    public SingleNode next;

    /**
     * 随机指针，某个题要用。
     */
    public SingleNode random;

    public SingleNode(String data) {
        this.data = data;
    }

    public SingleNode(int data) {
        this.data = data + "";
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        SingleNode tmpNode = this;
        while (tmpNode != null) {
            sb.append(tmpNode.data + ", ");
            tmpNode = tmpNode.next;
        }
        return sb.toString();
    }

    public String printWithRandom() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        SingleNode tmpNode = this;
        while (tmpNode != null) {
            sb.append(tmpNode.data + ", ");
            sb1.append(tmpNode.random.data + ", ");
            tmpNode = tmpNode.next;
        }
        return sb.toString() + "\n" + sb1.toString();
    }
}
