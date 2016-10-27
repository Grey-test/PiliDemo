package com.zbb.grey.pilidemo.ui.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;

import com.jstudio.utils.PreferencesUtils;
import com.zbb.grey.pilidemo.R;
import com.zbb.grey.pilidemo.base.GlobalVar;
import com.zbb.grey.pilidemo.constant.AppConstant;
import com.zbb.grey.pilidemo.ui.model.HomeModel;
import com.zbb.grey.pilidemo.ui.view.home.HomeFragment;
import com.zbb.grey.pilidemo.ui.view.home.HomeViewPort;
import com.zbb.grey.pilidemo.ui.view.home.fragment.HistoryRecordFragment;
import com.zbb.grey.pilidemo.ui.view.home.fragment.MyCollectFragment;
import com.zbb.grey.pilidemo.ui.view.home.fragment.MyConcernFragment;
import com.zbb.grey.pilidemo.ui.view.home.fragment.MyPurseFragment;
import com.zbb.grey.pilidemo.ui.view.home.fragment.ThemeSelectFragment;

import cz.msebera.android.httpclient.util.TextUtils;

/**
 * Home界面的操作类
 * Created by jumook on 2016/10/25.
 */

public class HomePresenter {

    private HomeViewPort mHomeView;
    private HomeModel mHomeModel;
    private PreferencesUtils preferences;

    public HomePresenter(Activity activity, HomeViewPort mHomeView) {
        this.mHomeView = mHomeView;
        mHomeModel = new HomeModel();
        preferences = PreferencesUtils.getInstance(activity, AppConstant.APP_PREFERENCE);
    }

    public void initViewWithNative() {
        preferences.putBoolean(AppConstant.APP_IS_FIRST_BOOT, false).apply();
        mHomeView.initViewWithNative(preferences.getBoolean(AppConstant.APP_IS_NIGHT_MODE, false));
    }

    /**
     * 抽屉菜单栏点击事件
     *
     * @param item                    MenuItem
     * @param mSupportFragmentManager FragmentManager
     */
    public void navigationItemSelected(MenuItem item, FragmentManager mSupportFragmentManager) {
        Fragment destFragment = null;
        String fragmentTag = "";
        boolean isReplace = false;
        switch (item.getItemId()) {
            case R.id.nav_home:
                isReplace = true;
                fragmentTag = HomeFragment.class.getSimpleName();
                destFragment = mSupportFragmentManager.findFragmentByTag(fragmentTag);
                if (destFragment == null) destFragment = new HomeFragment();
                break;
            case R.id.nav_vip:
                break;
            case R.id.nav_download:
                break;
            case R.id.nav_collect:
                isReplace = true;
                fragmentTag = MyCollectFragment.class.getSimpleName();
                destFragment = mSupportFragmentManager.findFragmentByTag(fragmentTag);
                if (destFragment == null) destFragment = new MyCollectFragment();
                break;
            case R.id.nav_history:
                isReplace = true;
                fragmentTag = HistoryRecordFragment.class.getSimpleName();
                destFragment = mSupportFragmentManager.findFragmentByTag(fragmentTag);
                if (destFragment == null) destFragment = new HistoryRecordFragment();
                break;
            case R.id.nav_friends:
                isReplace = true;
                fragmentTag = MyConcernFragment.class.getSimpleName();
                destFragment = mSupportFragmentManager.findFragmentByTag(fragmentTag);
                if (destFragment == null) destFragment = new MyConcernFragment();
                break;
            case R.id.nav_purse:
                isReplace = true;
                fragmentTag = MyPurseFragment.class.getSimpleName();
                destFragment = mSupportFragmentManager.findFragmentByTag(fragmentTag);
                if (destFragment == null) destFragment = new MyPurseFragment();
                break;
            case R.id.nav_theme_choose:
                isReplace = true;
                fragmentTag = ThemeSelectFragment.class.getSimpleName();
                destFragment = mSupportFragmentManager.findFragmentByTag(fragmentTag);
                if (destFragment == null) destFragment = new ThemeSelectFragment();
                break;
            case R.id.nav_recommend:
                break;
            case R.id.nav_settings:
                break;
        }
        if (!TextUtils.isEmpty(fragmentTag) && destFragment != null) {
            mHomeModel.setCurrentFragmentName(fragmentTag);
            mHomeModel.setCurrentMenuItemId(item.getItemId());
            if (isReplace) {
                FragmentTransaction transaction = mSupportFragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_enter, 0, 0, R.anim.fragment_pop_exit);
                transaction.replace(R.id.home_fragment_container, destFragment, fragmentTag).commit();
                mHomeView.refreshFragment(true);
            } else {
                FragmentTransaction transaction = mSupportFragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_enter, 0, 0, R.anim.fragment_pop_exit);
                transaction.replace(R.id.home_fragment_container, destFragment, fragmentTag).addToBackStack(null).commit();
                mHomeView.refreshFragment(false);
            }
        }
    }


    /**
     * 判断当前Fragment是否是HomeFragment
     *
     * @return boolean
     */
    public boolean checkCurrentView() {
        return mHomeModel.getCurrentFragmentName().equals(HomeFragment.class.getSimpleName());
    }


    /**
     * 获取菜单的ID
     *
     * @return menuId
     */
    public int getCurrentMenuId() {
        return mHomeModel.getCurrentMenuItemId();
    }

    /**
     * 日夜间模式切换
     *
     * @param context 文本
     * @param builder Dialog.Builder
     */
    public void switchDayNightMode(Context context, AlertDialog.Builder builder) {
        final boolean isNightMode = preferences.getBoolean(AppConstant.APP_IS_NIGHT_MODE, false);
        builder.setTitle(isNightMode ? context.getString(R.string.switch_to_day_mode) : context.getString(R.string.switch_to_night_mode))
                .setMessage(R.string.tip_restart_app)
                .setView(null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        preferences.putBoolean(AppConstant.APP_IS_NIGHT_MODE, !isNightMode).apply();
                        mHomeView.switchDayNightMode();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    /**
     * 刷新用户信息
     */
    public void refreshUserProfile() {
        mHomeView.refreshUserProfile(GlobalVar.getInstance().getUserEntity());
    }

    /**
     * 搜索点击事件
     */
    public void onClickSearch() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mHomeView.highSearchSkip();
        } else {
            mHomeView.lowSearchSkip();
        }
    }

    /**
     * 头像点击事件
     */
    private void onClickAvatar() {

    }

}
