package com.tck.commonlibrary.base;

/**
 * Created by tck on 2017/5/29.
 */

public interface MyCallBack<T> {
    void showSuccess(T t);

    void showError(String msg);
}
