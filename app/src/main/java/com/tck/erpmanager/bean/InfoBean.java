package com.tck.erpmanager.bean;

/**
 * Created by tck on 2017/7/1.
 */

public class InfoBean {

    /**
     * status : 200
     * messgae : 注册失败
     * data : 手机号已经存在
     */

    private int status;
    private String messgae;
    private String data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
