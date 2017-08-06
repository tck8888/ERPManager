package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.WarehouseBean;
import com.tck.erpmanager.net.contract.WarehouseContract;
import com.tck.erpmanager.net.model.AddWarehouseModelImpl;

/**
 * Created by tck on 2017/8/6.
 */

public class AddWarehousePresenterImpl implements WarehouseContract.AddWarehousePresenter, MyCallBack<BaseData<String>> {

    private WarehouseContract.AddWarehouseView mAddWarehouseView;
    private WarehouseContract.AddWarehouseModel mAddWarehouseModel;

    public AddWarehousePresenterImpl(WarehouseContract.AddWarehouseView addWarehouseView) {
        mAddWarehouseView = addWarehouseView;
        mAddWarehouseModel = new AddWarehouseModelImpl();
    }

    @Override
    public void addWarehouse(WarehouseBean warehouseBean) {
        mAddWarehouseView.showLoading();
        mAddWarehouseModel.addWarehouse(warehouseBean, this);
    }

    @Override
    public void showSuccess(BaseData<String> infoBean) {
        mAddWarehouseView.dimissloading();
        mAddWarehouseView.showAddWarehouseSuccess(infoBean);
    }

    @Override
    public void showError(String msg) {
        mAddWarehouseView.dimissloading();
        mAddWarehouseView.showError(msg);
    }
}
