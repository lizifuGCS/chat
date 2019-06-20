package com.qst.chat01;


import java.io.Closeable;
import java.io.IOException;

public class SxtUtils {

    public static void close(Closeable... targets){

        for (Closeable target:targets){
            if (null!=target){
                try {
                    target.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
