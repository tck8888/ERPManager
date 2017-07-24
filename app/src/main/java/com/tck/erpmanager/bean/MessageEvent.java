package com.tck.erpmanager.bean;

/**
 * Created by tck on 2017/6/24.
 */

public class MessageEvent<T> {

    private String tag;
    private T data;

    public MessageEvent() {
    }

    public MessageEvent(String tag, T data) {
        this.tag = tag;
        this.data = data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
