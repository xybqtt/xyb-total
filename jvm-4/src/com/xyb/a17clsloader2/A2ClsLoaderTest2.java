package com.xyb.a17clsloader2;

public class A2ClsLoaderTest2 {

    public static void main(String[] args) throws ClassNotFoundException {

        // 获取和输出系统类加载器
        ClassLoader systemClsLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClsLoader);

        // 获取和输出扩展类加载器
        ClassLoader extClsLoader = systemClsLoader.getParent();
        System.out.println(extClsLoader);

        // 获取和输出引导类加载器null
        ClassLoader bootstrapClsLoader = extClsLoader.getParent();
        System.out.println(bootstrapClsLoader);


        // #####################
        ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
        System.out.println(classLoader);

        // 自定义的类默认使用系统类加载器
        ClassLoader classLoader1 = Class.forName("com.xyb.a17clsloader2.A2ClsLoaderTest2").getClassLoader();
        System.out.println(classLoader1);

        // 数组类型是在运行创建的，其加载器与数组元素的加载器相同
        String[] arrStr = new String[10];
        System.out.println(arrStr.getClass().getClassLoader());

        A2ClsLoaderTest2[] a2s = new A2ClsLoaderTest2[10];
        System.out.println(a2s.getClass().getClassLoader());

        int[] intArr = new int[10];
        System.out.println(intArr.getClass().getClassLoader()); // 基本类型是没有引导器加载的，此时的null指不需要类加载器，而不是引导类加载器。
    }

}
