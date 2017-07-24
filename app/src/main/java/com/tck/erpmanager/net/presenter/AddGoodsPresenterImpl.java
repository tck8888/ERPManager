package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.ProductBean;
import com.tck.erpmanager.net.contract.ProductContract;
import com.tck.erpmanager.net.model.AddGoodsModelImpl;

/**
 * Created by tck on 2017/6/26.
 */

public class AddGoodsPresenterImpl implements ProductContract.AddGoodsPresenter, MyCallBack<String> {

    private ProductContract.AddGoodsView mAddGoodsView;
    private ProductContract.AddGoodsModel mAddGoodsModel;

    public AddGoodsPresenterImpl(ProductContract.AddGoodsView addGoodsView) {
        mAddGoodsView = addGoodsView;
        mAddGoodsModel = new AddGoodsModelImpl();
    }

    @Override
    public void addGoods(ProductBean productBean) {
        mAddGoodsView.showLoading();
        mAddGoodsModel.addGoods(productBean, this);
    }

    @Override
    public void showSuccess(String s) {
        mAddGoodsView.dimissloading();
        mAddGoodsView.showData(s);
    }

    @Override
    public void showError(String msg) {
        mAddGoodsView.dimissloading();
        mAddGoodsView.showError(msg);
    }
}
