package com.jstudio.adapter.list;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 使用方法见CommonAdapter
 * <p/>
 * Created by Jason
 */
@SuppressWarnings("unused")
public abstract class CommonCursorAdapter extends CursorAdapter {

    public static String TAG = null;

    protected LayoutInflater mInflater;

    /**
     * 构造方法
     *
     * @param context Context
     * @param c       Cursor
     * @param flags   使用{@link #FLAG_AUTO_REQUERY} 和 {@link #FLAG_REGISTER_CONTENT_OBSERVER} 任意组合
     */
    public CommonCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        TAG = this.getClass().getSimpleName();
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * 填充Item内容的方法，如设置TextView的text
     *
     * @param holder   返回可供用户调用的holder
     * @param position 当前的位置
     * @param cursor   已经将游标移动到position位置的Cursor
     */
    protected abstract void inflateContent(ViewHolder holder, int position, Cursor cursor);

    protected abstract int setItemLayout(int type);

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        if (!mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("couldn't move cursor to position " + position);
        }
        int layout = 0;
        if (getViewTypeCount() == 1) {
            layout = setItemLayout(0);
        } else {
            for (int i = 0; i < getViewTypeCount(); i++) {
                // 通过item种类数量遍历类型，如果跟当前position类型吻合
                // 则调用setItemLayout，找到跳出循环
                if (getItemViewType(position) == i) {
                    layout = setItemLayout(i);
                    break;
                }
            }
        }
        ViewHolder holder = ViewHolder.getInstance(mContext, convertView, parent, layout, position);
        mCursor.moveToPosition(position);
        inflateContent(holder, position, mCursor);
        return holder.getConvertView();
    }

}
