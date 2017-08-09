package com.tck.commonlibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.tck.commonlibrary.common.CommonConstant;
import com.tck.commonlibrary.widget.LoadingDialog;

/**
 * Created by tck on 2017/5/26.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mLoadingDialog = new LoadingDialog(this);
        initData();
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initView();

    public void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CommonConstant.IS_DEBUG){
            System.out.println("=======" + getClass().getSimpleName()+"=======");
        }
    }

    @Override
    public void showLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void dimissloading() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    /**
     * 隐藏软键盘
     */
    protected void hideSoftKeyboard() {
        if (!this.isDestroyed()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }

}
