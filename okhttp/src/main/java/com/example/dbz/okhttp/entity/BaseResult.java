package com.example.dbz.okhttp.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class BaseResult implements Serializable{
    public String error;
    public int code;
    public String msg;
    public String time;
}
