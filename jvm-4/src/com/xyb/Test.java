package com.xyb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * -Xms60m -Xmx60m -XX:SurvivorRatio=8
 */
public class Test {

    public static void main(String[] args) {
        //
        List<byte[]> list = new ArrayList<byte[]>();

        for (int i = 0; i < 1000; i++) {
            byte[] arr = new byte[1024 * 1000];
            list.add(arr);
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
