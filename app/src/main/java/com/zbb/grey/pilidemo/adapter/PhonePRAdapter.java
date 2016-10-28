package com.zbb.grey.pilidemo.adapter;

import android.content.Context;
import android.widget.CheckBox;

import com.jstudio.adapter.list.CommonAdapter;
import com.jstudio.adapter.list.ViewHolder;
import com.zbb.grey.pilidemo.R;
import com.zbb.grey.pilidemo.ui.bean.PhonePR;

import java.util.List;

/**
 * 国家or地区手机号码前缀
 * Created by jumook on 2016/10/28.
 */
public class PhonePRAdapter extends CommonAdapter<PhonePR> {


    public PhonePRAdapter(Context context, List<PhonePR> data) {
        super(context, data);
    }

    @Override
    public void inflateContent(ViewHolder holder, int position, PhonePR phonePR) {
        //地区名字
        holder.setTextByString(R.id.item_name, phonePR.name);
        //设置选择状态
        CheckBox checkBox = holder.getView(R.id.item_checkbox);
        if (phonePR.isTrue) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
    }

    @Override
    public int setItemLayout(int type) {
        return R.layout.item_lv_phone_pr;
    }

}
