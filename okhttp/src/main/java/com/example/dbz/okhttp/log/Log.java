package com.example.dbz.okhttp.log;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class Log {

    private static final String TAG = "OkHttpUtils: ";

    public static void e(String msg){
        android.util.Log.e(TAG, msg);
    }

    public static void i(String msg){
        android.util.Log.e(TAG, msg);
    }

    public static void d(String msg){
        android.util.Log.e(TAG, msg);
    }

}
