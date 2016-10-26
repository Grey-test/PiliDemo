package com.zbb.grey.pilidemo.ui.view.other;

import android.content.Intent;
import android.support.v7.app.AppCompatDelegate;

import com.jstudio.utils.PreferencesUtils;
import com.zbb.grey.pilidemo.base.AppBaseActivity;
import com.zbb.grey.pilidemo.constant.AppConstant;

/**
 * Created by jumook on 2016/10/25.
 */
public class LaunchActivity extends AppBaseActivity {

    @Override
    protected void setContentView() {
    }

    @Override
    protected void initialization() {
    }

    @Override
    protected void bindEvent() {
    }

    @Override
    protected void doMoreInOnCreate() {
        PreferencesUtils preferences = PreferencesUtils.getInstance(this, AppConstant.APP_PREFERENCE);

        boolean isNightMode = preferences.getBoolean(AppConstant.APP_IS_NIGHT_MODE, false);
        AppCompatDelegate.setDefaultNightMode(isNightMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
//        if (preferences.getBoolean(AppConstant.APP_IS_FIRST_BOOT, true)) {
            Intent toLaunchScreen = new Intent(this, SplashActivity.class);
            startActivity(toLaunchScreen);
//        } else if (preferences.getBoolean(AppConstant.APP_HAS_ADVERTISEMENT, false)) {
//            Intent toAdvertisement = new Intent(this, AdActivity.class);
//            startActivity(toAdvertisement);
//        } else {
//            Intent toHomeActivity = new Intent(this, HomeActivity.class);
//            startActivity(toHomeActivity);
//        }
        finish();
    }

    @Override
    public void onBackPressed() {
    }
}
