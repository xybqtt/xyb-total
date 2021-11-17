package com.xyb.a1principle.a3inversion;

/**
 * 用依赖倒转解决新增接收微信等问题。
 * 如果Person需要新增加一个接收的类型，只要新增一个类实现IReceiver接口就行，
 * 不需要修改Person类。
 */
public class A2DIImprove {

    public static void main(String[] args) {
        A2Person p = new A2Person();
        p.receive(new A2Email());
        p.receive(new A2WeChat());
    }

}

interface IReveiver {
    String getInfo();
}

class A2Person {
    public void receive(IReveiver iReveiver) {
        System.out.println(iReveiver.getInfo());
    }
}

class A2Email implements IReveiver {

    @Override
    public String getInfo() {
        return "email";
    }
}

class A2WeChat implements IReveiver {

    @Override
    public String getInfo() {
        return "WeChat";
    }
}
