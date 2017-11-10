package com.tck.erpmanager.net.contract;

import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.IBaseView;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.SaleOrderBean;
import com.tck.erpmanager.bean.SaleOrderDeatilBean;
import com.tck.erpmanager.bean.SaleOrderListBean;

/**
 * Created by tck on 2017/8/6.
 */

public interface SaleOrderContract {
    interface GetSaleOrderListPresenter {
        void getSaleOrderList(int userId);
    }

    interface GetSaleOrderListView extends IBaseView {
        void showSaleOrderList(SaleOrderListBean saleOrderListBean);

    }

    interface GetSaleOrderListModel {
        void getSaleOrderList(int userId, MyCallBack<SaleOrderListBean> myCallBack);
    }

    interface GetSaleOrderDeatilPresenter {
        void getSaleOrderList(int purchaseOrderId);
    }

    interface GetSaleOrderDeatilView extends IBaseView {
        void showSaleOrderDeatilBean(SaleOrderDeatilBean saleOrderDeatilBean);
    }

    interface GetSaleOrderDeatilModel {
        void getSaleOrderList(int purchaseOrderId, MyCallBack<SaleOrderDeatilBean> myCallBack);
    }

    interface AddSaleOrderPresenter {
        void addSaleOrder(SaleOrderBean saleOrderBean);
    }

    interface AddSaleOrderView extends IBaseView {
        void showAddSaleOrderSuccess(BaseData<String> data);
    }

    interface AddSaleOrderModel {
        void addSaleOrder(SaleOrderBean saleOrderBean, MyCallBack<BaseData<String>> myCallBack);
    }
}
