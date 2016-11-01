package com.zbb.grey.pilidemo.ui.view.register;

import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zbb.grey.pilidemo.R;
import com.zbb.grey.pilidemo.base.AppBaseActivity;
import com.zbb.grey.pilidemo.ui.presenter.ProofCodePresenter;

import butterknife.Bind;
import butterknife.OnClick;
import tools.ActivityTaskManager;

/**
 * 校对验证码界面
 * Created by jumook on 2016/10/31.
 */
public class ProofCodeActivity extends AppBaseActivity implements ProofCodeViewPort {

    public static final String TAG = "ProofCodeActivity";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.item_phone)
    TextView mPhoneView;
    @Bind(R.id.item_code)
    EditText mCodeInput;
    @Bind(R.id.item_count_down)
    TextView mTimeCountDown;
    @Bind(R.id.item_next)
    Button mNextBtn;

    private ProofCodePresenter proofCodePresenter;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_proof_code);
        ActivityTaskManager.getInstance().putActivity(TAG, this);
    }

    @Override
    protected void initialization() {
        proofCodePresenter = new ProofCodePresenter(this);
        mToolbar.setTitle(getString(R.string.proof_code_toolbar_name));
        mToolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        setSupportActionBar(mToolbar);

        //初始化界面
        proofCodePresenter.initData(getIntent().getExtras());
    }

    @Override
    protected void bindEvent() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mCodeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    mNextBtn.setEnabled(true);
                } else {
                    mNextBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.item_count_down, R.id.item_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_count_down:
                proofCodePresenter.resendCode();
                break;
            case R.id.item_next:
                showProgressDialog(getString(R.string.opt_doing), false);
                proofCodePresenter.verifyCode(mCodeInput.getText().toString());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        ActivityTaskManager.getInstance().removeActivity(TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void initView(boolean isTrue, String phoneNumber) {
        if (isTrue) {
            mPhoneView.setText(phoneNumber);
        } else {
            showToast(getString(R.string.skip_error));
            finish();
        }
    }

    @Override
    public void refreshTime(boolean isTrue, String time) {
        mTimeCountDown.setText(time);
        mTimeCountDown.setEnabled(isTrue);
        mTimeCountDown.setClickable(isTrue);
    }

    @Override
    public void nextOperation(boolean isTrue, String message) {
        dismissProgressDialog();
        if (isTrue) {
            proofCodePresenter.shopDownTimer();
            openActivityWithBundle(SetPasswordActivity.class, null);
        } else {
            showToast(message);
        }
    }

}
