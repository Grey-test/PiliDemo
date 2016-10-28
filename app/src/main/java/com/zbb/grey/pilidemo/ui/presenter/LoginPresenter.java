package com.zbb.grey.pilidemo.ui.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.jstudio.utils.PreferencesUtils;
import com.zbb.grey.pilidemo.constant.AppConstant;
import com.zbb.grey.pilidemo.ui.model.LoginModel;
import com.zbb.grey.pilidemo.ui.view.register.LoginViewPort;

import java.util.List;

/**
 * Created by jumook on 2016/10/28.
 */

public class LoginPresenter {

    private LoginViewPort loginViewPort;
    private LoginModel loginModel;
    private PreferencesUtils preferences;

    public LoginPresenter(LoginViewPort loginViewPort, Activity activity) {
        this.loginViewPort = loginViewPort;
        loginModel = new LoginModel();
        preferences = PreferencesUtils.getInstance(activity, AppConstant.APP_PREFERENCE);
    }


    /**
     * 设置登录按钮的状态
     *
     * @param isUser   是否是用户帐号
     * @param userName 用户帐号
     * @param password 用户密码
     */
    public void checkLoginInState(boolean isUser, CharSequence userName, CharSequence password) {
        if (isUser) {
            loginModel.setUserName(String.valueOf(userName));
        } else {
            loginModel.setPassword(String.valueOf(password));
        }
        //判断用户帐号与密码是否都存在
        if (!TextUtils.isEmpty(loginModel.getUserName()) && !TextUtils.isEmpty(loginModel.getPassword())) {
            loginViewPort.setLoginInStatue(true);
        } else {
            loginViewPort.setLoginInStatue(false);
        }
    }

    public List<String> getUserNameList() {
        return loginModel.getUserNameList(preferences.getSet(AppConstant.LOGIN_USER_NAME_LIST, null));
    }

    public void saveUserName() {
    }


}
