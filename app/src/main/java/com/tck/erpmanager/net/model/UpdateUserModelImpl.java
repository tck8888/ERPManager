package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.CommonConstant;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.AppSharePreferenceMgr;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.ERPApp;
import com.tck.erpmanager.net.contract.LoginAndRegisterContract;

/**
 * Created by tck on 2017/11/11.
 */

public class UpdateUserModelImpl implements LoginAndRegisterContract.UpdateUserModel {

    @Override
    public void update(String name, String type, final MyCallBack<BaseData<String>> myCallBack) {
        OkGo.<BaseData<String>>get(HttpUrlList.MemberModule.UPDATE_USER_INFO)
                .params("name", name)
                .params("type", type)
                .params("userId", (Integer) AppSharePreferenceMgr.get(ERPApp.getContext(), CommonConstant.KEY_USER_ID, -1))
                .execute(new AbsCallback<BaseData<String>>() {
                    @Override
                    public void onSuccess(Response<BaseData<String>> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public BaseData<String> convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), BaseData.class);
                    }

                    @Override
                    public void onError(Response<BaseData<String>> response) {
                        super.onError(response);
                        if (response != null) {
                            myCallBack.showError(response.message());
                        }
                    }
                });
    }
}
