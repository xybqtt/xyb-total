package com.xyb.a17clsloader2;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 自定义类加载器，并实现热替换
 */
public class A3MyClassLoader extends ClassLoader {

    private String rootDir;

    /**
     * 热替换的实现：即每次运行某个类时，都new一个自定义的ClassLoader，通过这个ClassLoader去加载指定位置的class文件，再通过反射调用此类的方法，
     * 那么只要在某次时，修改了class文件，就可以实现热替换。
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        while (true) {
            // 此处参数填此类的绝对路径
            A3MyClassLoader classLoader = new A3MyClassLoader("D:\\java\\idea200101\\topWorkspace\\xyb-total-project\\jvm-4\\src");
            Class cls = classLoader.findClass("com.xyb.a17clsloader2.Demo1");
            Object obj = cls.newInstance();
            cls.getMethod("hot").invoke(obj);
            Thread.sleep(5000);
        }

    }

    public A3MyClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String clsName) throws ClassNotFoundException {
        Class cls = this.findLoadedClass(clsName);
        FileChannel fileChannel = null;
        WritableByteChannel outChannel = null;
        if (cls == null) {
            try {
                String classFile = this.rootDir + "\\" + clsName.replace('.', '\\') + ".class";
                FileInputStream fis = new FileInputStream(classFile);
                fileChannel = fis.getChannel();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                outChannel = Channels.newChannel(baos);

                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

                while (true) {
                    int i = fileChannel.read(buffer);
                    if (i == 0 || i == -1)
                        break;
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }

                byte[] bytes = baos.toByteArray();
                cls = defineClass(clsName, bytes, 0, bytes.length);

                if (fileChannel != null)
                    fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (outChannel != null)
                    outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return cls;
    }

}
