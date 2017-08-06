package com.tck.erpmanager.net.contract;

import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.IBaseView;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.WarehouseBean;
import com.tck.erpmanager.bean.WarehouseDetailBean;
import com.tck.erpmanager.bean.WarehouseListBean;

/**
 * Created by tck on 2017/8/6.
 */

public interface WarehouseContract {

    interface GetWarehouseListPresenter {
        void getWarehouseList();
    }

    interface GetWarehouseListView extends IBaseView {
        void showWarehouseList(WarehouseListBean warehouseListBean);
    }

    interface GetWarehouseListModel {
        void getWarehouseList(MyCallBack<WarehouseListBean> myCallBack);
    }

    interface AddWarehousePresenter {
        void addWarehouse(WarehouseBean warehouseBean);
    }

    interface AddWarehouseView extends IBaseView {
        void showAddWarehouseSuccess(BaseData<String> infoBean);
    }

    interface AddWarehouseModel {
        void addWarehouse(WarehouseBean warehouseBean, MyCallBack<BaseData<String>> myCallBack);
    }

    interface GetWarehouseDetailPresenter {
        void getWarehouseDetail(int warehouseId);
    }

    interface GetWarehouseDetailView extends IBaseView {
        void showWarehouseDetailSuccess(WarehouseDetailBean warehouseDetailBean);
    }

    interface GetWarehouseDetailModel {
        void getWarehouseDetail(int warehouseId, MyCallBack<WarehouseDetailBean> myCallBack);
    }
}
