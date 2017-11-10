package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.bean.User;
import com.tck.erpmanager.net.contract.LoginAndRegisterContract;

/**
 * Created by tck on 2017/11/11.
 */

public class FindUserModelImpl implements LoginAndRegisterContract.FindUserModel {
    @Override
    public void findUserById(int userId, final MyCallBack<User> myCallBack) {
        OkGo.<User>get(HttpUrlList.MemberModule.FIND_USER_BY_ID)
                .tag(this)
                .params("userId", userId)
                .execute(new AbsCallback<User>() {
                    @Override
                    public void onSuccess(Response<User> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public User convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), User.class);
                    }

                    @Override
                    public void onError(Response<User> response) {
                        super.onError(response);

                        myCallBack.showError(response.message());
                    }
                });
    }
}
