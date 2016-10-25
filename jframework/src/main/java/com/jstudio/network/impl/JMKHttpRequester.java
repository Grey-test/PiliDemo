package com.jstudio.network.impl;

import android.content.Context;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.jstudio.utils.JLog;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.jstudio.network.base.AsyncRequestCreator;
import com.jstudio.network.base.BaseRequester;
import com.jstudio.network.base.NetworkCallback;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jason
 */
public class JMKHttpRequester extends BaseRequester {

    public static final String TAG = JMKHttpRequester.class.getSimpleName();

    public JMKHttpRequester(NetworkCallback callback) {
        super(callback);
    }

    @Override
    public void postRequest(final Context context, String url, RequestParams params, final String method) {
        JLog.d(TAG, JMKRequestHelper.parseApi(url, params));
        TextHttpResponseHandler handler = new TextHttpResponseHandler() {
            @Override
            public void onStart() {
                mCallback.onStart(method);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String result) {
                //通过判断method，可以在此处处理事件统计
                JMKRequestHelper.resolveEvent(context, method);

                try {
                    JsonObject response = new JsonParser().parse(result).getAsJsonObject();
                    int status = JMKRequestHelper.parseStatus(response);
                    int code = JMKRequestHelper.parseCode(response);
                    if (status == 1) {
                        JLog.i(TAG, result);
                        mCallback.onSuccess(method, response);
//                        if (response.has(JMKRequestHelper.DATA)) {
//                            JsonObject dataObject = JMKRequestHelper.parseData(response);
//                            mCallback.onGetDataObjectSuccess(method, dataObject);
//                            if (dataObject.has(JMKRequestHelper.LIST)) {
//                                mCallback.onGetListObjectSuccess(method, JMKRequestHelper.parseList(response));
//                            }
//                        }
                    } else {
                        JLog.w(TAG, result);
                        String msg = JMKRequestHelper.parseMsg(response);
                        //在一下这个类中处理错误码的问题，例如登录超时跳转到登录界面等操作，
                        //强转context为activity可以处理对话框弹出等问题
                        JMKRequestHelper.resolveResponseCode(context, code);
                        mCallback.onFailed(code, method, msg, response);
                    }
                } catch (JsonSyntaxException e) {
                    JLog.e(TAG, PARSE_JSON_EXCEPTION);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                JLog.e(TAG, responseString);
                //statusCode为0时表示网路不通
                JMKRequestHelper.resolveHttpStatusCode(context, statusCode);
                mCallback.onFailed(statusCode, method, NETWORK_ERROR, null);
            }

            @Override
            public void onFinish() {
                mCallback.onFinish(method);
            }
        };
        AsyncRequestCreator.getClient().post(context, url, params, handler);
    }

    @Override
    public void httpGet(Context context, String url, String method) {

    }

}
