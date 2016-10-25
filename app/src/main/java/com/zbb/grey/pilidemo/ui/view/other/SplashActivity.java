package com.zbb.grey.pilidemo.ui.view.other;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jstudio.utils.JLog;
import com.jstudio.utils.PreferencesUtils;
import com.zbb.grey.pilidemo.R;
import com.zbb.grey.pilidemo.base.AppBaseActivity;
import com.zbb.grey.pilidemo.constant.AppConstant;
import com.zbb.grey.pilidemo.ui.view.home.HomeActivity;


/**
 * 加载界面
 * Created by jumook on 2016/10/25.
 */
public class SplashActivity extends AppBaseActivity {

    public static final String TAG = "SplashActivity";

    @Override
    protected boolean onRestoreState(Bundle paramSavedState) {
        return false;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initialization() {
        PreferencesUtils preferences = PreferencesUtils.getInstance(this, AppConstant.APP_PREFERENCE);
        boolean isFirstLogin = preferences.getBoolean("APP_IS_FIRST_BOOT", false);
        JLog.d(TAG, "isFirstLogin =  " + isFirstLogin);
        if (isFirstLogin) {
            openActivity(new Intent(SplashActivity.this, GuidePageActivity.class));
        } else {
            openActivity(new Intent(SplashActivity.this, HomeActivity.class));
        }
    }

    @Override
    protected void bindEvent() {

    }

    private void openActivity(final Intent intent) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 4 * 1000);
    }

}
