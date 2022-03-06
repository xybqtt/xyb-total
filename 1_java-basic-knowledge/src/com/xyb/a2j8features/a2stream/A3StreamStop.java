package com.xyb.a2j8features.a2stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * stream的终止操作
 * |allMatch(Predicate p)|检查是否匹配所有元素|
 * |anyMatch(Predicate p)|检查是否至少匹配一个元素|
 * |noneMatch(Predicate p)|检查是否没有匹配所有元素|
 * |findFirst()|返回第一个元素|
 * |findAny()|返回当前流中的任意元素|
 * |count()|返回流中元素总数|
 * |max(Comparator c)|返回流中最大值|
 * |min(Comparator c)|返回流中最小值|
 * |forEach(Consumer c)|内部迭代(使用Collection接口需要用户去做迭代，称为外部迭代。相反，Stream API使用内部迭代——它帮你把迭代做了)|
 *
 * 归约操作
 * |reduce(T iden, BinaryOperator b)|可以将流中元素反复结合起来，得到一个值。返回T|
 * |reduce(BinaryOperator b)|可以将流中元素反复结合起来，得到一个值。返回Optional<T>|
 */
public class A3StreamStop {
    
    @Test
    public void test() {
//        stopOperate();
//        reduceOperate();
        collectOp();
    }

    /**
     * 收集操作，将流中的数据返回给集合或数组，其它的Collectors的功能不测了，操作都类似。
     */
    private void collectOp() {
        List<User> list = createUserList();

        List<User> collect = list.stream().filter(user -> user.salary >= 5000).collect(Collectors.toList());
        System.out.println("收集操作，工资>=5000的人List集合" + collect.toString());

        Set<User> userSet = list.stream().filter(user -> user.userName.length() >= 4).collect(Collectors.toSet());
        System.out.println("收集操作，名字长度>=4的人Set集合" + userSet.toString());

        Map<String, User> userMap = list.stream().filter(user -> user.userName.length() >= 4).collect(Collectors.toMap(User::getUserName, user -> user));
        System.out.println("收集操作，名字长度>=4的人Map集合" + userMap.toString());

    }

    /**
     * 归约、操作
     */
    private void reduceOperate() {
        List<User> list = createUserList();

        // 归约操作
        // |reduce(T iden, BinaryOperator b)|可以将流中元素反复结合起来，得到一个值。返回T|，将所有User的薪水加和，第一个参数是初始值
        Integer reduce = list.stream().map(User::getSalary).reduce(10, Integer::sum);
        System.out.println("归约操作，加和所有人的工资再加10：" + reduce);

        // |reduce(BinaryOperator b)|可以将流中元素反复结合起来，得到一个值。返回Optional<T>|
        Optional<Integer> reduce2 = list.stream().map(User::getSalary).reduce(Integer::sum);
        System.out.println("归约操作，加和所有人的工资：" + reduce2);
    }

    /**
     * 中止操作
     */
    private void stopOperate() {
        List<User> list = createUserList();
        System.out.println(list.toString());

        System.out.println("中止操作");
        // |allMatch(Predicate p)|检查是否匹配所有元素|
        boolean flag = list.stream().allMatch(user -> user.salary > 5000);
        System.out.println("allMatch，是否工资都>5000：" + flag);

        // |anyMatch(Predicate p)|检查是否至少匹配一个元素|
        flag = list.stream().anyMatch(user -> user.salary > 5000);
        System.out.println("anyMatch，是否有至少一个工资>5000：" + flag);

        // |noneMatch(Predicate p)|检查是否没有匹配所有元素|
        flag = list.stream().noneMatch(user -> user.salary > 5000);
        System.out.println("noneMatch，是否没有一个工资>5000：" + flag);

        // |findFirst()|返回第一个元素|
        Optional<User> first = list.stream().findFirst();
        System.out.println("findFirst，查看第一个元素：" + first);

        // |findAny()|返回当前流中的任意元素|
        Optional<User> any = list.stream().findAny();
        System.out.println("findAny，查看任意一个元素：" + any);

        // |count()|返回流中元素总数|
        long count = list.stream().count();
        System.out.println("count，查看元素个数：" + count);

        // |max(Comparator c)|返回流中最大值|
        Optional<User> max = list.stream().max((o1, o2) -> o1.salary - o2.salary);
        System.out.println("max，返回流中最大值：" + max);

        // |min(Comparator c)|返回流中最小值|
        Optional<User> min = list.stream().min((o1, o2) -> o1.salary - o2.salary);
        System.out.println("min，返回流中最大值：" + min);


        // |forEach(Consumer c)|内部迭代(使用Collection接口需要用户去做迭代，称为外部迭代。相反，Stream API使用内部迭代——它帮你把迭代做了)|
        System.out.println("forEach，遍历操作：" + min);
        list.stream().forEach(System.out::println);
        list.stream().collect(Collectors.toList());

    }

    private List<User> createUserList() {
        List<User> list = new ArrayList<>();
        Random random = new Random();

        list.add(new User("张三1", 6000));
        list.add(new User("张三11", 6000));
        list.add(new User("张三221", 7000));
        list.add(new User("张三311", 8000));
        list.add(new User("张三4", 9000));
        list.add(new User("张三523", 2000));
        list.add(new User("张三645", 3000));
        list.add(new User("张三77", 1000));
        list.add(new User("张三83", 1000));
        list.add(new User("张三8222", 4000));

        return list;

    }

    private class User {

        private String userName;

        private int salary;

        public User() {
        }

        public User(String userName, int salary) {
            this.userName = userName;
            this.salary = salary;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            if (salary != user.salary) return false;
            return userName != null ? userName.equals(user.userName) : user.userName == null;
        }

        @Override
        public int hashCode() {
            int result = userName != null ? userName.hashCode() : 0;
            result = 31 * result + salary;
            return result;
        }


        @Override
        public String toString() {
            return "User{" +
                    "userName='" + userName + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }


}
