package com.xyb.a18observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 使用jdk自带的观察者模式接口
 */
public class A2JdkObserver {

    public static void main(String[] args) {
        A2ConcreteObservable observable = new A2ConcreteObservable();
        observable.addObserver(new A2ConcreteObserver());
        observable.addObserver(new A2ConcreteObserver());

        observable.setPrice(10);

    }

}

/**
 * 具体目标，被观察者
 */
class A2ConcreteObservable extends Observable {
    private int price;

    /**
     * 当price发生改变时，通知观察者
     * @param price
     */
    public void setPrice(int price) {
        super.setChanged();
        super.notifyObservers(price);
        this.price = price;
    }

}

/**
 * 具体观察者
 */
class A2ConcreteObserver implements Observer {

    /**
     *
     * @param o 被观察对象
     * @param arg 改变的值
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("修改后的price = " + arg + "，所属对象 = " + o.toString());
    }
}
