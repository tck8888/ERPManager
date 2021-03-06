package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.ProductBean;
import com.tck.erpmanager.net.contract.ProductContract;
import com.tck.erpmanager.net.model.AddGoodsModelImpl;

/**
 * Created by tck on 2017/6/26.
 */

public class AddGoodsPresenterImpl implements ProductContract.AddGoodsPresenter, MyCallBack<BaseData<String>> {

    private ProductContract.AddGoodsView mAddGoodsView;
    private ProductContract.AddGoodsModel mAddGoodsModel;

    public AddGoodsPresenterImpl(ProductContract.AddGoodsView addGoodsView) {
        mAddGoodsView = addGoodsView;
        mAddGoodsModel = new AddGoodsModelImpl();
    }

    @Override
    public void addGoods(ProductBean productBean,String type) {
        mAddGoodsView.showLoading();
        mAddGoodsModel.addGoods(productBean,type, this);
    }

    @Override
    public void showSuccess(BaseData<String> s) {
        mAddGoodsView.dimissloading();
        mAddGoodsView.showData(s);
    }

    @Override
    public void showError(String msg) {
        mAddGoodsView.dimissloading();
        mAddGoodsView.showError(msg);
    }
}
