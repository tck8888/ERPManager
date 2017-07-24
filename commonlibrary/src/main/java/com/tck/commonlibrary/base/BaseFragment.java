package com.tck.commonlibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tck on 2017/6/24.
 */

public abstract class BaseFragment extends Fragment {


    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initData();
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), container, false);
        }
        initView(mView);
        return mView;
    }


    protected abstract int getLayoutId();

    protected abstract void initData();
    protected abstract void initView(View view);


}
