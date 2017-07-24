package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.InfoBean;
import com.tck.erpmanager.net.contract.LoginAndRegisterContract;
import com.tck.erpmanager.net.model.RegisterModelImpl;

/**
 * Created by tck on 2017/6/24.
 */

public class RegisterPresenterImpl implements LoginAndRegisterContract.RegisterPresenter, MyCallBack<InfoBean> {

    private LoginAndRegisterContract.RegisterView mRegisterView;
    private LoginAndRegisterContract.RegisterModel mRegisterModel;

    public RegisterPresenterImpl(LoginAndRegisterContract.RegisterView registerView) {
        mRegisterView = registerView;
        mRegisterModel = new RegisterModelImpl();
    }

    @Override
    public void register(String username, String pwd) {
        mRegisterModel.register(username, pwd, this);
    }

    @Override
    public void showSuccess(InfoBean s) {
        mRegisterView.dimissloading();
        mRegisterView.showData(s);
    }

    @Override
    public void showError(String msg) {
        mRegisterView.dimissloading();
        mRegisterView.showError(msg);
    }
}
