package com.xyb.a2j8features.a2stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream的中间操作：
 * 筛选与切片
 * |filter(Predicate p)|接收Lambda，从流中排除校验返回false的元素|
 * |distinct()|筛选，通过流所生成元素的hashCode()和equals()去除重复元素|
 * |limit(long maxSize)|截断流，使其元素不超过给定数量|
 * |skip(long n)|跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补。|
 * 
 * 映射
 * |map(Function f)|接收一个函数做为参数，该函数会被应用到每个元素上，并将其映射成一个新函数|
 * |mapToDouble(ToDoubleFunction f)|接收一个函数做为参数，该函数会被应用到每个元素上，产生一个新的DoubleStream|
 * |mapToInt(ToIntFunction f)|接收一个函数做为参数，该函数会被应用到每个元素上，产生一个新的IntStream|
 * |mapToLong(ToLongFunction f)|接收一个函数做为参数，该函数会被应用到每个元素上，产生一个新的LongStream|
 * |flatMap(Function f)|接收一个函数做为参数，将流中的每个值都换成另一个流，然后把所有的流连接成一个流
 */
public class A2StreamCenterOpera {

    @Test
    public void test() {
        // 筛选与切片
//        select();

        // 映射
//        mapOpera();

        // 排序
        streamSort();

    }

    /**
     * 排序操作
     */
    private void streamSort() {

        /**
         * |sorted()|产生一个新流，其中按自然顺序排序|
         * |sorted(Comparator com)|产生一个新流，其中按比较器顺序排序|
         */
        Stream.of(4, 3, 2, 1).sorted().forEach(System.out::println);

        System.out.println();
        Stream.of(4, 3, 2, 1).sorted(((o1, o2) -> o2.compareTo(o1))).forEach(System.out::println);

    }

    /**
     * 映射操作
     */
    private void mapOpera() {

        /**
         * |flatMap(Function f)|接收一个函数做为参数，将流中的每个值都换成另一个流，然后把所有的流连接成一个流
         */
        // map(Function f)：接收一个函数做为参数，该函数会被应用到每个元素上，并将其映射成一个新函数
        Stream<String> stream = createUserList().stream().map(User::getUserName).filter(t -> t.length() >= 4);
        List<String> list = stream.collect(Collectors.toList());
        System.out.println("演示\"map\"操作：userName长度>=4" + list.toString());


    }

    /**
     * 筛选与切片
     */
    private void select() {
        System.out.println("-------------------筛选与切片---------------------");
        // filter操作：salary >= 5000的
        System.out.println("演示\"过滤\"操作：只保留符合predicate的数据");
        createUserList().stream().filter(user -> user.salary >= 5000).forEach(System.out::println);


        // limit操作：截断流，使其元素不超过给定数量
        System.out.println("演示\"截断\"操作：只保留不超过要求的数量的数据");
        createUserList().stream().limit(3).forEach(System.out::println);


        // limit操作：跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补。
        System.out.println("演示\"跳过\"操作：不要前n个元素，若不足，则返回空流");
        createUserList().stream().skip(3).forEach(System.out::println);

        //  distinct()|筛选，通过流所生成元素的hashCode()和equals()去除重复元素
        System.out.println("演示\"去重\"操作：通过流所生成元素的hashCode()和equals()去除重复元素");
        createUserList().stream().distinct().forEach(System.out::println);
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
