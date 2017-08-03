package com.tck.erpmanager.net.contract;

import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.IBaseView;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.ProductBean;
import com.tck.erpmanager.bean.ProductDetailBean;
import com.tck.erpmanager.bean.ProductListBean;

/**
 * Created by tck on 2017/6/26.
 */

public interface ProductContract {

    interface AddGoodsPresenter {
        void addGoods(ProductBean productBean);
    }

    interface AddGoodsView extends IBaseView {
        void showData(BaseData<String> msg);
    }

    interface AddGoodsModel {
        void addGoods(ProductBean productBean, MyCallBack<BaseData<String>> myCallBack);
    }

    interface GetGoodsListPresnter {
        void getGoodsList();
    }

    interface GetGoodsListView extends IBaseView {
        void showData(ProductListBean productListBean);
    }

    interface GetGoodsListModel {
        void getGoodsList(MyCallBack<ProductListBean> myCallBack);
    }

    interface GetGoodsDetailPresenter {
        void getGoodsDetail(int goodsId);
    }

    interface GetGoodsDetailView extends IBaseView {
        void showData(ProductDetailBean avObject);
    }

    interface GetGoodsDetailModel {
        void getGoodsDetail(int goodsId,MyCallBack<ProductDetailBean> myCallBack);
    }
}
