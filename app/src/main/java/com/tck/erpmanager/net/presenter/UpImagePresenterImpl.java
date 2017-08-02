package com.tck.erpmanager.net.presenter;

import com.tck.commonlibrary.base.MyCallBack;
import com.tck.erpmanager.bean.InfoBean;
import com.tck.erpmanager.net.contract.UpImageContract;
import com.tck.erpmanager.net.model.UpImageModelImpl;

import java.io.File;

/**
 * Description:
 * <p>
 * Created by tck on 2017/8/1.
 */

public class UpImagePresenterImpl implements UpImageContract.UpImagePresenter, MyCallBack<InfoBean> {

    private UpImageContract.UpImageView mUpImageView;
    private UpImageContract.UpImageModel mUpImageModel;

    public UpImagePresenterImpl(UpImageContract.UpImageView upImageView) {
        mUpImageView = upImageView;
        mUpImageModel = new UpImageModelImpl();
    }

    @Override
    public void upImage(File file) {
        mUpImageView.showLoading();
        mUpImageModel.upImage(file, this);
    }

    @Override
    public void showSuccess(InfoBean s) {
        mUpImageView.dimissloading();
        mUpImageView.showSuccess(s);
    }

    @Override
    public void showError(String msg) {
        mUpImageView.dimissloading();
        mUpImageView.showError(msg);
    }
}
