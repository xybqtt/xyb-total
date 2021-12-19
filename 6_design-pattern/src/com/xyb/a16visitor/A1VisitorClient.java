package com.xyb.a16visitor;

import java.util.*;

/**
 * 观察者模式，如各科老师(观察者)仅关心所带科目男女学生(被观察者)的成绩。
 */
public class A1VisitorClient {
    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();
        os.add(new A1MaleStudent("张三", 10, 20));
        os.add(new A1MaleStudent("李四", 11, 21));

        os.add(new A1FemaleStudent("金莲", 12, 22));
        os.add(new A1FemaleStudent("翠花", 13, 23));

        A1Teacher visitor = new A1ChineseTeacher();
        os.accept(visitor);

        System.out.println("------------------------");

        visitor = new A1MathTeacher();
        os.accept(visitor);
    }
}

/**
 * 抽象访问者：教师
 */
interface A1Teacher {
    void visit(A1MaleStudent a1MaleStudent);

    void visit(A1FemaleStudent a1FemaleStudent);
}

/**
 * 具体访问者1：语文教师
 */
class A1ChineseTeacher implements A1Teacher {

    public void visit(A1MaleStudent student) {
        System.out.println("语文老师查看男学生-->" + student.getName() + "分数，为" + student.getChineseScore());
    }

    public void visit(A1FemaleStudent student) {
        System.out.println("语文老师查看女学生-->" + student.getName() + "分数，为" + student.getChineseScore());
    }
}

/**
 * 具体访问者2：数学教师
 */
class A1MathTeacher implements A1Teacher {
    public void visit(A1MaleStudent student) {
        System.out.println("数学老师查看男学生-->" + student.getName() + "分数，为" + student.getMathScore());
    }

    public void visit(A1FemaleStudent student) {
        System.out.println("数学老师查看女学生-->" + student.getName() + "分数，为" + student.getMathScore());
    }
}

/**
 * 被访问者：学生
 */
interface A1Student {
    /**
     * 学生接受教师的观察
     * @param visitor
     */
    void accept(A1Teacher visitor);
}

/**
 * 具体被观察者：男同学
 */
class A1MaleStudent implements A1Student {

    private String name;
    private int chineseScore;
    private int mathScore;

    public A1MaleStudent(String name, int chineseScore, int mathScore) {
        this.name = name;
        this.chineseScore = chineseScore;
        this.mathScore = mathScore;
    }

    public void accept(A1Teacher a1Teacher) {
        a1Teacher.visit(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChineseScore() {
        return chineseScore;
    }

    public void setChineseScore(int chineseScore) {
        this.chineseScore = chineseScore;
    }

    public int getMathScore() {
        return mathScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }
}

/**
 * 具体被观察者：女同学
 */
class A1FemaleStudent implements A1Student  {

    private String name;
    private int chineseScore;
    private int mathScore;

    public A1FemaleStudent(String name, int chineseScore, int mathScore) {
        this.name = name;
        this.chineseScore = chineseScore;
        this.mathScore = mathScore;
    }

    public void accept(A1Teacher a1Teacher) {
        a1Teacher.visit(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChineseScore() {
        return chineseScore;
    }

    public void setChineseScore(int chineseScore) {
        this.chineseScore = chineseScore;
    }

    public int getMathScore() {
        return mathScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }

}

//对象结构角色
class ObjectStructure {
    private List<A1Student> list = new ArrayList<A1Student>();

    public void accept(A1Teacher a1Teacher) {
        Iterator<A1Student> i = list.iterator();
        while (i.hasNext()) {
            ((A1Student) i.next()).accept(a1Teacher);
        }
    }

    public void add(A1Student element) {
        list.add(element);
    }

    public void remove(A1Student element) {
        list.remove(element);
    }
}