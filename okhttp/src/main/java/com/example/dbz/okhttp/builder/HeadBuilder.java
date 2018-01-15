package com.example.dbz.okhttp.builder;

import com.example.dbz.okhttp.OkHttpUtils;
import com.example.dbz.okhttp.okhttp.OtherRequest;
import com.example.dbz.okhttp.okhttp.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers, id).build();
    }
}
