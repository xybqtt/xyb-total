package com.xyb.a10composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 组合模式：安全模式。
 * 相比于透明模式，这个模式需要知道叶子节点和非叶子节点有哪些方法。
 */
public class A2CompositeSafe {

    public static void main(String[] args) {

        // 根节点
        A2Composite root = new A2Composite("根");

        // 创建二级节点，并添加到根节点
        A2Composite l1 = new A2Composite("  枝l1");
        A2Composite l2 = new A2Composite("  枝l2");
        A2Composite l3 = new A2Composite("  枝l3");
        root.add(l1);
        root.add(l2);
        root.add(l3);

        // 创建3级节点，并添加到2级节点
        A2Composite l11 = new A2Composite("    枝11");
        A2Composite l12 = new A2Composite("    枝12");
        A2Composite l21 = new A2Composite("    枝21");

        l1.add(l11);
        l1.add(l12);
        l2.add(l21);

        // 创建叶节点，并添加到对应节点
        A2Leaf l111 = new A2Leaf("      叶l111");
        A2Leaf l121 = new A2Leaf("      叶l121");
        A2Leaf l211 = new A2Leaf("      叶l211");
        l11.add(l111);
        l12.add(l121);
        l21.add(l211);

        // 展示
        root.show();


    }

}

interface A2Component {
    public void show();
}

class A2Composite implements A2Component {

    private String compositeName;

    private List<A2Component> list = new ArrayList<A2Component>();

    public A2Composite(String name) {
        this.compositeName = name;
    }

    public void add(A2Component A2Component) {
        list.add(A2Component);
    }

    public void remove(A2Component A2Component) {
        list.remove(A2Component);
    }

    public A2Component getChild(int i) {
        return this.list.get(i);
    }

    @Override
    public void show() {
        System.out.println(this.compositeName);
        Iterator iterator = this.list.iterator();
        A2Component A2Component;
        while (iterator.hasNext()) {
            A2Component = (A2Component) iterator.next();
            A2Component.show();
        }
    }
}


class A2Leaf implements A2Component {

    private String leafName;

    public A2Leaf(String name) {
        this.leafName = name;
    }

    @Override
    public void show() {
        System.out.println(this.leafName);
    }
}
