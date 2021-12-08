package com.xyb.a10composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 买东西：大袋子可以装中袋子，中袋子可以装小袋子，小袋子可以装货物，计算总价。
 */
public class A3BuyGoodsDemo {

    public static void main(String[] args) {
        // 大袋子
        A3Bags b1 = new A3Bags(5, "大袋子");

        // 3个中袋子
        A3Bags b11 = new A3Bags(4, "  中袋子1");
        A3Bags b12 = new A3Bags(4, "  中袋子2");
        A3Bags b13 = new A3Bags(4, "  中袋子3"); // 里面没东西

        b1.add(b11);
        b1.add(b12);
        b1.add(b13);

        // 5个小袋子
        A3Bags b111 = new A3Bags(4, "    小袋子11");
        A3Bags b112 = new A3Bags(4, "    小袋子12");
        A3Bags b121 = new A3Bags(4, "    小袋子21");
        A3Bags b122 = new A3Bags(4, "    小袋子22");
        A3Bags b123 = new A3Bags(4, "    小袋子23");

        b11.add(b111);
        b11.add(b112);
        b12.add(b111);
        b12.add(b112);
        b12.add(b123);

        // 5个小袋子放货物
        A3Goods g1111 = new A3Goods(10, "      货1");
        A3Goods g1112 = new A3Goods(20, "      货2");
        A3Goods g1121 = new A3Goods(20, "      货3");
        A3Goods g1211 = new A3Goods(20, "      货4");
        A3Goods g1221 = new A3Goods(20, "      货5");

        b111.add(g1111);
        b111.add(g1112);
        b112.add(g1121);
        b121.add(g1211);
        b122.add(g1221);

        // 展示
        b1.show();
        System.out.println("共计：" + b1.calPrice());;


    }

}

/**
 * 物品
 */
interface A3Articles {
    public float calPrice();
    public void show();
}

/**
 * 叶子构件，商品
 */
class A3Goods implements A3Articles {

    private float price;
    private String goodsName;

    public A3Goods(float price, String name) {
        this.price = price;
        this.goodsName = name;
    }

    @Override
    public float calPrice() {
        return this.price;
    }

    @Override
    public void show() {
        System.out.println(this.goodsName + "(" + this.price + ")");
    }
}

class A3Bags implements A3Articles {

    private float price;
    private String bagName;
    private List<A3Articles> list = new ArrayList<>();
    
    public A3Bags(float price, String name) {
        this.price = price;
        this.bagName = name;
    }
    
    public void add(A3Articles a3Articles) {
        this.list.add(a3Articles);
    }

    public void remove(A3Articles a3Articles) {
        this.list.remove(a3Articles);
    }
    
    @Override
    public float calPrice() {
        float sumPrice = 0.0f;
        sumPrice += this.price;

        Iterator iterator = this.list.iterator();
        A3Articles a3Articles;
        while (iterator.hasNext()) {
            a3Articles = (A3Articles) iterator.next();
            sumPrice += a3Articles.calPrice();
        }
        return sumPrice;
    }

    @Override
    public void show() {
        System.out.println(this.bagName + "(" + this.price + ")");
        Iterator iterator = this.list.iterator();
        A3Articles a3Articles;
        while (iterator.hasNext()) {
            a3Articles = (A3Articles) iterator.next();
            a3Articles.show();
        }
    }
}
