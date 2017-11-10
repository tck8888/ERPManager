package com.tck.erpmanager.net.contract;

import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.base.IBaseView;
import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.InfoBean;
import com.tck.erpmanager.bean.User;

/**
 * Created by tck on 2017/5/29.
 */

public interface LoginAndRegisterContract {

    interface LoginPresenter {
        void login(String username, String pwd);
    }

    interface LoginView extends IBaseView {
        void showData(User user);
    }

    interface LoginModel {
        void login(String username, String pwd, MyCallBack<User> myCallBack);
    }

    interface RegisterPresenter {
        void register(String username, String pwd);
    }

    interface RegisterView extends IBaseView {
        void showData(InfoBean avUser);
    }

    interface RegisterModel {
        void register(String username, String pwd, MyCallBack<InfoBean> myCallBack);
    }


    interface UpdateUserPresenter {
        void update(String name, String type);
    }

    interface UpdateUserView extends IBaseView {
        void showData(BaseData<String> data);
    }

    interface UpdateUserModel {
        void update(String name, String type, MyCallBack<BaseData<String>> myCallBack);
    }

    interface FindUserPresenter {
        void findUserById(int userId);
    }

    interface FindUserView extends IBaseView {
        void showData(User user);
    }

    interface FindUserModel {
        void findUserById(int userId, MyCallBack<User> myCallBack);
    }

}
