package com.jstudio.network.base;

import com.google.gson.JsonObject;

/**
 * Created by Jason
 */
public interface NetworkCallback {

    BaseRequester setupRequester();

    void onStart(String method);

    void onSuccess(String method, JsonObject wholeObject);

    void onFailed(int code, String method, String msg, JsonObject object);

    void onFinish(String method);

}
