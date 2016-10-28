package com.zbb.grey.pilidemo.ui.view.register;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zbb.grey.pilidemo.R;
import com.zbb.grey.pilidemo.adapter.UserNameAdapter;
import com.zbb.grey.pilidemo.base.AppBaseActivity;
import com.zbb.grey.pilidemo.bridge.OnTextChangeListener;
import com.zbb.grey.pilidemo.ui.presenter.LoginPresenter;
import com.zbb.grey.pilidemo.ui.widge.TintAutoCompleteText;
import com.zbb.grey.pilidemo.ui.widge.TintEditText;

import butterknife.Bind;
import butterknife.OnClick;

import static com.zbb.grey.pilidemo.R.color.theme_color;
import static com.zbb.grey.pilidemo.R.color.under_grey_line;


/**
 * 登录界面
 * Created by jumook on 2016/10/27.
 */

public class LoginActivity extends AppBaseActivity implements LoginViewPort, TintEditText.OnFocusChangeListener {

    public static final String TAG = "LoginActivity";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.image_left)
    ImageView imageLeft;
    @Bind(R.id.image_right)
    ImageView imageRight;
    @Bind(R.id.login_user)
    TintAutoCompleteText loginUser;
    @Bind(R.id.login_user_line)
    View userLine;
    @Bind(R.id.login_password)
    TintEditText loginPassword;
    @Bind(R.id.login_password_line)
    View passwordLine;
    @Bind(R.id.login_account)
    Button loginAccount;
    @Bind(R.id.login_in)
    Button loginIn;

    private LoginPresenter loginPresenter;
    private UserNameAdapter mUserAdapter;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initialization() {
        loginPresenter = new LoginPresenter(this, this);

        toolbar.setTitle(getString(R.string.login_toolbar_title));
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        setSupportActionBar(toolbar);

        mUserAdapter = new UserNameAdapter(this, loginPresenter.getUserNameList());
        loginUser.setAdapter(mUserAdapter);
    }

    @Override
    protected void bindEvent() {
        loginUser.setOnFocusChangeListener(this);
        loginPassword.setOnFocusChangeListener(this);

        loginUser.setOnTextChangeListener(new OnTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s) {
                loginPresenter.checkLoginInState(true, s, "");
            }
        });

        loginPassword.setOnTextChangeListener(new OnTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s) {
                loginPresenter.checkLoginInState(false, "", s);
            }
        });

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.login_user:
                imageLeft.setImageResource(R.drawable.ic_22);
                imageRight.setImageResource(R.drawable.ic_33);
                userLine.setBackgroundResource(theme_color);
                passwordLine.setBackgroundResource(under_grey_line);
                break;
            case R.id.login_password:
                imageLeft.setImageResource(R.drawable.ic_22_hide);
                imageRight.setImageResource(R.drawable.ic_33_hide);
                userLine.setBackgroundResource(under_grey_line);
                passwordLine.setBackgroundResource(theme_color);
                break;
        }
    }

    @OnClick({R.id.login_account, R.id.login_in})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_account:

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


    @Override
    public void setLoginInStatue(boolean isTrue) {
        if (isTrue) {
            loginIn.setEnabled(true);
        } else {
            loginIn.setEnabled(false);
        }
    }
}
