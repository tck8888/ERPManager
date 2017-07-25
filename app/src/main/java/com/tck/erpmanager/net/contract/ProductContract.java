package com.tck.erpmanager.net.contract;

import com.tck.commonlibrary.base.IBaseView;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.ProductBean;

import java.util.List;

/**
 * Created by tck on 2017/6/26.
 */

public interface ProductContract {

    interface AddGoodsPresenter {
        void addGoods(ProductBean productBean);
    }

    interface AddGoodsView extends IBaseView {
        void showData(String msg);
    }

    interface AddGoodsModel {
        void addGoods(ProductBean productBean, MyCallBack<String> myCallBack);
    }

    interface GetGoodsListPresnter {
        void getGoodsList();
    }

    interface GetGoodsListView extends IBaseView {
        void showData(List<String> list);
    }

    interface GetGoodsListModel {
        void getGoodsList(MyCallBack<List<String>> myCallBack);
    }

    interface GetGoodsDetailPresenter {
        void getGoodsDetail(String objectId);
    }

    interface GetGoodsDetailView extends IBaseView {
        void showData(String avObject);
    }

    interface GetGoodsDetailModel {
        void getGoodsDetail(String objectId,MyCallBack<String> myCallBack);
    }
}
