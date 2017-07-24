package com.tck.erpmanager.net.presenter;

import com.avos.avoscloud.AVObject;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.net.contract.ProductContract;
import com.tck.erpmanager.net.model.GetGoodsListModelImpl;

import java.util.List;

/**
 * Created by tck on 2017/6/26.
 */

public class GetGoodsListPresnterImpl implements ProductContract.GetGoodsListPresnter,MyCallBack<List<AVObject>> {
    private ProductContract.GetGoodsListView mGetGoodsListView;
    private ProductContract.GetGoodsListModel mGetGoodsListModel;

    public GetGoodsListPresnterImpl(ProductContract.GetGoodsListView getGoodsListView) {
        mGetGoodsListView = getGoodsListView;
        mGetGoodsListModel = new GetGoodsListModelImpl();
    }

    @Override
    public void getGoodsList() {
        mGetGoodsListView.showLoading();
        mGetGoodsListModel.getGoodsList(this);
    }

    @Override
    public void showSuccess(List<AVObject> list) {
        mGetGoodsListView.dimissloading();
        mGetGoodsListView.showData(list);
    }

    @Override
    public void showError(String msg) {
        mGetGoodsListView.dimissloading();
        mGetGoodsListView.showError(msg);
    }
}
