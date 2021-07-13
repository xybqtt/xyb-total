package com.xyb.string10;

import java.util.HashSet;
import java.util.Set;

/**
 * 不同JDK版本常量池位置测试，注意切换jdk版本
 * JDK6：
 *  -XX:PermSize=10m -XX:MaxPermSize=10m -Xms10m -Xmx10m
 *  会报OOM:PermGen Size
 *
 * JDK8：
 *  -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m  -Xms10m -Xmx10m
 * 会报：OOM:java heap space
 */
public class ConstantPoolLoc2 {

    public static void main(String[] args) {

        int i = 0;
        Set<String> set = new HashSet<String>();
        while (true){
            set.add(String.valueOf(i++).intern());
        }


    }

}
