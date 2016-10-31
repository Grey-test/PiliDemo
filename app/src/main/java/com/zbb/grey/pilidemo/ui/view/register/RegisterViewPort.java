package com.zbb.grey.pilidemo.ui.view.register;

import android.os.Bundle;

/**
 * Created by jumook on 2016/10/28.
 */

public interface RegisterViewPort {

    void refreshView(String areaName, String PR);

    void setCodeState(boolean isTrue);

    void onCodeCallBack(String message, Bundle bundle);

}
