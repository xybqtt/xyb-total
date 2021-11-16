package com.xyb.a2clsloader;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 自定义的磁盘加载器，从磁盘加载class字节码文件。
 * 1、重写findClass方法；
 * 2、
 */
public class A2MyDiskClassLoader extends ClassLoader {

    public static void main(String[] args) {
        A2MyDiskClassLoader myClassLoader2 = new A2MyDiskClassLoader();

        try {
            // 此处写个One类，编译后，删除就可测试，不能有源码，有了就会由AppClassLoader加载，我们需要从磁盘读取class文件。
            Class<?> cls = Class.forName("com.xyb.clsloader2.One", true, myClassLoader2);
            Object obj = cls.newInstance();
            System.out.println(obj.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找名为name的类，返回对应cls实例
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            System.out.println("进入findClass");
            String filepath = "D:\\java\\idea200101\\topWorkspace\\xyb-total-project\\jvm-4\\src\\com\\xyb\\clsloader2\\One.class";
            byte[] result = getClassFromCustomPath(filepath);
            System.out.println("result.length = " + result.length);
            if(result == null)
                throw new FileNotFoundException();
            else
                return defineClass(name, result, 0, result.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    /**
     * 设定从
     * @param name
     * @return
     */
    private byte[] getClassFromCustomPath(String name) throws IOException {
        FileInputStream fis = new FileInputStream(new File(name));
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true){
            int i = fc.read(by);
            if (i == 0 || i == -1)
                break;
            by.flip();
            wbc.write(by);
            by.clear();
        }
        fis.close();
        return baos.toByteArray();
    }
}
