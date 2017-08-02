package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.bean.InfoBean;
import com.tck.erpmanager.net.contract.UpImageContract;

import java.io.File;

/**
 * Description:
 * <p>
 * Created by tck on 2017/8/1.
 */

public class UpImageModelImpl implements UpImageContract.UpImageModel {
    @Override
    public void upImage(File file, final MyCallBack<InfoBean> myCallBack) {

        OkGo.<InfoBean>post(HttpUrlList.UP_SINGLE_IMAGE_URL)
                .params("file", file)
                .execute(new AbsCallback<InfoBean>() {
                    @Override
                    public void onSuccess(Response<InfoBean> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public InfoBean convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), InfoBean.class);
                    }

                    @Override
                    public void onError(Response<InfoBean> response) {
                        super.onError(response);
                        myCallBack.showError(response.message());
                    }
                });

    }

}
