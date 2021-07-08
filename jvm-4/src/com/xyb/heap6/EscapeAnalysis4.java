package com.xyb.heap6;

import java.util.ArrayList;
import java.util.List;

/**
 * 逃逸分析
 * -server -Xms1G -Xmx1G -XX:+DoEscapeAnalysis -XX:+EliminateAllocations -XX:+PrintEscapeAnalysis -XX:+PrintGCDetails
 */
public class EscapeAnalysis4 {

    private static List<User> userList = new ArrayList<User>();

    public static void main(String[] args) {

        // 查看标量替换
        Long startTime = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++){
            alloc();
        }
        System.out.println("未发生逃逸所用标量替换时间：" + (System.currentTimeMillis() - startTime) + "ms。");

        startTime = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++){
            userList.add(occurEscape());
        }
        System.out.println("发生逃逸未使用标题替换所用时间：" + (System.currentTimeMillis() - startTime) + "ms。");

        // 查看消除同步锁
        startTime = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++){
            User user = new User();
            synchronized (user){
                user.setName("张三");
            }
        }
        System.out.println("未发生逃逸同步锁所用时间：" + (System.currentTimeMillis() - startTime) + "ms。");

        startTime = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++){
            User user = new User();
            synchronized (user){
                user.setName("张三");
                userList.add(user);
            }
        }
        System.out.println("发生逃逸使用同步锁所用时间：" + (System.currentTimeMillis() - startTime) + "ms。");


    }

    /**
     * 未发生逃逸
     */
    public static void alloc(){
        User user = new User();
    }

    /**
     * 发生了逃逸
     * @return
     */
    public static User occurEscape(){
        User user = new User();
        return user;
    }

}

class User {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
