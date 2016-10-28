package com.zbb.grey.pilidemo.ui.view.register;

import android.app.Dialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jstudio.widget.dialog.DialogCreator;
import com.zbb.grey.pilidemo.R;
import com.zbb.grey.pilidemo.adapter.PhonePRAdapter;
import com.zbb.grey.pilidemo.base.AppBaseActivity;
import com.zbb.grey.pilidemo.ui.bean.PhonePR;
import com.zbb.grey.pilidemo.ui.presenter.RegisterPresenter;
import com.zbb.grey.pilidemo.ui.widge.BrightTextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

import static com.zbb.grey.pilidemo.R.id.toolbar;

/**
 * 注册手机号码
 * Created by jumook on 2016/10/28.
 */

public class RegisterActivity extends AppBaseActivity implements RegisterViewPort, View.OnClickListener {

    public static final String TAG = "RegisterActivity";
    @Bind(toolbar)
    Toolbar mToolbar;
    @Bind(R.id.hint_view)
    TextView mHintView;
    @Bind(R.id.area_name)
    TextView mAreaName;
    @Bind(R.id.area_code)
    TextView mAreaCode;
    @Bind(R.id.user_name)
    EditText mUsesPhone;
    @Bind(R.id.get_code)
    Button mGetCodeBtn;
    @Bind(R.id.item_agreement)
    BrightTextView mAgreementView;

    private Dialog mSingleDialog;
    private TextView mSingleTitle;
    private ListView mSingleListView;
    private TextView mSingleCancel;

    private RegisterPresenter registerPresenter;
    private PhonePRAdapter mPhoneAdapter;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void findViews() {
        View singleView = LayoutInflater.from(this).inflate(R.layout.dialog_single_selector, null);
        mSingleTitle = (TextView) singleView.findViewById(R.id.single_title);
        mSingleListView = (ListView) singleView.findViewById(R.id.single_list);
        mSingleCancel = (TextView) singleView.findViewById(R.id.single_cancel);
        mSingleTitle.setText("地区选择");
        mSingleDialog = DialogCreator.createNormalDialog(this, singleView, DialogCreator.Position.CENTER);
    }

    @Override
    protected void initialization() {
        registerPresenter = new RegisterPresenter(this);
        mToolbar.setTitle(getString(R.string.register_toolbar_name));
        mToolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        setSupportActionBar(mToolbar);
        mAgreementView.setBrightTextsColor(getString(R.string.register_agreement), "注册协议", getResources().getColor(R.color.theme_color));

        mPhoneAdapter = new PhonePRAdapter(this, new ArrayList<PhonePR>());
        mSingleListView.setAdapter(mPhoneAdapter);
    }

    @Override
    protected void bindEvent() {
        mSingleCancel.setOnClickListener(this);
        mSingleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                registerPresenter.setCurrentPhonePR(position);
                mSingleDialog.dismiss();
            }
        });
    }


    @OnClick({R.id.area_name, R.id.area_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.area_name:
                mPhoneAdapter.setData(registerPresenter.getPhonePRList());
                mSingleDialog.show();
                break;
            case R.id.area_code:
                break;
            case R.id.single_cancel:
                mSingleDialog.dismiss();
                break;
        }
    }

    @Override
    public void refreshView(String areaName, String PR) {
        mAreaName.setText(areaName);
        mAreaCode.setText(PR);
    }
}
