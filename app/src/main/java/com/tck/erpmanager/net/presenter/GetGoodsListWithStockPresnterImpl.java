package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.ProductListBean;
import com.tck.erpmanager.net.contract.ProductContract;
import com.tck.erpmanager.net.model.GetGoodsListWithStockModelImpl;

/**
 * Created by tck on 2017/11/10.
 */

public class GetGoodsListWithStockPresnterImpl implements ProductContract.GetGoodsListWithStockPresnter, MyCallBack<ProductListBean> {

    private ProductContract.GetGoodsListWithStockView mGetGoodsListWithStockView;
    private ProductContract.GetGoodsListWithStockModel mGetGoodsListWithStockModel;

    public GetGoodsListWithStockPresnterImpl(ProductContract.GetGoodsListWithStockView getGoodsListWithStockView) {
        mGetGoodsListWithStockView = getGoodsListWithStockView;
        mGetGoodsListWithStockModel = new GetGoodsListWithStockModelImpl();
    }

    @Override
    public void getGoodsList(int warehouseId) {
        mGetGoodsListWithStockView.showLoading();
        mGetGoodsListWithStockModel.getGoodsList(warehouseId, this);
    }

    @Override
    public void showSuccess(ProductListBean productListBean) {
        mGetGoodsListWithStockView.dimissloading();
        mGetGoodsListWithStockView.showData(productListBean);
    }

    @Override
    public void showError(String msg) {
        mGetGoodsListWithStockView.dimissloading();
        mGetGoodsListWithStockView.showError(msg);
    }
}
