package com.xyb.a10composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 组合模式：透明模式。
 */
public class A1CompositeNotSafe {

    public static void main(String[] args) {

        // 根节点
        A1Composite root = new A1Composite("根");

        // 创建二级节点，并添加到根节点
        A1Composite l1 = new A1Composite("  枝l1");
        A1Composite l2 = new A1Composite("  枝l2");
        A1Composite l3 = new A1Composite("  枝l3");
        root.add(l1);
        root.add(l2);
        root.add(l3);

        // 创建3级节点，并添加到2级节点
        A1Composite l11 = new A1Composite("    枝11");
        A1Composite l12 = new A1Composite("    枝12");
        A1Composite l21 = new A1Composite("    枝21");

        l1.add(l11);
        l1.add(l12);
        l2.add(l21);

        // 创建叶节点，并添加到对应节点
        A1Leaf l111 = new A1Leaf("      叶l111");
        A1Leaf l121 = new A1Leaf("      叶l121");
        A1Leaf l211 = new A1Leaf("      叶l211");
        l11.add(l111);
        l12.add(l121);
        l21.add(l211);

        // 展示
        root.show();


    }

}

interface A1Component {

    /**
     * 因为叶子节点不需要add、remove方法，所以设置默认实现
     * @param a1Component
     */
    public default void add(A1Component a1Component) {
        throw new UnsupportedOperationException();
    }

    public default void remove(A1Component a1Component) {
        throw new UnsupportedOperationException();
    }

    public A1Component getChild(int i);

    public void show();
}

class A1Composite implements A1Component {

    private String compositeName;

    private List<A1Component> list = new ArrayList<A1Component>();

    public A1Composite(String name) {
        this.compositeName = name;
    }

    @Override
    public void add(A1Component a1Component) {
        list.add(a1Component);
    }

    @Override
    public void remove(A1Component a1Component) {
        list.remove(a1Component);
    }

    @Override
    public A1Component getChild(int i) {
        return this.list.get(i);
    }

    @Override
    public void show() {
        System.out.println(this.compositeName);
        Iterator iterator = this.list.iterator();
        A1Component a1Component;
        while (iterator.hasNext()) {
            a1Component = (A1Component) iterator.next();
            a1Component.show();
        }
    }
}


class A1Leaf implements A1Component {

    private String leafName;

    public A1Leaf(String name) {
        this.leafName = name;
    }

    @Override
    public A1Component getChild(int i) {
        return null;
    }

    @Override
    public void show() {
        System.out.println(this.leafName);
    }
}
