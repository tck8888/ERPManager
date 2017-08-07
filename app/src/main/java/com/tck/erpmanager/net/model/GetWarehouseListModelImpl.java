package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.bean.WarehouseListBean;
import com.tck.erpmanager.net.contract.WarehouseContract;

/**
 * Created by tck on 2017/8/6.
 */

public class GetWarehouseListModelImpl implements WarehouseContract.GetWarehouseListModel {


    @Override
    public void getWarehouseList(int userId, final MyCallBack<WarehouseListBean> myCallBack) {
        OkGo.<WarehouseListBean>get(HttpUrlList.WarehouseModule.GET_WAREHOUSE_LIST_URL)
                .params("userId", userId)
                .execute(new AbsCallback<WarehouseListBean>() {
                    @Override
                    public void onSuccess(Response<WarehouseListBean> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public WarehouseListBean convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), WarehouseListBean.class);
                    }


                    @Override
                    public void onError(Response<WarehouseListBean> response) {
                        super.onError(response);
                        if (response != null) {
                            myCallBack.showError(response.message());
                        }
                    }
                });
    }
}
