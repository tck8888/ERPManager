package com.tck.erpmanager.net.contract;

import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.IBaseView;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.PurchaseOrderBean;
import com.tck.erpmanager.bean.PurchaseOrderDeatilBean;
import com.tck.erpmanager.bean.PurchaseOrderListBean;

/**
 * Created by tck on 2017/8/6.
 */

public interface PurchaseOrderContract {
    interface GetPurchaseOrderListPresenter {
        void getPurchaseOrderList(int userId);
    }

    interface GetPurchaseOrderListView  extends IBaseView{
        void showPurchaseOrderList(PurchaseOrderListBean purchaseOrderListBean);

    }

    interface GetPurchaseOrderListModel {
        void getPurchaseOrderList(int userId, MyCallBack<PurchaseOrderListBean> myCallBack);
    }

    interface GetPurchaseOrderDeatilPresenter {
        void getPurchaseOrderList(int purchaseOrderId);
    }

    interface GetPurchaseOrderDeatilView extends IBaseView{
        void showPurchaseOrderDeatilBean(PurchaseOrderDeatilBean purchaseOrderDeatilBean);
    }

    interface GetPurchaseOrderDeatilModel {
        void getPurchaseOrderList(int purchaseOrderId, MyCallBack<PurchaseOrderDeatilBean> myCallBack);
    }

    interface AddPurchaseOrderPresenter {
        void addPurchaseOrder(PurchaseOrderBean purchaseOrderBean);
    }

    interface AddPurchaseOrderView extends IBaseView{
        void showAddPurchaseOrderSuccess(BaseData<String> data);
    }

    interface AddPurchaseOrderModel {
        void addPurchaseOrder(PurchaseOrderBean purchaseOrderBean,MyCallBack<BaseData<String>> myCallBack);
    }
}
