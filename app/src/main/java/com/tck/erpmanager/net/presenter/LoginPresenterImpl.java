package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.User;
import com.tck.erpmanager.net.contract.LoginAndRegisterContract;
import com.tck.erpmanager.net.model.LoginModelImpl;

/**
 * Created by tck on 2017/6/24.
 */

public class LoginPresenterImpl implements LoginAndRegisterContract.LoginPresenter ,MyCallBack<User>{

    private LoginAndRegisterContract.LoginView mLoginView;
    private LoginAndRegisterContract.LoginModel mLoginModel;

    public LoginPresenterImpl(LoginAndRegisterContract.LoginView loginView) {
        mLoginView = loginView;
        mLoginModel = new LoginModelImpl();
    }

    @Override
    public void login(String username, String pwd) {
        mLoginView.showLoading();
        mLoginModel.login(username,pwd,this);
    }

    @Override
    public void showSuccess(User s) {
        mLoginView.dimissloading();
        mLoginView.showData(s);
    }

    @Override
    public void showError(String msg) {
        mLoginView.dimissloading();
        mLoginView.showError(msg);

    }
}
