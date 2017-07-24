package com.tck.erpmanager.net.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.ProductBean;
import com.tck.erpmanager.net.contract.ProductContract;

import java.io.FileNotFoundException;

/**
 * Created by tck on 2017/6/26.
 */
public class AddGoodsModelImpl implements ProductContract.AddGoodsModel {
    @Override
    public void addGoods(ProductBean productBean, final MyCallBack<String> myCallBack) {
        AVObject product = new AVObject("Product");
        product.put("name", productBean.getProductName());
        product.put("number", productBean.getProductNumber());
        product.put("buyprice", productBean.getProductBuyPrice());
        product.put("saleprice", productBean.getProductSalePrice());
        product.put("remark", productBean.getRemark());
        product.put("owner", AVUser.getCurrentUser());
        try {
            product.put("image", AVFile.withAbsoluteLocalPath("image1",productBean.getImageUrl()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        product.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    myCallBack.showSuccess("新增成功");
                } else {
                    myCallBack.showError(e.getMessage());
                }
            }
        });
    }
}
