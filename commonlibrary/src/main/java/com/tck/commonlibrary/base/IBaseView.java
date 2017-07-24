package com.tck.commonlibrary.base;

/**
 * Created by tck on 2017/5/29.
 */

public interface IBaseView {

    void showLoading();


    void dimissloading();

    void showError(String msg);
}
