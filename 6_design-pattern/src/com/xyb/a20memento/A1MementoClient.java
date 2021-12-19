package com.xyb.a20memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式
 */
public class A1MementoClient {

    public static void main(String[] args) {
        // 先设定一个状态
        A1Originator originator = new A1Originator();
        originator.setState("1");

        // 将这个状态记录到备忘录中
        A1Caretaker caretaker = new A1Caretaker();
        caretaker.saveMementoIF(originator.createMementoIF());

        // 再次更改状态
        originator.setState("2");
        caretaker.saveMementoIF(originator.createMementoIF());

        // 再次更改状态3
        originator.setState("3");
        caretaker.saveMementoIF(originator.createMementoIF());

        // 返回状态2
        originator.restoreMementoIF(caretaker.retrieveMementoIF(2));
        System.out.println("返回状态2：" + originator);

        // 返回状态1
        originator.restoreMementoIF(caretaker.retrieveMementoIF(1));
        System.out.println("返回状态1：" + originator);
    }

}

interface A1MementoIF {

}

/**
 * 发起者角色，即被保存状态的对象
 */
class A1Originator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * 生成备忘录对象，即对自身状态进行保存
     * @return
     */
    public A1MementoIF createMementoIF() {
        return new A1Memento(this.state);
    }

    /**
     * 读取备忘录对象
     * @param m
     */
    public void restoreMementoIF(A1MementoIF m) {
        this.state = ((A1Memento)m).getState();
    }

    /**
     * 备忘录角色
     */
    private class A1Memento implements A1MementoIF {
        private String state;

        public A1Memento(String state) {
            this.state = state;
        }

        private String getState() {
            return state;
        }

        private void setState(String state) {
            this.state = state;
        }
    }

    @Override
    public String toString() {
        return "A1Originator{" +
                "state='" + state + '\'' +
                '}';
    }
}

/**
 * 管理者角色，可以称为备忘录
 */
class A1Caretaker {
    private List<A1MementoIF> list = new ArrayList<>();

    /**
     * 保存备忘录对象
     * @param m
     */
    public void saveMementoIF(A1MementoIF m) {
        this.list.add(m);
    }

    /**
     * 返回备忘录对象
     * @param i
     */
    public A1MementoIF retrieveMementoIF(int i) {
        return this.list.get(i - 1);
    }
}


















