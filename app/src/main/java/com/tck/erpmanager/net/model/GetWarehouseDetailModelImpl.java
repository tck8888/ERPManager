package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.bean.WarehouseDetailBean;
import com.tck.erpmanager.net.contract.WarehouseContract;

/**
 * Created by tck on 2017/8/6.
 */

public class GetWarehouseDetailModelImpl implements WarehouseContract.GetWarehouseDetailModel {

    @Override
    public void getWarehouseDetail( int warehouseId, final MyCallBack<WarehouseDetailBean> myCallBack) {
        OkGo.<WarehouseDetailBean>get(HttpUrlList.WarehouseModule.GET_WAREHOUSE_DETAIL_URL)
                .params("warehouseId", warehouseId)
                .execute(new AbsCallback<WarehouseDetailBean>() {
                    @Override
                    public void onSuccess(Response<WarehouseDetailBean> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public WarehouseDetailBean convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), WarehouseDetailBean.class);
                    }

                    @Override
                    public void onError(Response<WarehouseDetailBean> response) {
                        super.onError(response);
                        if (response != null) {
                            myCallBack.showError(response.message());
                        }
                    }
                });
    }
}
