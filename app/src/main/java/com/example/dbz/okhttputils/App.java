package com.example.dbz.okhttputils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class App extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
