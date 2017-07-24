package com.tck.erpmanager.net.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.net.contract.ProductContract;

/**
 * Created by tck on 2017/6/27.
 */

public class GetGoodsDetailModelImpl implements ProductContract.GetGoodsDetailModel {
    @Override
    public void getGoodsDetail(String objectId, final MyCallBack<AVObject> myCallBack) {
        AVQuery<AVObject> avQuery = new AVQuery<>("Product");
        avQuery.getInBackground(objectId, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (e == null) {
                    myCallBack.showSuccess(avObject);
                } else {
                    myCallBack.showError(e.getMessage());
                }
            }
        });
    }
}
