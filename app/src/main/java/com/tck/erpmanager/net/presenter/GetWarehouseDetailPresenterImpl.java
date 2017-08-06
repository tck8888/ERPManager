package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.WarehouseDetailBean;
import com.tck.erpmanager.net.contract.WarehouseContract;
import com.tck.erpmanager.net.model.GetWarehouseDetailModelImpl;

/**
 * Created by tck on 2017/8/6.
 */

public class GetWarehouseDetailPresenterImpl implements WarehouseContract.GetWarehouseDetailPresenter, MyCallBack<WarehouseDetailBean> {

    private WarehouseContract.GetWarehouseDetailView mGetWarehouseDetailView;
    private WarehouseContract.GetWarehouseDetailModel mGetWarehouseDetailModel;

    public GetWarehouseDetailPresenterImpl(WarehouseContract.GetWarehouseDetailView getWarehouseDetailView) {
        mGetWarehouseDetailView = getWarehouseDetailView;
        mGetWarehouseDetailModel = new GetWarehouseDetailModelImpl();
    }

    @Override
    public void getWarehouseDetail(int warehouseId) {
        mGetWarehouseDetailView.showLoading();
        mGetWarehouseDetailModel.getWarehouseDetail(warehouseId, this);
    }

    @Override
    public void showSuccess(WarehouseDetailBean warehouseDetailBean) {
        mGetWarehouseDetailView.dimissloading();
        mGetWarehouseDetailView.showWarehouseDetailSuccess(warehouseDetailBean);
    }

    @Override
    public void showError(String msg) {
        mGetWarehouseDetailView.dimissloading();
        mGetWarehouseDetailView.showError(msg);

    }
}
