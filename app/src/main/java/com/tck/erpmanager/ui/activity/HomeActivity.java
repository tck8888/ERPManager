package com.tck.erpmanager.ui.activity;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.MyCustomTabEntity;
import com.tck.erpmanager.ui.fragment.home_fragment.BasicFragment;
import com.tck.erpmanager.ui.fragment.home_fragment.HomeFragment;

import java.util.ArrayList;

/**
 * 主界面
 * Created by tck on 2017/6/24.
 */
public class HomeActivity extends BaseActivity {

    private CommonTabLayout mCommonTabLayout;

    private ArrayList<CustomTabEntity> tabEntitys;
    private ArrayList<Fragment> fragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {

        String[] titles = getResources().getStringArray(R.array.home_tag);
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new BasicFragment());
        tabEntitys = new ArrayList<>();

        for (int i = 0; i < fragments.size(); i++) {
            switch (i) {
                case 0:
                    tabEntitys.add(new MyCustomTabEntity(titles[i], R.mipmap.main_nav_click_home_2_x, R.mipmap.icon_home_page));
                    break;
                case 1:
                    tabEntitys.add(new MyCustomTabEntity(titles[i], R.mipmap.main_nav_click_basics_2_x, R.mipmap.icon_basics));
                    break;
            }
        }
    }

    @Override
    protected void initView() {

        mCommonTabLayout = (CommonTabLayout) findViewById(R.id.common_tab_layout);
        mCommonTabLayout.setTabData(tabEntitys, this, R.id.frame_container, fragments);
    }
}
