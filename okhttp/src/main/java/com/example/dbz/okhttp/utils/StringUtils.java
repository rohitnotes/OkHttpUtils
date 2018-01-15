package com.example.dbz.okhttp.utils;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class StringUtils {


    public static boolean isEmpty(String str){
        if (str == null || str.length() == 0){
            return true;
        }else {
            return false;
        }
    }
}
