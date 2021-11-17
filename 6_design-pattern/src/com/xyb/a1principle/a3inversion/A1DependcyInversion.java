package com.xyb.a1principle.a3inversion;

/**
 * 依赖倒转
 */
public class A1DependcyInversion {

    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
        // 每新增一个接收的类型，就需要重新在Person里面加方法，不方便。
        person.receiveWechat(new WeChat());
    }

}

class Email {
    public String getInfo() {
        return "hello";
    }
}

class WeChat {
    public String getInfo() {
        return "WeChat";
    }
}

/**
 * 完成Person接收消息功能
 * 方式1分析：
 *      1、简单，比较容易想到；
 *      2、如果我们获取的对象不是email，而是微信、短信等，则新增类，同时Person也要新增相应
 *      接收方法。
 *      3、解决思路：引入一个抽象的接口IReveiver，表示接收者，这样Person类与接口IReceiver
 *      发生依赖，因为email、weixin等待属于接收的范围，他们各自实现IReveiver接口就ok。这样我们
 *      就符合依赖倒转。查看下一个类。
 */
class Person {
    public void receive(Email email) {
        System.out.println(email.getInfo());
    }

    public void receiveWechat(WeChat weChat) {
        System.out.println(weChat.getInfo());
    }
}