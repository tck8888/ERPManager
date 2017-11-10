package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.bean.PurchaseOrderListBean;
import com.tck.erpmanager.net.contract.PurchaseOrderContract;

/**
 * Created by tck on 2017/11/10.
 */

public class GetPurchaseOrderListModelImpl implements PurchaseOrderContract.GetPurchaseOrderListModel {

    @Override
    public void getPurchaseOrderList(int userId, final MyCallBack<PurchaseOrderListBean> myCallBack) {
        OkGo.<PurchaseOrderListBean>get(HttpUrlList.PurchaseOrderModule.ADD_PURCHASE_ORDER_LIST_URL)
                .params("userId", userId)
                .execute(new AbsCallback<PurchaseOrderListBean>() {
                    @Override
                    public void onSuccess(Response<PurchaseOrderListBean> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public PurchaseOrderListBean convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), PurchaseOrderListBean.class);
                    }


                    @Override
                    public void onError(Response<PurchaseOrderListBean> response) {
                        super.onError(response);
                        if (response != null) {
                            myCallBack.showError(response.message());
                        }
                    }
                });
    }
}
