package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.SaleOrderBean;
import com.tck.erpmanager.net.contract.SaleOrderContract;
import com.tck.erpmanager.net.model.AddSaleOrderModelImpl;

/**
 * Created by tck on 2017/8/6.
 */

public class AddSaleOrderPresenterImpl implements SaleOrderContract.AddSaleOrderPresenter, MyCallBack<BaseData<String>> {

    private SaleOrderContract.AddSaleOrderView mAddSaleOrderView;
    private SaleOrderContract.AddSaleOrderModel mAddSaleOrderModel;

    public AddSaleOrderPresenterImpl(SaleOrderContract.AddSaleOrderView addSaleOrderView) {
        mAddSaleOrderView = addSaleOrderView;
        mAddSaleOrderModel = new AddSaleOrderModelImpl();

    }

    @Override
    public void addSaleOrder(SaleOrderBean saleOrderBean) {
        mAddSaleOrderView.showLoading();
        mAddSaleOrderModel.addSaleOrder(saleOrderBean, this);
    }

    @Override
    public void showSuccess(BaseData<String> data) {
        mAddSaleOrderView.dimissloading();
        mAddSaleOrderView.showAddSaleOrderSuccess(data);
    }

    @Override
    public void showError(String msg) {
        mAddSaleOrderView.dimissloading();
        mAddSaleOrderView.showError(msg);
    }
}
