package com.zbb.grey.pilidemo.ui.model;

/**
 * Created by jumook on 2016/10/31.
 */

public class ProofCodeCodeModel implements ProofCodeModelPort {

    private String phone;
    private String phonePRName;

    @Override
    public String getPhone() {
        return this.phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getPhonePRName() {
        return this.phonePRName;
    }


    @Override
    public void setPhonePRName(String phonePRName) {
        this.phonePRName = phonePRName;
    }
}
