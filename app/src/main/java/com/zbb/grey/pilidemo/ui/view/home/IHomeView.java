package com.zbb.grey.pilidemo.ui.view.home;

import android.support.v4.app.Fragment;

import com.zbb.grey.pilidemo.ui.bean.UserEntity;

/**
 * Created by jumook on 2016/10/25.
 */

public interface IHomeView {

    void switchDayNightMode();

    void refreshUserProfile(UserEntity userEntity);

    void replaceFragment(Fragment fragment, String tag);

    void addFragment(Fragment fragment, String tag);

    void highSearchSkip();

    void lowSearchSkip();

}
