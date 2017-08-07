package com.tck.erpmanager.net.contract;

import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.IBaseView;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.AccountBean;
import com.tck.erpmanager.bean.AccountDetailBean;
import com.tck.erpmanager.bean.AccountListBean;

/**
 * Created by tck on 2017/8/6.
 */

public interface AccountContract {
    interface GetAccountListPresenter {
        void getAccountList(int userId);
    }

    interface GetAccountListView extends IBaseView {
        void showAccountList(AccountListBean accountListBean);

    }

    interface GetAccountListModel {
        void getAccountList(int userId, MyCallBack<AccountListBean> myCallBack);
    }

    interface GetAccountDetailPresenter {
        void getAccountDetail(int accountId);
    }

    interface GetAccountDetailView extends IBaseView {
        void showAccountDeatilBean(AccountDetailBean accountDetailBean);
    }

    interface GetAccountDetailModel {
        void getAccountDetail(int accountId, MyCallBack<AccountDetailBean> myCallBack);
    }

    interface AddAccountPresenter {
        void addAccount(AccountBean purchaseOrderBean);
    }

    interface AddAccountView extends IBaseView {
        void showAddAccountSuccess(BaseData<String> data);
    }

    interface AddAccountModel {
        void addAccount(AccountBean accountBean, MyCallBack<BaseData<String>> myCallBack);
    }
}
