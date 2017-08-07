package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.AccountDetailBean;
import com.tck.erpmanager.net.contract.AccountContract;
import com.tck.erpmanager.net.model.GetAccountDetailModelImpl;

/**
 * Created by tck on 2017/8/7.
 */

public class GetAccountDetailPresenterImpl implements AccountContract.GetAccountDetailPresenter, MyCallBack<AccountDetailBean> {

    private AccountContract.GetAccountDetailView mGetAccountDetailView;
    private AccountContract.GetAccountDetailModel mGetAccountDetailModel;

    public GetAccountDetailPresenterImpl(AccountContract.GetAccountDetailView getAccountDetailView) {
        mGetAccountDetailView = getAccountDetailView;
        mGetAccountDetailModel = new GetAccountDetailModelImpl();
    }

    @Override
    public void getAccountDetail(int accountId) {
        mGetAccountDetailView.showLoading();
        mGetAccountDetailModel.getAccountDetail(accountId, this);
    }

    @Override
    public void showSuccess(AccountDetailBean accountDetailBean) {
        mGetAccountDetailView.dimissloading();
        mGetAccountDetailView.showAccountDeatilBean(accountDetailBean);
    }

    @Override
    public void showError(String msg) {
        mGetAccountDetailView.dimissloading();
        mGetAccountDetailView.showError(msg);
    }
}
