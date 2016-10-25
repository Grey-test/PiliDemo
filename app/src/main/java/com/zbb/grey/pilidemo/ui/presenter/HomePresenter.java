package com.zbb.grey.pilidemo.ui.presenter;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.MenuItem;

import com.jstudio.utils.PreferencesUtils;
import com.zbb.grey.pilidemo.R;
import com.zbb.grey.pilidemo.base.GlobalVar;
import com.zbb.grey.pilidemo.constant.AppConstant;
import com.zbb.grey.pilidemo.ui.view.home.IHomeView;

/**
 * Home界面的操作类
 * Created by jumook on 2016/10/25.
 */

public class HomePresenter {

    private IHomeView mHomeView;
    private PreferencesUtils preferences;

    public HomePresenter(Context context, IHomeView mHomeView) {
        this.mHomeView = mHomeView;
        preferences = PreferencesUtils.getInstance(context, AppConstant.APP_PREFERENCE);
    }

    public void setIsFirstLogin() {
        preferences.putBoolean(AppConstant.APP_IS_FIRST_BOOT, false).apply();
    }

    public void navigationItemSelected(MenuItem item) {
        Fragment destFragment;
        String fragmentTag;
        switch (item.getItemId()) {
            case R.id.nat_home:
                break;
            case R.id.nav_vip:
                break;
            case R.id.nav_download:
                break;
            case R.id.nav_collect:
                break;
            case R.id.nav_history:
                break;
            case R.id.nav_friends:
                break;
            case R.id.nav_purse:
                break;
            case R.id.nav_theme_choose:
                break;
            case R.id.nav_recommend:
                break;
            case R.id.nav_settings:
                break;
        }
    }

    public void switchDayNightMode(Context context, AlertDialog.Builder builder) {
        final boolean isNightMode = preferences.getBoolean(AppConstant.APP_IS_NIGHT_MODE, false);
        builder.setTitle(isNightMode ? context.getString(R.string.switch_to_day_mode) : context.getString(R.string.switch_to_night_mode))
                .setMessage(R.string.tip_restart_app)
                .setView(null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        preferences.putBoolean(AppConstant.APP_IS_NIGHT_MODE, !isNightMode).apply();
                        mHomeView.switchDayNightMode();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
    }

    public void refreshUserProfile() {
        mHomeView.refreshUserProfile(GlobalVar.getInstance().getUserEntity());
    }

    public void onClickSearch() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mHomeView.highSearchSkip();
        } else {
            mHomeView.lowSearchSkip();
        }
    }

}
