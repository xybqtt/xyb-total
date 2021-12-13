package com.xyb.a12flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式：
 * 围棋黑白棋子。
 */
public class A1FlyWeightDemo {

    public static void main(String[] args) {
        A1Chess w1 = A1ChessFactory.getChess("0");
        w1.downChess(new A1ChessPoint(1, 10));

        A1Chess w2 = A1ChessFactory.getChess("0");
        w2.downChess(new A1ChessPoint(2, 10));

        A1Chess w3 = A1ChessFactory.getChess("0");
        w2.downChess(new A1ChessPoint(3, 10));

        System.out.println("查看白棋是否是同一个对象：" + (w1 == w2));
        System.out.println("查看白棋是否是同一个对象：" + (w1 == w3));
        System.out.println("-------------------------------");

        A1Chess b1 = A1ChessFactory.getChess("1");
        w1.downChess(new A1ChessPoint(1, 9));

        A1Chess b2 = A1ChessFactory.getChess("1");
        w2.downChess(new A1ChessPoint(2, 9));

        A1Chess b3 = A1ChessFactory.getChess("1");
        w2.downChess(new A1ChessPoint(3, 9));

        System.out.println("查看黑棋是否是同一个对象：" + (b1 == b2));
        System.out.println("查看黑棋是否是同一个对象：" + (b1 == b3));
    }

}

/**
 * 不可共享的信息，棋子位置，在具体享元角色中被调用
 */
class A1ChessPoint {

    private int x;
    private int y;

    public A1ChessPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}

/**
 * 抽象享元角色，棋子
 */
interface A1Chess {
    /**
     * 下棋子
     */
    public void downChess(A1ChessPoint point);
}

/**
 * 具体享元角色，白子
 */
class A1BlackChess implements A1Chess {

    private String chessName = "黑棋";

    @Override
    public void downChess(A1ChessPoint point) {
        System.out.println(this.chessName + "：(" + point.getX() + ", " + point.getY() + ")");
    }
}

/**
 * 具体享元角色，黑子
 */
class A1WhiteChess implements A1Chess {

    private String chessName = "白棋";

    @Override
    public void downChess(A1ChessPoint point) {
        System.out.println(this.chessName + "：(" + point.getX() + ", " + point.getY() + ")");
    }
}

/**
 * 享元工厂角色
 */
class A1ChessFactory {
    private static Map<String, A1Chess> map = new HashMap<>();

    static {
        map.put("0", new A1WhiteChess());
        map.put("1", new A1BlackChess());
    }

    private A1ChessFactory() {

    }

    /**
     *
     * @param type 0黑子，1白子
     * @return
     */
    public static A1Chess getChess(String type) {
        return map.get(type);
    }
}
