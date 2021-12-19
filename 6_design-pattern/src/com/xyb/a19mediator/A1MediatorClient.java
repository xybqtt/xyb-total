package com.xyb.a19mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介者模式
 */
public class A1MediatorClient {

    public static void main(String[] args) {

        A1Mediator a1Mediator = new A1ConcreteMediator();
        A1Colleague a1Colleague1 = new A1ConcreteColleague1("张三", a1Mediator);
        A1Colleague a1Colleague2 = new A1ConcreteColleague1("李四", a1Mediator);
        A1Colleague a1Colleague3 = new A1ConcreteColleague1("王五", a1Mediator);

        A1Colleague a1Colleague4 = new A1ConcreteColleague2("赵6", a1Mediator);
        A1Colleague a1Colleague5 = new A1ConcreteColleague2("王8", a1Mediator);
        A1Colleague a1Colleague6 = new A1ConcreteColleague2("孙9", a1Mediator);

        // 同事角色注册
        a1Mediator.register(a1Colleague1);
        a1Mediator.register(a1Colleague2);
        a1Mediator.register(a1Colleague3);
        a1Mediator.register(a1Colleague4);
        a1Mediator.register(a1Colleague5);
        a1Mediator.register(a1Colleague6);

        // a1Colleague1 发布消息
        a1Colleague1.send("a1Colleague1发消息了");

    }

}

/**
 * 抽象同事角色
 */
abstract class A1Colleague {
    /**
     * 接收中介消息
     */
    public abstract void receive(String message);

    /**
     * 向中介发送消息，让其转发
     */
    public abstract void send(String message);
}

/**
 * 具体同事角色1
 */
class A1ConcreteColleague1 extends A1Colleague {

    private String name;
    private A1Mediator mediator;

    public A1ConcreteColleague1(String name, A1Mediator a1Mediator) {
        this.name = name + "角色1";
        this.mediator = a1Mediator;
    }

    @Override
    public void receive(String message) {
        System.out.println(toString() + "接收到消息：" + message);
    }

    @Override
    public void send(String message) {
        System.out.println(toString() + "发送消息：" + message);
        this.mediator.relay(this, message);
    }

    @Override
    public String toString() {
        return "A1ConcreteColleague1{" +
                "name='" + name + '\'' +
                '}';
    }
}

/**
 * 具体同事角色2
 */
class A1ConcreteColleague2 extends A1Colleague {

    private String name;
    private A1Mediator mediator;

    public A1ConcreteColleague2(String name, A1Mediator a1Mediator) {
        this.name = name + "角色2";
        this.mediator = a1Mediator;
    }

    @Override
    public void receive(String message) {
        System.out.println(toString() + "接收到消息：" + message);
    }

    @Override
    public void send(String message) {
        System.out.println(toString() + "发送消息：" + message);
    }

    @Override
    public String toString() {
        return "A1ConcreteColleague2{" +
                "name='" + name + '\'' +
                '}';
    }
}

/**
 * 抽象中介者
 */
abstract class A1Mediator {
    /**
     * 同事角色注册到中介者中
     * @param a1Colleague
     */
    public abstract void register(A1Colleague a1Colleague);

    /**
     * 中介发布消息。
     * @param a1Colleague 不向此同事角色转发
     */
    public abstract void relay(A1Colleague a1Colleague, String message);
}

class A1ConcreteMediator extends A1Mediator {

    private List<A1Colleague> list = new ArrayList<>();

    @Override
    public void register(A1Colleague a1Colleague) {
        if(!this.list.contains(a1Colleague))
            this.list.add(a1Colleague);
    }

    @Override
    public void relay(A1Colleague a1Colleague, String message) {
        for(A1Colleague cl : this.list) {
            if(cl != a1Colleague)
                cl.receive(message);
        }
    }
}
