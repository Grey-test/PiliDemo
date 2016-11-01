package com.zbb.grey.pilidemo.ui.presenter;

import android.content.Context;
import android.os.Handler;

import com.jstudio.utils.PreferencesUtils;
import com.zbb.grey.pilidemo.constant.AppConstant;
import com.zbb.grey.pilidemo.ui.view.register.SetPasswordViewPort;


/**
 * Created by jumook on 2016/11/1.
 */

public class SetPasswordPresenter {

    private SetPasswordViewPort viewPort;
    private PreferencesUtils preferencesUtils;

    public SetPasswordPresenter(SetPasswordViewPort viewPort, Context context) {
        this.viewPort = viewPort;
        preferencesUtils = PreferencesUtils.getInstance(context, AppConstant.APP_PREFERENCE);
    }

    public void checkInfo(String firstPassword, String secondPassword, String nickName) {
        if (firstPassword.length() < 6) {
            viewPort.upLoadInfo(false, "密码不能少于6位字符");
            return;
        }
        if (nickName.length() < 3) {
            viewPort.upLoadInfo(false, "昵称不能少于3位字符");
            return;
        }
        if (!firstPassword.equals(secondPassword)) {
            viewPort.upLoadInfo(false, "两次密码输入不一致");
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPort.upLoadInfo(true, "");
                preferencesUtils.putBoolean(AppConstant.USER_IS_LOGIN, true);
            }
        }, 1500);
    }


}
