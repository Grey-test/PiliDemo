package com.zbb.grey.pilidemo.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by jumook on 2016/10/28.
 */

public class LoginModel implements LoginModelPort {

    private String userName;
    private String password;
    private List<String> userNameList;


    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List<String> getUserNameList(Set<String> list) {
        if (list != null) {
            this.userNameList = new ArrayList<>(list);
            return this.userNameList;
        }
        return new ArrayList<>();
    }

    @Override
    public void putUserNamInList() {
        if (!this.userNameList.contains(this.userName)) {
            this.userNameList.add(this.userName);
        }
    }


}
