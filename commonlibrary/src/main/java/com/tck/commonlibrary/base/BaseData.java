package com.tck.commonlibrary.base;

/**
 * Created by tck on 2017/7/1.
 */
public class BaseData<T> {

    private int status;
    private String message;
    private T Data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
