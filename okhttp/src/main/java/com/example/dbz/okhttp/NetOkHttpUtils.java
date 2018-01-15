package com.example.dbz.okhttp;

import android.content.Context;
import android.widget.Toast;

import com.example.dbz.okhttp.callback.StringCallback;
import com.example.dbz.okhttp.entity.BaseResult;
import com.example.dbz.okhttp.log.Log;
import com.example.dbz.okhttp.okhttp.BaseRequest;
import com.example.dbz.okhttp.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class NetOkHttpUtils {

    private static NetOkHttpUtils mInstance;
    private Gson mGson;

    public NetOkHttpUtils(){
        mGson = new Gson();
    }

    public static NetOkHttpUtils getInstance(){
        if (mInstance == null){
            mInstance = new NetOkHttpUtils();
        }
        return mInstance;
    }

    /**
     * JSON 数据格式POST请求
     *
     * @param context       上下文
     * @param url           请求的网址
     * @param parame        请求是需要传的参数
     * @param reqClass      解析的实体类
     * @param baseRequest   网络请求返回的借口
     */
    public <T> void postJsonRequest(Context context, String url, Map<String, String> parame,
                                     Class<T> reqClass, BaseRequest<T> baseRequest){
        postJsonRequest(context, url, parame, reqClass, baseRequest, false, true);
    }



    /**
     * JSON 数据格式POST请求
     *
     * @param context           上下文
     * @param url               请求的网址
     * @param parame            请求是需要传的参数
     * @param reqClass          解析的实体类
     * @param baseRequest       网络请求返回的借口
     * @param needAES
     * @param showFailMessage  是否显示失败的信息
     */
    private <T> void postJsonRequest(final Context context, final String url, Map<String, String> parame,
                                     final Class<T> reqClass, final BaseRequest<T> baseRequest, final boolean needAES,
                                     final boolean showFailMessage){

        if (context == null) return;

        OkHttpUtils
                .postRequest()
                .url(url)
                .params(parame)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showMsg(context, url, e, showFailMessage);
                        Log.e("call = " + call.toString());
                        Log.e("e = " + e.getMessage());
                        Log.e("id = " + id);
                        if (baseRequest != null){
                            baseRequest.onError();
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (StringUtils.isEmpty(response)){
                            showMsg(context, url, null, showFailMessage);
                            if (baseRequest != null) {
                                baseRequest.onError();
                            }
                        } else {
                            Log.e("response = " + response.toString());
                            Log.e("id = " + id);
                            if (reqClass == null) return;
                            T Request = null;
                            try {
                                Request = parseJson(context, response, reqClass, needAES);
                                if (baseRequest != null){
                                    baseRequest.onSucceed(Request);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("e = " + e.getMessage());
                                showMsg(context, url, e, showFailMessage);
                                if (baseRequest != null){
                                    baseRequest.onError();
                                }
                            }
                        }
                    }
                });

    }


    /**
     * 解析Json字符串
     */
    private <T> T parseJson(Context context, String strJson, Class<T> reqClass, boolean needAES)
            throws Exception{
        if (context == null || StringUtils.isEmpty(strJson)) return null;

        T T = null;
        try {
            Log.e(strJson);
            T = mGson.fromJson(strJson, reqClass);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Log.e(e.getMessage());
        }

        return T;
    }


    /**
     * 显示请求网络异常失败信息
     */
    private void showMsg(Context context, String url, Object object, boolean showMsg){
        if (context == null || !showMsg) return;
        Log.e(object.toString());
        try {
            if (object == null){
                Toast.makeText(context, "服务器走神了，请稍后再试", Toast.LENGTH_SHORT).show();
            } else if (object instanceof BaseResult) {
                BaseResult error = (BaseResult) object;
                Toast.makeText(context, error.error, Toast.LENGTH_SHORT).show();
            } else if (object instanceof String) {
                Toast.makeText(context, (String) object, Toast.LENGTH_SHORT).show();
            } else if (object instanceof Exception){
                Toast.makeText(context, "服务器走神了，请稍后再试", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(e.getMessage());
        }
    }
}
