package com.xyb.a4io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 字节输入流
 */
public class A3FileInputStream {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\devTools\\JetBrains\\idea20210301\\topWorkspace\\xyb-total-project\\1_java-basic-knowledge\\src\\com\\xyb\\a4io\\1.txt");

        // 通过单字节读取文件
        readBySingleByte(file);

        // 通过字节数组读取文件
        readByByteArr(file);
    }

    /**
     * fis.read(byte[])方法每次会读取一个byte[]，并将读到的数据赋值给byte[]中，当读到-1时就是文件末尾。
     * 返回一个int，说明读了几个字节。
     *
     * @param file
     */
    private static void readByByteArr(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bytes = new byte[8];
            int readLen = 0;
            System.out.println("通过byte[]读取文件字节：");
            while ((readLen = fis.read(bytes)) != -1) { // 读到-1证明读到了文件末尾
                // 使用String将bytes数组输出
                System.out.print(new String(bytes, 0, readLen));
            }
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


    /**
     * fis.read()方法每次会读取一个byte，当读到-1时就是文件末尾
     * @param file
     */
    private static void readBySingleByte(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            int readData = 0;
            System.out.print("通过int读取1个文件字节：");
            while ((readData = fis.read()) != -1) { // 读到-1证明读到了文件末尾
                System.out.print((char) readData);
            }
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
