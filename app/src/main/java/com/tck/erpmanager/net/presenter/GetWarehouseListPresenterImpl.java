package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.WarehouseListBean;
import com.tck.erpmanager.net.contract.WarehouseContract;
import com.tck.erpmanager.net.model.GetWarehouseListModelImpl;

/**
 * Created by tck on 2017/8/6.
 */

public class GetWarehouseListPresenterImpl implements WarehouseContract.GetWarehouseListPresenter, MyCallBack<WarehouseListBean> {

    private WarehouseContract.GetWarehouseListView mGetWarehouseListView;
    private WarehouseContract.GetWarehouseListModel mGetWarehouseListModel;

    public GetWarehouseListPresenterImpl(WarehouseContract.GetWarehouseListView getWarehouseListView) {
        mGetWarehouseListView = getWarehouseListView;
        mGetWarehouseListModel = new GetWarehouseListModelImpl();
    }

    @Override
    public void getWarehouseList(int userId) {
        mGetWarehouseListView.showLoading();
        mGetWarehouseListModel.getWarehouseList(userId,this);
    }

    @Override
    public void showSuccess(WarehouseListBean warehouseListBean) {
        mGetWarehouseListView.dimissloading();
        mGetWarehouseListView.showWarehouseList(warehouseListBean);
    }

    @Override
    public void showError(String msg) {
        mGetWarehouseListView.dimissloading();
        mGetWarehouseListView.showError(msg);
    }
}
