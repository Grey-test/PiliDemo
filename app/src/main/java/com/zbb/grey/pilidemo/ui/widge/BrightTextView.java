package com.zbb.grey.pilidemo.ui.widge;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 局部文字变色
 * Created by jumook on 2016/10/28.
 */

public class BrightTextView extends TextView {

    public BrightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BrightTextView(Context context) {
        super(context);
    }

    public void setBrightTextsColor(String text, String specifiedTexts, int color) {
        List<Integer> sTextsStartList = new ArrayList<>();

        if (specifiedTexts != null && specifiedTexts.length() != 0) {
            int sTextLength = specifiedTexts.length();
            String temp = text;
            int lengthFront = 0;//记录被找出后前面的字段的长度
            int start = -1;
            do {
                start = temp.indexOf(specifiedTexts);
                if (start != -1) {
                    start = start + lengthFront;
                    sTextsStartList.add(start);
                    lengthFront = start + sTextLength;
                    temp = text.substring(lengthFront);
                }
            } while (start != -1);
            SpannableStringBuilder styledText = new SpannableStringBuilder(text);
            for (Integer i : sTextsStartList) {
                styledText.setSpan(new ForegroundColorSpan(color), i, i + sTextLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            setText(styledText);
        }
    }

}
