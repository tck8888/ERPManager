package com.tck.erpmanager.bean;

/**
 * Created by tck on 2017/7/1.
 */

public class InfoBean {

    /**
     * status : 200
     * message : 注册成功
     * data : SUCCESS
     */

    private int status;
    private String message;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
