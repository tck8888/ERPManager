package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.bean.SaleOrderListBean;
import com.tck.erpmanager.net.contract.SaleOrderContract;

/**
 * Created by tck on 2017/11/11.
 */

public class GetSaleOrderListModelImpl implements SaleOrderContract.GetSaleOrderListModel {

    @Override
    public void getSaleOrderList(int userId, final MyCallBack<SaleOrderListBean> myCallBack) {
        OkGo.<SaleOrderListBean>get(HttpUrlList.PurchaseOrderModule.ADD_PURCHASE_ORDER_LIST_URL)
                .params("userId", userId)
                .execute(new AbsCallback<SaleOrderListBean>() {
                    @Override
                    public void onSuccess(Response<SaleOrderListBean> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public SaleOrderListBean convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), SaleOrderListBean.class);
                    }


                    @Override
                    public void onError(Response<SaleOrderListBean> response) {
                        super.onError(response);
                        if (response != null) {
                            myCallBack.showError(response.message());
                        }
                    }
                });
    }
}
