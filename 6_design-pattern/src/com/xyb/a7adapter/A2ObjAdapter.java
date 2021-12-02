package com.xyb.a7adapter;

/**
 * 对象适配器模式
 */
public class A2ObjAdapter {

    public static void main(String[] args) {
        A2Phone phone = new A2Phone();
        phone.charging(new A2VAdapter(new A2V220()));
    }

}

class A2Phone {
    public void charging(A2IV5 iv5) {
        if(iv5.output5V() == 5)
            System.out.println("电压5V，可充电");
        else
            System.out.println("电压非5V，不能充电");
    }
}

/**
 * 被适配的类
 */
class A2V220 {
    public int output220V() {
        return 220;
    }
}

interface A2IV5 {
    public int output5V();
}

/**
 * 适配器类，将源类作为成员变量
 */
class A2VAdapter implements A2IV5 {

    private A2V220 a2V220;

    public A2VAdapter(A2V220 a2V220) {
        this.a2V220 = a2V220;
    }

    @Override
    public int output5V() {
        int dstV = this.a2V220.output220V() / 44;
        return dstV;
    }
}
