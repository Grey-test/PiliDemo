package com.zbb.grey.pilidemo.ui.model;

/**
 * Created by jumook on 2016/10/26.
 */

public class HomeModel implements HomeModelPort {

    private String currentFragmentName;
    private int currentMenuItemId;

    @Override
    public String getCurrentFragmentName() {
        return currentFragmentName;
    }

    @Override
    public void setCurrentFragmentName(String name) {
        this.currentFragmentName = name;
    }

    @Override
    public int getCurrentMenuItemId() {
        return currentMenuItemId;
    }

    @Override
    public void setCurrentMenuItemId(int currentMenuItemId) {
        this.currentMenuItemId = currentMenuItemId;
    }


}
