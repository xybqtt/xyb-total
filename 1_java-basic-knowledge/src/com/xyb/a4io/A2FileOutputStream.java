package com.xyb.a4io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 字节输入流
 */
public class A2FileOutputStream {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\devTools\\JetBrains\\idea20210301\\topWorkspace\\xyb-total-project\\1_java-basic-knowledge\\src\\com\\xyb\\a4io\\1.txt");

        // 通过单字节写取文件
        write(file);

    }

    /**
     *
     * @param file
     */
    private static void write(File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file); // 如果没有文件则会创建文件，注意目录必须存在。文件存在，则会清空文件内容。
            System.out.println("通过byte写出文件字节到文件中：");
            String str = "helloworldaaaa.\nhelloworldbbbd.";
            for (int i = 0; i < str.length(); i++) {
                fos.write(str.charAt(i));
            }
            System.out.println("通过byte写出文件字节到文件中完成。");

            System.out.println("通过byte[]写出文件字节到文件中：");
            fos.write("asdfasdf".getBytes());
            System.out.println("通过byte[]写出文件字节到文件中完成。");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 写出一个byte
     * @param file
     */
    private static void writeByByteArr(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            System.out.print("通过byte[]写入到文件中：");
            String str = "helloworldaaa.\nhelloworldbbb.";
            byte[] bytes = new byte[8];
            for (int i = 0; i < str.length(); i++) {
//                bytes = str.
            }

            System.out.print("通过byte[]写入到文件结束");

            System.out.println();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
