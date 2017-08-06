package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.bean.WarehouseBean;
import com.tck.erpmanager.net.contract.WarehouseContract;

/**
 * Created by tck on 2017/8/6.
 */

public class AddWarehouseModelImpl implements WarehouseContract.AddWarehouseModel {
    @Override
    public void addWarehouse(WarehouseBean warehouseBean, final MyCallBack<BaseData<String>> myCallBack) {
        OkGo.<BaseData<String>>get(HttpUrlList.WarehouseModule.ADD_WAREHOUSE_URL)
                .params("productName", warehouseBean.getWarehouseName())
                .params("remark", warehouseBean.getRemark())
                .params("userId", warehouseBean.getId())
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
