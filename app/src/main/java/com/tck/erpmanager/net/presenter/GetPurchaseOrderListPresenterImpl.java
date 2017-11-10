package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.PurchaseOrderListBean;
import com.tck.erpmanager.net.contract.PurchaseOrderContract;
import com.tck.erpmanager.net.model.GetPurchaseOrderListModelImpl;

/**
 * Created by tck on 2017/11/10.
 */

public class GetPurchaseOrderListPresenterImpl implements PurchaseOrderContract.GetPurchaseOrderListPresenter, MyCallBack<PurchaseOrderListBean> {

    private PurchaseOrderContract.GetPurchaseOrderListView mGetPurchaseOrderListView;
    private PurchaseOrderContract.GetPurchaseOrderListModel mGetPurchaseOrderListModel;

    public GetPurchaseOrderListPresenterImpl(PurchaseOrderContract.GetPurchaseOrderListView getPurchaseOrderListView) {
        mGetPurchaseOrderListView = getPurchaseOrderListView;
        mGetPurchaseOrderListModel = new GetPurchaseOrderListModelImpl();
    }

    @Override
    public void getPurchaseOrderList(int userId) {
        mGetPurchaseOrderListView.showLoading();
        mGetPurchaseOrderListModel.getPurchaseOrderList(userId, this);
    }

    @Override
    public void showSuccess(PurchaseOrderListBean purchaseOrderListBean) {
        mGetPurchaseOrderListView.dimissloading();
        mGetPurchaseOrderListView.showPurchaseOrderList(purchaseOrderListBean);
    }

    @Override
    public void showError(String msg) {
        mGetPurchaseOrderListView.dimissloading();
        mGetPurchaseOrderListView.showError(msg);
    }
}
