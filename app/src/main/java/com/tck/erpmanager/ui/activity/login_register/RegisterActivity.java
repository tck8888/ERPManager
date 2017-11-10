package com.tck.erpmanager.ui.activity.login_register;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.commonlibrary.utils.PhoneFormatCheckUtils;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.InfoBean;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.net.contract.LoginAndRegisterContract;
import com.tck.erpmanager.net.presenter.RegisterPresenterImpl;

import org.greenrobot.eventbus.EventBus;

/**
 * 注册界面
 * Created by tck on 2017/5/27.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener, LoginAndRegisterContract.RegisterView {

    private EditText mPhoneTv;
    private EditText mPwdTv;

    private RegisterPresenterImpl mRegisterPresenter;

    @Override
    protected void initView() {

        mPhoneTv = (EditText) findViewById(R.id.phone_tv);
        mPwdTv = (EditText) findViewById(R.id.pwd_tv);
        findViewById(R.id.login).setOnClickListener(this);

    }

    @Override
    protected void initData() {
        mRegisterPresenter = new RegisterPresenterImpl(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                toRegister();
                break;
        }
    }

    private void toRegister() {
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

        mRegisterPresenter.register(phone, pwd);
    }

    @Override
    public void showData(InfoBean msg) {
        if (msg != null) {
            showToast(msg.getData());
            if (msg.getStatus()==200) {
                EventBus.getDefault().post(new MessageEvent<String>("UserBean", mPhoneTv.getText().toString().trim()));
                finish();
            }
        }
    }
}
