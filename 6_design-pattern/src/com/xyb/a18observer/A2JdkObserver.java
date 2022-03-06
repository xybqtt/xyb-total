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

        observable.setPrice(5);

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
        int a = this.price;

        super.setChanged();
        this.price = price;
        super.notifyObservers(a);

    }


    @Override
    public String toString() {
        return "A2ConcreteObservable{" +
                "price=" + price +
                '}';
    }
}

/**
 * 具体观察者
 */
class A2ConcreteObserver implements Observer {

    /**
     *
     * @param o 被观察对象
     * @param arg 改变前的值
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("A2ConcreteObserver观察者，被观察者现在为：" + o.toString() + "，价格修改前为：" + arg);
    }
}
