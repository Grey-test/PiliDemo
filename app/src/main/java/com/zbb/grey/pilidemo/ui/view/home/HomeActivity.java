package com.zbb.grey.pilidemo.ui.view.home;

import android.content.Intent;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
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
import butterknife.OnClick;

import static com.zbb.grey.pilidemo.R.id.toolbar;

/**
 * Created by jumook on 2016/10/25.
 */

public class HomeActivity extends AppBaseActivity implements NavigationView.OnNavigationItemSelectedListener, HomeViewPort, View.OnClickListener {

    public static final String TAG = "HomeActivity";

    @Bind(toolbar)
    Toolbar mToolbar;
    @Bind(R.id.home_nav_view)
    NavigationView mNavView;
    @Bind(R.id.home_drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.toolbar_left_btn)
    ImageView toolbarLeftBtn;
    @Bind(R.id.toolbar_avatar)
    SimpleDraweeView toolbarAvatar;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;

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
        //移除滚动条
        NavigationMenuView navigationMenuView = (NavigationMenuView) mNavView.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
        //设置菜单默认选中
        MenuItem localLibraryMenu = mNavView.getMenu().findItem(R.id.nav_home);
        onNavigationItemSelected(localLibraryMenu);
        localLibraryMenu.setChecked(true);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.home_drawer_open, R.string.home_drawer_close);
        mToolbar.setNavigationIcon(R.drawable.ic_drawer_home);
        setSupportActionBar(mToolbar);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void bindEvent() {
        mAccountAvatar.setOnClickListener(this);
        mMessage.setOnClickListener(this);
        mSwitchPattern.setOnClickListener(this);

        mDrawerLayout.addDrawerListener(mToggle);
//        updateDrawerToggle();
        mNavView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void doMoreInOnCreate() {
        //根据本地数据初始化界面
        homePresenter.initViewWithNative();
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

    @OnClick({R.id.toolbar_left_btn, R.id.toolbar_avatar, R.id.toolbar_title})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_header_avatar:
                break;
            case R.id.nav_message:
                break;
            case R.id.nav_switch_pattern:
                homePresenter.switchDayNightMode(HomeActivity.this, getDialogBuilder());
                break;
            case R.id.toolbar_left_btn:
            case R.id.toolbar_avatar:
            case R.id.toolbar_title:
                mDrawerLayout.openDrawer(mNavView);
                break;
        }
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
        if (!homePresenter.checkCurrentView()) {
            mNavView.getMenu().getItem(0).setChecked(true);
            homePresenter.navigationItemSelected(mNavView.getMenu().getItem(0), mSupportFragmentManager);
            return;
        }
        if (mSupportFragmentManager.getBackStackEntryCount() > 0) {
            mSupportFragmentManager.popBackStack();
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
            case R.id.action_download:

                return true;
            case R.id.action_search:
                homePresenter.onClickSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        homePresenter.refreshToolbarMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        mDrawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                homePresenter.navigationItemSelected(item, mSupportFragmentManager);
            }
        }, 300);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void initViewWithNative(boolean isNightMode) {
        if (isNightMode) {
            mSwitchPattern.setImageResource(R.drawable.ic_switch_night);
        } else {
            mSwitchPattern.setImageResource(R.drawable.ic_switch_daily);
        }
        //初始化默认为HomeFragment
        mNavView.getMenu().getItem(0).setChecked(true);
        homePresenter.navigationItemSelected(mNavView.getMenu().getItem(0), mSupportFragmentManager);
    }

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
    public void refreshFragment(boolean isReplace) {
        if (!isReplace) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        invalidateOptionsMenu();
    }

    @Override
    public void refreshToolbar(int id) {
        switch (id) {
            case R.id.nav_home:
                mToolbar.setTitle("");
                break;
            case R.id.nav_collect:
                mToolbar.setTitle("我的收藏");
                break;
            case R.id.nav_friends:
                mToolbar.setTitle("关注的人");
                break;
            case R.id.nav_purse:
                mToolbar.setTitle("我的钱包");
                break;
            case R.id.nav_theme_choose:
                mToolbar.setTitle("主题选择");
                break;
            case R.id.nav_history:
                mToolbar.setTitle("历史记录");
                break;
        }
        if (id == R.id.nav_home) {
            mToolbar.setNavigationIcon(null);
            toolbarLeftBtn.setVisibility(View.VISIBLE);
            toolbarAvatar.setVisibility(View.VISIBLE);
            toolbarTitle.setVisibility(View.VISIBLE);
        } else {
            mToolbar.setNavigationIcon(R.drawable.ic_navigation_drawer);
            toolbarLeftBtn.setVisibility(View.GONE);
            toolbarAvatar.setVisibility(View.GONE);
            toolbarTitle.setVisibility(View.GONE);
        }
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

    private final FragmentManager.OnBackStackChangedListener mBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            updateDrawerToggle();
        }
    };

    //更新DrawerToggle控件
    private void updateDrawerToggle() {
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

}
