package com.example.dbz.okhttp.okhttp;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public interface BaseRequest<T> {

    /**
     * 请求成功
     */
    void onSucceed(T result) throws Exception;

    /**
     * 请求失败
     */
    void onError();

}
