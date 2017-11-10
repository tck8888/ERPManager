package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.net.contract.LoginAndRegisterContract;
import com.tck.erpmanager.net.model.UpdateUserModelImpl;

/**
 * Created by tck on 2017/11/11.
 */

public class UpdateUserPresenterImpl implements LoginAndRegisterContract.UpdateUserPresenter, MyCallBack<BaseData<String>> {

    private LoginAndRegisterContract.UpdateUserView mUpdateUserView;
    private LoginAndRegisterContract.UpdateUserModel mUpdateUserModel;

    public UpdateUserPresenterImpl(LoginAndRegisterContract.UpdateUserView updateUserView) {
        mUpdateUserView = updateUserView;
        mUpdateUserModel = new UpdateUserModelImpl();
    }

    @Override
    public void update(String name, String type) {
        mUpdateUserView.showLoading();
        mUpdateUserModel.update(name, type, this);
    }

    @Override
    public void showSuccess(BaseData<String> data) {
        mUpdateUserView.dimissloading();
        mUpdateUserView.showData(data);
    }

    @Override
    public void showError(String msg) {
        mUpdateUserView.dimissloading();
        mUpdateUserView.showError(msg);
    }
}
