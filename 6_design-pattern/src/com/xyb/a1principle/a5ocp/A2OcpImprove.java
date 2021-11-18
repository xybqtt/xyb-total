package com.xyb.a1principle.a5ocp;

/**
 * 开闭原则
 */
public class A2OcpImprove {

    public static void main(String[] args) {
        A2GraphicEditor a2 = new A2GraphicEditor();
        a2.drawShape(new A2Circle());
        a2.drawShape(new A2Rectangle());
        // 如果想绘制三角形，添加一个三角形类并实现IShape接口即可，或者extends抽象类
    }

}

class A2GraphicEditor {

    public void drawShape(IShape s) {
        s.draw();
    }

}

interface IShape {
    public void draw();
}

class A2Circle implements IShape {

    @Override
    public void draw() {
        System.out.println("绘制圆形");
    }
}

class A2Rectangle implements IShape {

    @Override
    public void draw() {
        System.out.println("绘制矩形");
    }
}


