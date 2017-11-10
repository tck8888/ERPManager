package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.SaleOrderListBean;
import com.tck.erpmanager.net.contract.SaleOrderContract;
import com.tck.erpmanager.net.model.GetSaleOrderListModelImpl;

/**
 * Created by tck on 2017/11/11.
 */

public class GetSaleOrderListPresenterImpl implements SaleOrderContract.GetSaleOrderListPresenter, MyCallBack<SaleOrderListBean> {

    private SaleOrderContract.GetSaleOrderListView mGetSaleOrderListView;
    private SaleOrderContract.GetSaleOrderListModel mGetSaleOrderListModel;

    public GetSaleOrderListPresenterImpl(SaleOrderContract.GetSaleOrderListView getSaleOrderListView) {
        mGetSaleOrderListView = getSaleOrderListView;
        mGetSaleOrderListModel = new GetSaleOrderListModelImpl();
    }

    @Override
    public void showSuccess(SaleOrderListBean saleOrderListBean) {
        mGetSaleOrderListView.dimissloading();
        mGetSaleOrderListView.showSaleOrderList(saleOrderListBean);
    }

    @Override
    public void showError(String msg) {
        mGetSaleOrderListView.dimissloading();
        mGetSaleOrderListView.showError(msg);
    }

    @Override
    public void getSaleOrderList(int userId) {
        mGetSaleOrderListView.showLoading();
        mGetSaleOrderListModel.getSaleOrderList(userId, this);
    }
}
