package com.zbb.grey.pilidemo.ui.model;

import com.zbb.grey.pilidemo.ui.bean.PhonePR;

import java.util.List;

/**
 * Created by jumook on 2016/10/28.
 */

public interface RegisterModelPort {

    String getPhone();

    void setPhone(String phone);

    String getCode();

    void setCode(String code);

    PhonePR getPhonePR();

    void setPhonePR(PhonePR phonePR);

    List<PhonePR> getPhonePRList();

}
