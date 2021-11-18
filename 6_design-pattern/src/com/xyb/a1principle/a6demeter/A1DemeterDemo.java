package com.xyb.a1principle.a6demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * 迪米特法则：
 */
public class A1DemeterDemo {
    public static void main(String[] args) {
        SchoolManager em = new SchoolManager();
        em.printAllEmployee(new CollegeManager());
    }
}

/**
 * 学校总部员工
 */
class SchoolEmployee {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}

/**
 * 学院员工类
 */
class CollegeEmployee {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}

/**
 * 学院员工管理类
 */
class CollegeManager {

    public List<CollegeEmployee> getAllEmployee() {
        List<CollegeEmployee> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CollegeEmployee em = new CollegeEmployee();
            em.setId("学院员工id=" + String.valueOf(i));
            list.add(em);
        }
        return list;
    }


    public void printAllEmployee() {
        List<CollegeEmployee> collist = getAllEmployee();
        System.out.println("学院员工：");
        for(CollegeEmployee ce : collist)
            System.out.println(ce.getId());
    }

}

/**
 * 学校总部员工管理类，对于此类：
 *      直接朋友：SchoolEmployee、CollegeManager
 *      陌生类：CollegeEmployee，违背了迪米特法则。
 */
class SchoolManager {

    public List<SchoolEmployee> getAllEmployee() {
        List<SchoolEmployee> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SchoolEmployee em = new SchoolEmployee();
            em.setId("学校员工id=" + String.valueOf(i));
            list.add(em);
        }
        return list;
    }

    /**
     * 输出学院和学校员工的id。
     * @param sub
     */
    public void printAllEmployee(CollegeManager sub) {

        // 此处违背了迪米特法则，应该将打印学院员工这部分代码移到CollegeManager类中，然后此处直接调用sub.printAllEmployee()即可。
        List<CollegeEmployee> collist = sub.getAllEmployee();
        System.out.println("学院员工：");
        for(CollegeEmployee ce : collist)
            System.out.println(ce.getId());


        System.out.println();
        System.out.println("学校总部员工：");
        for(SchoolEmployee ce : getAllEmployee())
            System.out.println(ce.getId());
    }

}















