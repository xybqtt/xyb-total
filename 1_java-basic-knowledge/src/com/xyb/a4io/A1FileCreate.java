package com.xyb.a4io;

import java.io.File;
import java.io.IOException;

public class A1FileCreate {

    public static void main(String[] args) throws IOException {
        // 1、创建文件
        File file = createFile();

        // 2、获取文件相关信息
        getFileInfo(file);

        // 3、创建目录
        File dir = createDir();

    }

    private static File createDir() {
        String currDir = System.getProperty("user.dir");
        File dir = new File(currDir + File.separator + "a");
        System.out.println("查看目录信息：\n" + print(dir));

        dir.mkdirs(); // 创建多级目录
        System.out.println("再次查看目录信息：\n" + print(dir));

        return null;
    }

    /**
     * 获取文件信息
     * @param file 文件
     */
    private static void getFileInfo(File file) {
        System.out.println("获取文件信息：\n" + print(file));
    }

    private static File createFile() throws IOException {
        // 获取当前路径，注意这个目录是工作路径，不一定就是在这个地方
        String currDir = System.getProperty("user.dir");
        System.out.println(currDir);

        // 1、根据路径创建文件
        File file = new File(currDir + File.separator + "3.txt");
        file.createNewFile();

        // 2、根据父目录文件 + 子文件路径构建
        file = new File(new File(currDir), "2.txt");
        file.createNewFile();

        // 3、根据父目录 + 子文件路径构建
        file = new File(currDir, "1.txt");
        file.createNewFile();
        System.out.println("创建文件成功：" + file.getAbsolutePath() + "\n");
        return file;
    }

    public static String print(File file) {
        try {
            StringBuilder sb = new StringBuilder("");
            sb.append("    文件名称：" + file.getName() + "\n");
            sb.append("    文件标准化路径：" + file.getCanonicalPath() + "\n");
            sb.append("    文件绝对路径：" + file.getAbsolutePath() + "\n");
            sb.append("    父目录：" + file.getParent() + "\n");
            sb.append("    大小(byte)：" + file.length() + "\n");
            sb.append("    是否存在：" + file.exists() + "\n");
            sb.append("    是文件？：" + file.isFile() + "\n");
            sb.append("    是目录？：" + file.isDirectory() + "\n");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
