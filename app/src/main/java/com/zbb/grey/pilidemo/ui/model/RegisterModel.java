package com.zbb.grey.pilidemo.ui.model;

import com.zbb.grey.pilidemo.ui.bean.PhonePR;

import java.util.List;

/**
 * Created by jumook on 2016/10/28.
 */

public class RegisterModel implements RegisterModelPort {

    private String phone;
    private List<PhonePR> list;

    @Override
    public String getPhone() {
        return this.phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public List<PhonePR> getPhonePRList() {
        if (this.list == null) {
            this.list = PhonePR.getPRList();
        }
        return this.list;
    }
}
