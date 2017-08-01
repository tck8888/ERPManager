package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.erpmanager.net.contract.UpImageContract;

import java.io.File;

/**
 * Description:
 * <p>
 * Created by tck on 2017/8/1.
 */

public class UpImageModelImpl implements UpImageContract.UpImageModel {
    @Override
    public void upImage(File file, final MyCallBack<String> myCallBack) {

        OkGo.<String>post(HttpUrlList.UP_SINGLE_IMAGE_URL)
                .params("file", file)
                .execute(new AbsCallback<String>() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public String convertResponse(okhttp3.Response response) throws Throwable {
                        return response.body().string();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (response != null) {
                            myCallBack.showError(response.message());
                        }
                    }
                });

    }
}
