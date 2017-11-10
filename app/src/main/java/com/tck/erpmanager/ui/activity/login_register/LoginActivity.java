package com.tck.erpmanager.ui.activity.login_register;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.commonlibrary.common.CommonConstant;
import com.tck.commonlibrary.utils.AppSharePreferenceMgr;
import com.tck.commonlibrary.utils.PhoneFormatCheckUtils;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.bean.User;
import com.tck.erpmanager.net.contract.LoginAndRegisterContract;
import com.tck.erpmanager.net.presenter.LoginPresenterImpl;
import com.tck.erpmanager.ui.activity.HomeActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 登录界面
 * <p>
 * Created by tck on 2017/5/27.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginAndRegisterContract.LoginView {

    private EditText mPhoneTv;
    private EditText mPwdTv;

    private LoginPresenterImpl mLoginPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

        mLoginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void initView() {
        mPhoneTv = (EditText) findViewById(R.id.phone_tv);
        mPwdTv = (EditText) findViewById(R.id.pwd_tv);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.to_register).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent<String> event) {
        if (event.getTag().equals("UserBean")) {
            mPhoneTv.setText(event.getData());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                userLogin();
                break;
            case R.id.to_register:
                toRegister();
                break;
        }
    }


    /**
     * 登录
     */
    private void userLogin() {
        String phone = mPhoneTv.getText().toString().trim();
        String pwd = mPwdTv.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showToast(getString(R.string.login_phone_string));
            return;
        }
        if (!PhoneFormatCheckUtils.isChinaPhoneLegal(phone)) {
            showToast(getString(R.string.phone_format_string));
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showToast(getString(R.string.login_pwd_string));
            return;
        }
        mLoginPresenter.login(phone, pwd);
    }

    /**
     * 前往注册
     */
    private void toRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void showData(User user) {
        if (user != null) {
            showToast(user.getMessgae());
            if (user.getStatus() == 200) {
                if (user.getData() != null) {
                    AppSharePreferenceMgr.put(this, CommonConstant.KEY_USER_ID, user.getData().getId());
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }
}
