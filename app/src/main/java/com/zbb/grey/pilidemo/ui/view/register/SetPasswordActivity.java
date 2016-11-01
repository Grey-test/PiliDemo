package com.zbb.grey.pilidemo.ui.view.register;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zbb.grey.pilidemo.R;
import com.zbb.grey.pilidemo.base.AppBaseActivity;
import com.zbb.grey.pilidemo.ui.presenter.SetPasswordPresenter;
import com.zbb.grey.pilidemo.ui.view.home.HomeActivity;

import butterknife.Bind;
import butterknife.OnClick;
import tools.ActivityTaskManager;

/**
 * 设置密码、并完善资料
 * Created by jumook on 2016/11/1.
 */

public class SetPasswordActivity extends AppBaseActivity implements SetPasswordViewPort {

    public static final String TAG = "SetPasswordActivity";
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.hint_view)
    TextView mHintView;
    @Bind(R.id.first_password)
    EditText mFirstPassword;
    @Bind(R.id.second_password)
    EditText mSecondPassword;
    @Bind(R.id.user_name)
    EditText mUserName;
    @Bind(R.id.item_complete)
    Button mCompleteBtn;

    private SetPasswordPresenter setPasswordPresenter;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_set_password);
        ActivityTaskManager.getInstance().putActivity(TAG, this);
    }

    @Override
    protected void initialization() {
        setPasswordPresenter = new SetPasswordPresenter(this, this);

        mToolbar.setTitle(getString(R.string.set_password_toolbar_name));
        mToolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void bindEvent() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick(R.id.item_complete)
    public void onClick() {
        mHintView.setText(getString(R.string.set_password_tip));
        mHintView.setTextColor(getResources().getColor(R.color.black_tf_3d));
        showProgressDialog(getString(R.string.opt_doing), false);
        setPasswordPresenter.checkInfo(mFirstPassword.getText().toString(), mSecondPassword.getText().toString(), mUserName.getText().toString());
    }

    @Override
    public void onBackPressed() {
        ActivityTaskManager.getInstance().removeActivity(TAG);
    }


    @Override
    public void upLoadInfo(boolean isTrue, String message) {
        dismissProgressDialog();
        if (isTrue) {
            openActivityWithBundle(HomeActivity.class, null);
            ActivityTaskManager.getInstance().removeActivity(LoginActivity.TAG);
            ActivityTaskManager.getInstance().removeActivity(RegisterActivity.TAG);
            ActivityTaskManager.getInstance().removeActivity(ProofCodeActivity.TAG);
            ActivityTaskManager.getInstance().removeActivity(SetPasswordActivity.TAG);
            ActivityTaskManager.getInstance().removeActivity(TAG);
        } else {
            mHintView.setText(message);
            mHintView.setTextColor(getResources().getColor(R.color.hint_tip_red));
        }
    }
}
