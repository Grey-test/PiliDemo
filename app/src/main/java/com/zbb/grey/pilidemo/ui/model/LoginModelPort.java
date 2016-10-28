package com.zbb.grey.pilidemo.ui.model;

import java.util.List;
import java.util.Set;

/**
 * Created by jumook on 2016/10/28.
 */

public interface LoginModelPort {

    String getUserName();

    void setUserName(String userName);

    String getPassword();

    void setPassword(String password);

    List<String> getUserNameList(Set<String> list);

    void putUserNamInList();

}
