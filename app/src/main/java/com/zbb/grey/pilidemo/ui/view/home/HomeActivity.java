package com.zbb.grey.pilidemo.ui.view.home;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jstudio.utils.ExitAppUtils;
import com.jstudio.utils.IntentHelper;
import com.jstudio.utils.JLog;
import com.zbb.grey.pilidemo.R;
import com.zbb.grey.pilidemo.base.AppBaseActivity;
import com.zbb.grey.pilidemo.ui.bean.UserEntity;
import com.zbb.grey.pilidemo.ui.presenter.HomePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;

import static com.zbb.grey.pilidemo.R.id.toolbar;

/**
 * Created by jumook on 2016/10/25.
 */

public class HomeActivity extends AppBaseActivity implements NavigationView.OnNavigationItemSelectedListener, IHomeView {

    public static final String TAG = "HomeActivity";

    @Bind(toolbar)
    Toolbar mToolbar;
    @Bind(R.id.home_nav_view)
    NavigationView mNavView;
    @Bind(R.id.home_drawer_layout)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mToggle;

    private SimpleDraweeView mAccountAvatar;
    private ImageView mMessage;
    private ImageView mSwitchPattern;
    private TextView mUserName;
    private TextView mUserLimit;
    private TextView mUserVip;
    private TextView mUserCoin;

    private HomePresenter homePresenter;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void findViews() {
        View navHeaderView = mNavView.getHeaderView(0);
        mAccountAvatar = (SimpleDraweeView) navHeaderView.findViewById(R.id.nav_header_avatar);
        mMessage = (ImageView) navHeaderView.findViewById(R.id.nav_message);
        mSwitchPattern = (ImageView) navHeaderView.findViewById(R.id.nav_switch_pattern);
        mUserName = (TextView) navHeaderView.findViewById(R.id.nav_header_name);
        mUserLimit = (TextView) navHeaderView.findViewById(R.id.nav_vip_limit);
        mUserVip = (TextView) navHeaderView.findViewById(R.id.nav_header_vip);
        mUserCoin = (TextView) navHeaderView.findViewById(R.id.nav_coin);
    }

    @Override
    protected void initialization() {
        homePresenter = new HomePresenter(this, this);

        MenuItem localLibraryMenu = mNavView.getMenu().findItem(R.id.nat_home);
        onNavigationItemSelected(localLibraryMenu);
        localLibraryMenu.setChecked(true);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.home_drawer_open, R.string.home_drawer_close);
        mToolbar.setLogo(R.drawable.ic_defualt_avatar_1);
        mToolbar.setTitle("  格雷D奈尔");
        setSupportActionBar(mToolbar);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void doMoreInOnCreate() {
        homePresenter.setIsFirstLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSupportFragmentManager.addOnBackStackChangedListener(mBackStackChangedListener);
        homePresenter.refreshUserProfile();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSupportFragmentManager.removeOnBackStackChangedListener(mBackStackChangedListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceive(String s) {
        JLog.w("EventBus test", s);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        if (mSupportFragmentManager.getBackStackEntryCount() > 0) {
            mSupportFragmentManager.popBackStack();
        } else if (false) {
            IntentHelper.showLauncher(this);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:

                return true;
            case R.id.action_search:
                homePresenter.onClickSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        mDrawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                homePresenter.navigationItemSelected(item);
            }
        }, 300);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private final FragmentManager.OnBackStackChangedListener mBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            if (mToggle == null) {
                return;
            }
            boolean isRoot = mSupportFragmentManager.getBackStackEntryCount() == 0;
            mToggle.setDrawerIndicatorEnabled(isRoot);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowHomeEnabled(!isRoot);
                getSupportActionBar().setDisplayHomeAsUpEnabled(!isRoot);
                getSupportActionBar().setHomeButtonEnabled(!isRoot);
            }
            if (isRoot) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                mToggle.syncState();
            }
        }
    };

    @Override
    public void switchDayNightMode() {
        Intent intent = IntentHelper.getRebootAppIntent(HomeActivity.this);
        ExitAppUtils.getInstance().terminateAllActivity();
        startActivity(intent);
    }

    @Override
    public void refreshUserProfile(UserEntity userEntity) {

    }

    @Override
    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = mSupportFragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_enter, 0, 0, R.anim.fragment_pop_exit);
        transaction.replace(R.id.home_fragment_container, fragment, tag).commit();
    }

    @Override
    public void addFragment(Fragment fragment, String tag) {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        FragmentTransaction transaction = mSupportFragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_enter, 0, 0, R.anim.fragment_pop_exit);
        transaction.replace(R.id.home_fragment_container, fragment, tag).addToBackStack(null).commit();
    }

    @Override
    public void highSearchSkip() {
//        Intent toSearch = new Intent(this, SearchActivity.class);
//        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
//        startActivity(toSearch, transitionActivityOptions.toBundle());
    }

    @Override
    public void lowSearchSkip() {
//        startActivity(new Intent(this, SearchActivity.class));
    }


}
