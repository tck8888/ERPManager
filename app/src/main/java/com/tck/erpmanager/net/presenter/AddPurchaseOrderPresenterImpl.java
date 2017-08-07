package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.PurchaseOrderBean;
import com.tck.erpmanager.net.contract.PurchaseOrderContract;
import com.tck.erpmanager.net.model.AddPurchaseOrderModelImpl;

/**
 * Created by tck on 2017/8/6.
 */

public class AddPurchaseOrderPresenterImpl implements PurchaseOrderContract.AddPurchaseOrderPresenter, MyCallBack<BaseData<String>> {

    private PurchaseOrderContract.AddPurchaseOrderView mAddPurchaseOrderView;
    private PurchaseOrderContract.AddPurchaseOrderModel mAddPurchaseOrderModel;

    public AddPurchaseOrderPresenterImpl(PurchaseOrderContract.AddPurchaseOrderView addPurchaseOrderView) {
        mAddPurchaseOrderView = addPurchaseOrderView;
        mAddPurchaseOrderModel = new AddPurchaseOrderModelImpl();
    }

    @Override
    public void addPurchaseOrder(PurchaseOrderBean purchaseOrderBean) {
        mAddPurchaseOrderView.showLoading();
        mAddPurchaseOrderModel.addPurchaseOrder(purchaseOrderBean, this);
    }

    @Override
    public void showSuccess(BaseData<String> data) {
        mAddPurchaseOrderView.dimissloading();
        mAddPurchaseOrderView.showAddPurchaseOrderSuccess(data);
    }

    @Override
    public void showError(String msg) {
        mAddPurchaseOrderView.dimissloading();
        mAddPurchaseOrderView.showError(msg);
    }
}
