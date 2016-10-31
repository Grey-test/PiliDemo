package com.zbb.grey.pilidemo.ui.presenter;

import android.os.Bundle;
import android.os.Handler;

import com.jstudio.utils.JLog;
import com.zbb.grey.pilidemo.constant.BundleConstant;
import com.zbb.grey.pilidemo.ui.bean.PhonePR;
import com.zbb.grey.pilidemo.ui.model.RegisterModel;
import com.zbb.grey.pilidemo.ui.view.register.RegisterViewPort;

import java.util.List;

/**
 * Created by jumook on 2016/10/28.
 */

public class RegisterPresenter {

    private static final String TAG = "RegisterPresenter";

    private RegisterViewPort registerViewPort;
    private RegisterModel registerModel;

    public RegisterPresenter(RegisterViewPort registerViewPort) {
        this.registerViewPort = registerViewPort;
        registerModel = new RegisterModel();
    }

    /**
     * 获取国家or地区的手机号前缀
     *
     * @return
     */
    public List<PhonePR> getPhonePRList() {
        return registerModel.getPhonePRList();
    }

    /**
     * 设置用户当前选中的国家or地区
     *
     * @param position id
     */
    public void setCurrentPhonePR(int position) {
        for (PhonePR item : registerModel.getPhonePRList()) {
            item.isTrue = false;
        }
        PhonePR phonePR = registerModel.getPhonePRList().get(position);
        phonePR.isTrue = true;
        registerModel.setPhonePR(phonePR);
        registerViewPort.refreshView(phonePR.name, phonePR.prefix);
    }


    /**
     * 设置获取验证码按钮的状态
     *
     * @param s editText charSequence
     */
    public void setGetCodeState(CharSequence s) {
        if (s.toString().length() > 0) {
            registerViewPort.setCodeState(true);
            registerModel.setPhone(s.toString());
        } else {
            registerViewPort.setCodeState(false);
            registerModel.setPhone("");
        }
    }

    /**
     * 获取验证码
     */
    public void getCode() {
        //TODO HTPP 模拟请求验证码
        final PhonePR phonePR = registerModel.getPhonePR();
        JLog.d(TAG, "PhonePR: id = " + phonePR.id + " area = " + phonePR.name + "\nphoneNumber = " + registerModel.getPhone());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (registerModel.getPhone().equals("15000000000")) {
                    registerModel.setCode("1234");
                    Bundle bundle = new Bundle();
                    bundle.putString(BundleConstant.PHONEPR_NAME, phonePR.prefix);
                    bundle.putString(BundleConstant.PHONE, registerModel.getPhone());
                    bundle.putString(BundleConstant.CODE, registerModel.getCode());
                    registerViewPort.onCodeCallBack("", bundle);
                } else {
                    registerViewPort.onCodeCallBack("该手机号码已注册", null);
                }
            }
        }, 3 * 1000);
    }



}
