package com.zbb.grey.pilidemo.ui.view.other;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zbb.grey.pilidemo.R;
import com.zbb.grey.pilidemo.base.AppBaseActivity;
import com.zbb.grey.pilidemo.ui.view.home.HomeActivity;
import com.zbb.grey.pilidemo.ui.widge.TintEditText;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 登录界面
 * Created by jumook on 2016/10/27.
 */

public class LoginActivity extends AppBaseActivity implements TintEditText.OnFocusChangeListener {

    public static final String TAG = "LoginActivity";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.image_left)
    ImageView imageLeft;
    @Bind(R.id.image_right)
    ImageView imageRight;
    @Bind(R.id.login_user)
    TintEditText loginUser;
    @Bind(R.id.login_password)
    TintEditText loginPassword;
    @Bind(R.id.login_account)
    Button loginAccount;
    @Bind(R.id.login_in)
    Button loginIn;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initialization() {
        toolbar.setTitle(getString(R.string.login_toolbar_title));
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void bindEvent() {
        loginUser.setOnFocusChangeListener(this);
        loginPassword.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.login_user:
                imageLeft.setImageResource(R.drawable.ic_22);
                imageRight.setImageResource(R.drawable.ic_33);
                break;
            case R.id.login_password:
                imageLeft.setImageResource(R.drawable.ic_22_hide);
                imageRight.setImageResource(R.drawable.ic_33_hide);
                break;
        }
    }

    @OnClick({R.id.login_account, R.id.login_in})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_account:
                openActivityWithBundle(HomeActivity.class, null);
                break;
            case R.id.login_in:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_forget:

                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
