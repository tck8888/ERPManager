package com.tck.erpmanager.net.contract;

import com.tck.commonlibrary.base.IBaseView;
import com.tck.commonlibrary.base.MyCallBack;

import java.io.File;

/**
 * Description:
 * <p>
 * Created by tck on 2017/8/1.
 */

public interface UpImageContract {

    interface UpImagePresenter {
        void upImage(File file);
    }

    interface UpImageView extends IBaseView {
        void showSuccess(String str);
    }

    interface UpImageModel {
        void upImage(File file, MyCallBack<String> myCallBack);
    }
}
