package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.AccountBean;
import com.tck.erpmanager.net.contract.AccountContract;
import com.tck.erpmanager.net.model.AddAccountModelImpl;

/**
 * Created by tck on 2017/8/6.
 */

public class AddAccountPresenterImpl implements AccountContract.AddAccountPresenter, MyCallBack<BaseData<String>> {

    private AccountContract.AddAccountView mAddAccountView;
    private AccountContract.AddAccountModel mAddAccountModel;

    public AddAccountPresenterImpl(AccountContract.AddAccountView addAccountView) {
        mAddAccountView = addAccountView;
        mAddAccountModel = new AddAccountModelImpl();
    }

    @Override
    public void addAccount(AccountBean accountBean) {
        mAddAccountView.showLoading();
        mAddAccountModel.addAccount(accountBean, this);
    }

    @Override
    public void showSuccess(BaseData<String> data) {
        mAddAccountView.dimissloading();
        mAddAccountView.showAddAccountSuccess(data);
    }

    @Override
    public void showError(String msg) {
        mAddAccountView.dimissloading();
        mAddAccountView.showError(msg);
    }


}
