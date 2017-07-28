package com.tck.erpmanager.net.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.CommonConstant;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.AppSharePreferenceMgr;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.ERPApp;
import com.tck.erpmanager.bean.ProductListBean;
import com.tck.erpmanager.net.contract.ProductContract;

/**
 * Created by tck on 2017/6/26.
 */

public class GetGoodsListModelImpl implements ProductContract.GetGoodsListModel {
    @Override
    public void getGoodsList(final MyCallBack<ProductListBean> myCallBack) {
        OkGo.<ProductListBean>get(HttpUrlList.ProductModule.GET_GOODS_LIST)
                .tag(this)
                .params("userId", (Integer) AppSharePreferenceMgr.get(ERPApp.getContext(), CommonConstant.KEY_USER_ID, -1))
                .execute(new AbsCallback<ProductListBean>() {
                    @Override
                    public void onSuccess(Response<ProductListBean> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public ProductListBean convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), ProductListBean.class);
                    }

                    @Override
                    public void onError(Response<ProductListBean> response) {
                        super.onError(response);
                        if (response != null) {
                            myCallBack.showError(response.message());
                        }
                    }
                });
    }
}
