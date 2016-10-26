package com.zbb.grey.pilidemo.ui.view.home;


import com.zbb.grey.pilidemo.ui.bean.UserEntity;

/**
 * Created by jumook on 2016/10/25.
 */

public interface HomeViewPort {

    void initViewWithNative(boolean isNightMode);

    void switchDayNightMode();

    void refreshUserProfile(UserEntity userEntity);

    void refreshFragment(boolean isReplace);

    void refreshToolbar(int id);

    void highSearchSkip();

    void lowSearchSkip();

}
