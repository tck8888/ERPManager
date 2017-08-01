package com.tck.erpmanager.net.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.CommonConstant;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.AppSharePreferenceMgr;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.ERPApp;
import com.tck.erpmanager.bean.ProductBean;
import com.tck.erpmanager.net.contract.ProductContract;

/**
 * Created by tck on 2017/6/26.
 */
public class AddGoodsModelImpl implements ProductContract.AddGoodsModel {
    @Override
    public void addGoods(ProductBean productBean, final MyCallBack<BaseData<String>> myCallBack) {
        OkGo.<BaseData<String>>get(HttpUrlList.ProductModule.ADD_GOODS)
                .tag(this)
                .params("productName", productBean.getProductName())
                .params("productPrice", productBean.getProductBuyPrice())
                .params("productImage", productBean.getImageUrl())
                .params("remark", productBean.getRemark())
                .params("userId", (Integer) AppSharePreferenceMgr.get(ERPApp.getContext(), CommonConstant.KEY_USER_ID, -1))
                .execute(new AbsCallback<BaseData<String>>() {
                    @Override
                    public BaseData<String> convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), BaseData.class);
                    }

                    @Override
                    public void onSuccess(Response<BaseData<String>> response) {
                        myCallBack.showSuccess(response.body());
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
