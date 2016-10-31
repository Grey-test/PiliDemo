package com.zbb.grey.pilidemo.ui.presenter;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.zbb.grey.pilidemo.constant.BundleConstant;
import com.zbb.grey.pilidemo.ui.model.ProofCodeCodeModel;
import com.zbb.grey.pilidemo.ui.view.register.ProofCodeViewPort;

/**
 * Created by jumook on 2016/10/31.
 */

public class ProofCodePresenter {

    private ProofCodeViewPort proofCodeViewPort;
    private ProofCodeCodeModel proofCodeCodeModel;
    private DownTimer downTimer;

    public ProofCodePresenter(ProofCodeViewPort proofCodeViewPort) {
        this.proofCodeViewPort = proofCodeViewPort;
        proofCodeCodeModel = new ProofCodeCodeModel();
        downTimer = new DownTimer(60 * 1000, 1000);
    }


    /**
     * 解析由Register数据
     *
     * @param bundle 数据源
     */
    public void initData(Bundle bundle) {
        if (bundle == null) {
            proofCodeViewPort.initView(false, "");
        } else {
            String phonePRName = bundle.getString(BundleConstant.PHONEPR_NAME);
            String phone = bundle.getString(BundleConstant.PHONE);
            String phoneNumber = String.format("%s%s", phonePRName, phone);
            proofCodeViewPort.initView(true, phoneNumber);
            //启动倒计时
            downTimer.start();
        }
    }

    public void resendCode() {
        downTimer.start();
    }

    /**
     * 倒计时
     */
    private class DownTimer extends CountDownTimer {

        DownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int time = (int) (millisUntilFinished / 1000);
            String timeStr = String.format("%s秒后可以重发", time);
            proofCodeViewPort.refreshTime(false, timeStr);
        }

        @Override
        public void onFinish() {
            proofCodeViewPort.refreshTime(true, "重发验证码");
        }
    }

}
