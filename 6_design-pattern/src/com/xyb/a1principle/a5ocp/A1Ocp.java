package com.xyb.a1principle.a5ocp;

/**
 * 开闭原则，问题展示
 * 优点是比较好理解，简单易操作。
 * 2) 缺点是违反了设计模式的 ocp 原则，即对扩展开放(提供方)，对修改关闭(使用方)。即当我们给类增加新功能的
 * 时候，尽量不修改代码，或者尽可能少修改代码. 3) 比如我们这时要新增加一个图形种类 三角形，我们需要做如下修改，修改的地方较多
 */
public class A1Ocp {

    public static void main(String[] args) {
        GraphicEditor g = new GraphicEditor();
        g.drawShape(new Rectangle());
        g.drawShape(new Circle());
    }

}

/**
 * 1)优点是比较好理解，简单易操作。
 * 2) 缺点是违反了设计模式的 ocp 原则，即对扩展开放(提供方)，对修改关闭(使用方)。即当我们给类增加新功能的
 * 时候，尽量不修改代码，或者尽可能少修改代码。
 * 3) 比如我们这时要新增加一个图形种类 三角形，我们需要做如下修改，修改的地方较多
 *      增加一个类、修改drawShape方法、添加drawSanjiaoxing()方法。
 */
class GraphicEditor {
    public void drawShape(Shape s) {
        if(s.m_type == 1)
            drawRectangle(s);
        else if(s.m_type == 2)
            drawCircle(s);
        // 如果再加个三角形，那么还需要修改GraphicEditor这个使用方
    }

    private void drawCircle(Shape s) {
        System.out.println("绘制圆形");
    }

    private void drawRectangle(Shape s) {
        System.out.println("绘制矩形");
    }


}

class Shape {
    int m_type;
}

class Rectangle extends Shape {
    public Rectangle() {
        super.m_type = 1;
    }
}

class Circle extends Shape {
    public Circle() {
        super.m_type = 2;
    }
}