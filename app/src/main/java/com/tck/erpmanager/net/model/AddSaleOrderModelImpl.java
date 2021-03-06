package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.bean.SaleOrderBean;
import com.tck.erpmanager.net.contract.SaleOrderContract;

/**
 * Created by tck on 2017/8/6.
 */

public class AddSaleOrderModelImpl implements SaleOrderContract.AddSaleOrderModel {
    @Override
    public void addSaleOrder(SaleOrderBean purchaseOrderBean, final MyCallBack<BaseData<String>> myCallBack) {
        OkGo.<BaseData<String>>get(HttpUrlList.SaleOrderModule.ADD_SALE_ORDER_URL)
                .params("userId", purchaseOrderBean.getUserId())
                .params("warehouseId", purchaseOrderBean.getWarehouseId())
                .params("warehouseName", purchaseOrderBean.getWarehouseName())
                .params("accountId", purchaseOrderBean.getAccountId())
                .params("accountName", purchaseOrderBean.getAccountName())
                .params("totalCount", purchaseOrderBean.getTotalCount())
                .params("totalPrice", purchaseOrderBean.getTotalprice())
                .params("productId", purchaseOrderBean.getProductId())
                .params("productCount", purchaseOrderBean.getTotalCount())
                .params("date", purchaseOrderBean.getDate())
                .params("remark", purchaseOrderBean.getRemark())
                .execute(new AbsCallback<BaseData<String>>() {
                    @Override
                    public void onSuccess(Response<BaseData<String>> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public BaseData<String> convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), BaseData.class);
                    }

                    @Override
                    public void onError(Response<BaseData<String>> response) {
                        super.onError(response);
                        if (response != null) {
                            myCallBack.showError(response.message());
                        }
                    }
                });
    }
}
