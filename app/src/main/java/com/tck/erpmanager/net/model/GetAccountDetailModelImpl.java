package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.bean.AccountDetailBean;
import com.tck.erpmanager.net.contract.AccountContract;

/**
 * Created by tck on 2017/8/7.
 */

public class GetAccountDetailModelImpl implements AccountContract.GetAccountDetailModel {

    @Override
    public void getAccountDetail(int accountId, final MyCallBack<AccountDetailBean> myCallBack) {
        OkGo.<AccountDetailBean>get(HttpUrlList.AccountModule.GET_ACCOUNT_DETAIL_URL)
                .params("accountId", accountId)
                .execute(new AbsCallback<AccountDetailBean>() {
                    @Override
                    public void onSuccess(Response<AccountDetailBean> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public AccountDetailBean convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), AccountDetailBean.class);
                    }

                    @Override
                    public void onError(Response<AccountDetailBean> response) {
                        super.onError(response);
                        if (response != null) {
                            myCallBack.showError(response.message());
                        }
                    }

                });
    }
}
