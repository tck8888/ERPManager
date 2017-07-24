package com.tck.erpmanager.net.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.net.contract.ProductContract;

import java.util.List;

/**
 * Created by tck on 2017/6/26.
 */

public class GetGoodsListModelImpl implements ProductContract.GetGoodsListModel {
    @Override
    public void getGoodsList(final MyCallBack<List<AVObject>> myCallBack) {
        AVQuery<AVObject> avQuery = new AVQuery<>("Product");
        avQuery.orderByDescending("createdAt");
        avQuery.include("owner");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    myCallBack.showSuccess(list);
                } else {
                    myCallBack.showError(e.getMessage());
                }
            }
        });
    }
}
