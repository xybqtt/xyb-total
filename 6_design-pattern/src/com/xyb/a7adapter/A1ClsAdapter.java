package com.xyb.a7adapter;

/**
 * 类适配器模式
 */
public class A1ClsAdapter {

    public static void main(String[] args) {
        A1Phone phone = new A1Phone();
        phone.charging(new A1VAdapter());
    }

}

class A1Phone {
    public void charging(A1IV5 iv5) {
        if(iv5.output5V() == 5)
            System.out.println("电压5V，可充电");
        else
            System.out.println("电压非5V，不能充电");
    }
}

/**
 * 被适配的类
 */
class A1V220 {
    public int output220V() {
        return 220;
    }
}

interface A1IV5 {
    public int output5V();
}

/**
 * 适配器类
 */
class A1VAdapter extends A1V220 implements A1IV5 {
    @Override
    public int output5V() {
        int dstV = super.output220V() / 44;
        return dstV;
    }
}
