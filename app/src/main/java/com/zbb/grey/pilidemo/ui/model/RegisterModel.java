package com.zbb.grey.pilidemo.ui.model;

import com.zbb.grey.pilidemo.ui.bean.PhonePR;

import java.util.List;

/**
 * Created by jumook on 2016/10/28.
 */

public class RegisterModel implements RegisterModelPort {

    private String phone;
    private String code;
    private PhonePR currentPhonePR;
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
    public String getCode() {
        return this.code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public PhonePR getPhonePR() {
        return currentPhonePR;
    }

    @Override
    public void setPhonePR(PhonePR phonePR) {
        this.currentPhonePR = phonePR;
    }

    @Override
    public List<PhonePR> getPhonePRList() {
        if (this.list == null) {
            this.list = PhonePR.getPRList();
            this.currentPhonePR = this.list.get(0) == null ? null : this.list.get(0);
        }
        return this.list;
    }
}
