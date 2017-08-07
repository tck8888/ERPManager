package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.bean.AccountListBean;
import com.tck.erpmanager.net.contract.AccountContract;

/**
 * Created by tck on 2017/8/6.
 */

public class GetAccountListModelImpl implements AccountContract.GetAccountListModel {

    @Override
    public void getAccountList(int userId, final MyCallBack<AccountListBean> myCallBack) {
        OkGo.<AccountListBean>get(HttpUrlList.AccountModule.GET_ACCOUNT_LIST_URL)
                .params("userId", userId)
                .execute(new AbsCallback<AccountListBean>() {
                    @Override
                    public void onSuccess(Response<AccountListBean> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public AccountListBean convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), AccountListBean.class);
                    }


                    @Override
                    public void onError(Response<AccountListBean> response) {
                        super.onError(response);
                        if (response != null) {
                            myCallBack.showError(response.message());
                        }
                    }
                });
    }
}
