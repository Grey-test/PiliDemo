package com.zbb.grey.pilidemo.ui.presenter;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

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

    /**
     * 重新获取验证码
     */
    public void resendCode() {
        //模拟
        downTimer.start();
    }

    /**
     * 校验手机验证码
     *
     * @param code 验证码
     */
    public void verifyCode(final String code) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (code.equals("1234")) {
                    proofCodeViewPort.nextOperation(true, "");
                } else {
                    proofCodeViewPort.nextOperation(false, "验证码错误,请重新输入");
                }
            }
        }, 1500);
    }

    public void shopDownTimer() {
        downTimer.cancel();
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
