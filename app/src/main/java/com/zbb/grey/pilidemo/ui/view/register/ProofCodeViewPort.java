package com.zbb.grey.pilidemo.ui.view.register;

/**
 * Created by jumook on 2016/10/31.
 */

public interface ProofCodeViewPort {


    void initView(boolean isTrue, String phoneNumber);

    void refreshTime(boolean isTrue, String time);

    void nextOperation(boolean isTrue, String message);

}
