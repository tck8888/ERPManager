package com.tck.erpmanager.net.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.bean.ProductDetailBean;
import com.tck.erpmanager.net.contract.ProductContract;

/**
 * Created by tck on 2017/6/27.
 */

public class GetGoodsDetailModelImpl implements ProductContract.GetGoodsDetailModel {
    @Override
    public void getGoodsDetail(int goodsId, final MyCallBack<ProductDetailBean> myCallBack) {
        OkGo.<ProductDetailBean>get(HttpUrlList.ProductModule.GET_GOODS_DETAIL)
                .params("id", goodsId)
                .execute(new AbsCallback<ProductDetailBean>() {
                    @Override
                    public void onSuccess(Response<ProductDetailBean> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public ProductDetailBean convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), ProductDetailBean.class);
                    }

                    @Override
                    public void onError(Response<ProductDetailBean> response) {
                        super.onError(response);
                        if (response != null) {
                            myCallBack.showError(response.message());
                        }
                    }
                });
    }
}
