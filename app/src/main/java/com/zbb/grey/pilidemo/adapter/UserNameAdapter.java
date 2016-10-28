package com.zbb.grey.pilidemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.jstudio.adapter.list.CommonAdapter;
import com.jstudio.adapter.list.ViewHolder;
import com.zbb.grey.pilidemo.R;

import java.util.List;

/**
 * 用户帐号提示
 * Created by jumook on 2016/10/28.
 */

public class UserNameAdapter extends CommonAdapter<String> {

    public UserNameAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    public void inflateContent(ViewHolder holder, int position, String s) {
        holder.setTextByString(R.id.user_name, s);
        ImageView deleteView = holder.getView(R.id.icon_clear);
        deleteView.setOnClickListener(new OnDeleteListener(position));
    }

    @Override
    public int setItemLayout(int type) {
        return R.layout.item_lv_user_name;
    }

    /**
     * 删除当前行的用户帐号
     */
    private class OnDeleteListener implements View.OnClickListener {

        private int position;

        OnDeleteListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            mData.remove(position);
            notifyDataSetChanged();
        }
    }
}
