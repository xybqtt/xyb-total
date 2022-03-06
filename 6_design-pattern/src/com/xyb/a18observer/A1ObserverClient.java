package com.xyb.a18observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 观察者模式
 */
public class A1ObserverClient {

    public static void main(String[] args) {
        A1ConcreteSubject a = new A1ConcreteSubject();
        a.addObserver(new A1ConcreteObserver1());
        a.addObserver(new A1ConcreteObserver2());

        // 通知所有观察者
        a.setPrice(10);
    }

}

/**
 * 抽象观察者，声明了当被观察目标发生改变时，
 * 观察者应该被调用的方法
 */
interface A1Observer {
    public void response(Object observeralbe, Object arg);
}

class A1ConcreteObserver1 implements A1Observer {

    @Override
    public void response(Object observeralbe, Object arg) {
        System.out.println("A1ConcreteObserver1观察者，被观察者现在为：" + observeralbe.toString() + "，修改前为：" + arg);
    }
}

class A1ConcreteObserver2 implements A1Observer {

    @Override
    public void response(Object observeralbe, Object arg) {
        System.out.println("A1ConcreteObserver2观察者，被观察者现在为：" + observeralbe.toString() + "，修改前为：" + arg);
    }
}

/**
 * 抽象目标
 */
abstract class A1Subject {
    protected List list = new ArrayList<A1Observer>();

    public void addObserver(A1Observer a1Observer) {
        this.list.add(a1Observer);
    }

    public void removeObserver(A1Observer a1Observer) {
        this.list.remove(a1Observer);
    }

    public abstract void notifyObservers(Object o);

}

/**
 * 被观察目标，当属性变化时，可以调用notifyObservers()，通知被观察者。
 */
class A1ConcreteSubject extends A1Subject {

    private int price;

    public void setPrice(int price) {
        int a = this.price;
        this.price = price;
        notifyObservers(a);
    }

    @Override
    public void notifyObservers(Object o) {
        Iterator it = this.list.iterator();
        while (it.hasNext()) {
            ((A1Observer) (it.next())).response(this, o);
        }
    }


    @Override
    public String toString() {
        return "A1ConcreteSubject{" +
                "price=" + price +
                '}';
    }
}