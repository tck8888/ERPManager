package com.tck.erpmanager.net.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.commonlibrary.common.HttpUrlList;
import com.tck.commonlibrary.utils.GsonUtil;
import com.tck.erpmanager.bean.InfoBean;
import com.tck.erpmanager.net.contract.LoginAndRegisterContract;

/**
 * Created by tck on 2017/6/24.
 */

public class RegisterModelImpl implements LoginAndRegisterContract.RegisterModel {

    @Override
    public void register(String username, String pwd, final MyCallBack<InfoBean> myCallBack) {
      /*  AVUser user = new AVUser();//
        user.setUsername(username);// 设置用户名
        user.setPassword(pwd);// 设置密码

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    myCallBack.showSuccess("注册成功");
                } else {
                    myCallBack.showError(e.getMessage());
                }
            }
        });*/

        OkGo.<InfoBean>get(HttpUrlList.MemberModule.REGISTER_URL+username +"/" + pwd)
                .execute(new AbsCallback<InfoBean>() {
                    @Override
                    public void onSuccess(Response<InfoBean> response) {
                        myCallBack.showSuccess(response.body());
                    }

                    @Override
                    public InfoBean convertResponse(okhttp3.Response response) throws Throwable {
                        return GsonUtil.GsonToBean(response.body().string(), InfoBean.class);
                    }
                });
    }
}
