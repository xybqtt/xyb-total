package com.xyb.a2clsloader;

import com.sun.glass.utils.NativeLibLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import sun.security.ec.CurveDB;

import java.net.URL;

public class A1ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {

        // 获取3个加载器
        getAllClassLoader();

        // 获取加载器的加载器
        getClOfCl();

        // 获取加载器可以加载的路径
        getLoadPathOfCl();

        // 获取加载器的途径
        fourWayToGetCl();
    }

    /**
     * 4种获取ClassLoader的途径
     */
    private static void fourWayToGetCl() throws ClassNotFoundException {

        // 1、Class实例获取对应类加载器
        ClassLoader cl = Class.forName("java.lang.String").getClassLoader();
        System.out.println(cl);

        // 2、获取当前线程的类加载器
        cl = Thread.currentThread().getContextClassLoader();
        System.out.println(cl);

        // 3、获取系统的类加载器
        cl = ClassLoader.getSystemClassLoader().getParent();
        System.out.println(cl);


    }

    /**
     * 获取加载器可以加载的路径
     */
    private static void getLoadPathOfCl() {
        // 获取BootstrapClassLoader能够加载的api路径
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        System.out.println("引导类加载器能加载的路径：");
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }

        // 获取ExtClassLoader能够加载的api路径
        String extDirs = System.getProperty("java.ext.dirs");
        System.out.println("扩展类加载器能加载的路径：" + extDirs);

    }

    /**
     * 自定义加载器本质也是class，获取其加载器
     */
    private static void getClOfCl() {

        // 获取系统类加载器的加载器，是引导类加载器，不是扩展类加载器
        ClassLoader classLoader = ClassLoader.getSystemClassLoader().getClass().getClassLoader();
        System.out.println("获取系统类加载器的加载器：" + classLoader);

        // 获取扩展类加载器的加载器
        classLoader = ClassLoader.getSystemClassLoader().getParent().getClass().getClassLoader();
        System.out.println("获取扩展类加载器的加载器：" + classLoader);

    }

    /**
     * 获取3个类加载器
     */
    public static void getAllClassLoader() {
        // 1、获取系统类加载器sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader systemCl = ClassLoader.getSystemClassLoader();
        System.out.println("获取系统类加载器：" + systemCl);

        // 2、获取系统类加载器的上层加载器：扩展类加载器sun.misc.Launcher$ExtClassLoader@1b6d3586
        ClassLoader extCl = systemCl.getParent();
        System.out.println("获取扩展类加载器，通过系统类加载器的parent获取" + extCl);

        extCl = com.sun.glass.utils.NativeLibLoader.class.getClassLoader();
        System.out.println("获取扩展类加载器，通过cls对象.getClassLoader()：" + extCl);


        // 3、获取"扩展类加载器"的上层加载器：引导类加载器 null
        ClassLoader bootstrapCl = extCl.getParent();
        System.out.println("获取引导类加载器：" + bootstrapCl);

    }

}
