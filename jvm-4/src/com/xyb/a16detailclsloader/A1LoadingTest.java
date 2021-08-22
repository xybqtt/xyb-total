package com.xyb.a16detailclsloader;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 类加载的：加载阶段
 * 通过Class类，获取了java.lang.String的所有方法信息，并打印方法访问标识符、描述符
 */
public class A1LoadingTest {

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("java.lang.String");

            //获取当前运行时类声明的所有方法
            Method[] ms = clazz.getDeclaredMethods();
            for (Method m : ms) {
                //获取方法的修饰符
                String mod = Modifier.toString(m.getModifiers());
                System.out.print(mod + "");
                //获取方法的返回值类型
                String returnType = (m.getReturnType()).getSimpleName();
                System.out.print(returnType + "");
                //获取方法名
                System.out.print(m.getName() + "(");
                //获取方法的参数列表
                Class<?>[] ps = m.getParameterTypes();
                if (ps.length == 0)
                    System.out.print(')');

                for (int i = 0; i < ps.length; i++) {
                    char end = (i == ps.length - 1) ? ')' : ',';
                    //获取参教的类型
                    System.out.print(ps[i].getSimpleName() + end);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
