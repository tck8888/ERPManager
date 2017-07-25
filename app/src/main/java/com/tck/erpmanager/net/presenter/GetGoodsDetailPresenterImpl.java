package com.tck.erpmanager.net.presenter;


import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.net.contract.ProductContract;
import com.tck.erpmanager.net.model.GetGoodsDetailModelImpl;

/**
 * Created by tck on 2017/6/27.
 */

public class GetGoodsDetailPresenterImpl implements ProductContract.GetGoodsDetailPresenter, MyCallBack<String> {

    private ProductContract.GetGoodsDetailView mGetGoodsDetailView;
    private ProductContract.GetGoodsDetailModel mGetGoodsDetailModel;

    public GetGoodsDetailPresenterImpl(ProductContract.GetGoodsDetailView getGoodsDetailView) {
        mGetGoodsDetailView = getGoodsDetailView;
        mGetGoodsDetailModel = new GetGoodsDetailModelImpl();
    }

    @Override
    public void getGoodsDetail(String objectId) {
        mGetGoodsDetailView.showLoading();
        mGetGoodsDetailModel.getGoodsDetail(objectId, this);
    }

    @Override
    public void showSuccess(String avObject) {
        mGetGoodsDetailView.dimissloading();
        mGetGoodsDetailView.showData(avObject);
    }

    @Override
    public void showError(String msg) {
        mGetGoodsDetailView.dimissloading();
        mGetGoodsDetailView.showError(msg);
    }
}
