package com.zbb.grey.pilidemo.ui.presenter;

import com.zbb.grey.pilidemo.ui.bean.PhonePR;
import com.zbb.grey.pilidemo.ui.model.RegisterModel;
import com.zbb.grey.pilidemo.ui.view.register.RegisterViewPort;

import java.util.List;

/**
 * Created by jumook on 2016/10/28.
 */

public class RegisterPresenter {

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

    public void setCurrentPhonePR(int position) {
        for (PhonePR item : registerModel.getPhonePRList()) {
            item.isTrue = false;
        }
        PhonePR phonePR = registerModel.getPhonePRList().get(position);
        phonePR.isTrue = true;
        registerViewPort.refreshView(phonePR.name, phonePR.prefix);
    }

}
