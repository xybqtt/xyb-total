package com.xyb.a15classinstruction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 指令8：异常处理
 */
public class A8ExceptionTest {

    public void throwZero(int i) {
        if(i == 0)
            throw new RuntimeException("参数值为0");
    }

    /**
     * 方法的Code属性不包含方法中的异常表部分，异常属性是方法的另一个属性。
     * @param i
     * @throws RuntimeException
     */
    public void throwOne(int i) throws RuntimeException {
        if(i == 1)
            throw  new RuntimeException("参数值为1");
    }

    public void throwArithmetic() {
        int i = 10;
        int j = i / 0;
        System.out.println(j);
    }

    public void tryCatch() {
        try {
            File file = new File("");
            FileInputStream fis = new FileInputStream(file);

            String info = "hello!";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看最后返回结果是什么
     * @return
     */
    public static String func() {

        String str = "a";
        try {
            return str;
        } finally {
            str = "ab";
        }

    }

}
