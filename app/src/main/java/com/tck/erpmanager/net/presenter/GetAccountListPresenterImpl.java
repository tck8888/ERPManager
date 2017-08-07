package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.AccountListBean;
import com.tck.erpmanager.net.contract.AccountContract;
import com.tck.erpmanager.net.model.GetAccountListModelImpl;

/**
 * Created by tck on 2017/8/6.
 */

public class GetAccountListPresenterImpl implements AccountContract.GetAccountListPresenter, MyCallBack<AccountListBean> {

    private AccountContract.GetAccountListView mGetAccountListView;
    private AccountContract.GetAccountListModel mGetAccountListModel;

    public GetAccountListPresenterImpl(AccountContract.GetAccountListView getAccountListView) {
        mGetAccountListView = getAccountListView;
        mGetAccountListModel = new GetAccountListModelImpl();
    }

    @Override
    public void getAccountList(int userId) {
        mGetAccountListView.showLoading();
        mGetAccountListModel.getAccountList(userId, this);
    }

    @Override
    public void showSuccess(AccountListBean accountListBean) {
        mGetAccountListView.dimissloading();
        mGetAccountListView.showAccountList(accountListBean);
    }

    @Override
    public void showError(String msg) {
        mGetAccountListView.dimissloading();
        mGetAccountListView.showError(msg);
    }


}
