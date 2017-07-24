package com.tck.erpmanager.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by tck on 2017/6/24.
 */

public class MyCustomTabEntity implements CustomTabEntity {

    private String title;
    private int selectedIcon;
    private int unSelectedIcon;

    /**
     * 通过构造方法接受参数
     *
     * @return
     */
    public MyCustomTabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
