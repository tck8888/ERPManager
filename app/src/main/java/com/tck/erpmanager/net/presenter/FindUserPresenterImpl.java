package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.User;
import com.tck.erpmanager.net.contract.LoginAndRegisterContract;
import com.tck.erpmanager.net.model.FindUserModelImpl;

/**
 * Created by tck on 2017/11/11.
 */

public class FindUserPresenterImpl implements LoginAndRegisterContract.FindUserPresenter, MyCallBack<User> {

    private LoginAndRegisterContract.FindUserView mFindUserView;
    private LoginAndRegisterContract.FindUserModel mFindUserModel;

    public FindUserPresenterImpl(LoginAndRegisterContract.FindUserView findUserView) {
        mFindUserView = findUserView;
        mFindUserModel = new FindUserModelImpl();
    }

    @Override
    public void findUserById(int userId) {
        mFindUserView.showLoading();
        mFindUserModel.findUserById(userId,this);
    }

    @Override
    public void showSuccess(User user) {
        mFindUserView.dimissloading();
        mFindUserView.showData(user);
    }

    @Override
    public void showError(String msg) {
        mFindUserView.dimissloading();
        mFindUserView.showError(msg);
    }
}
