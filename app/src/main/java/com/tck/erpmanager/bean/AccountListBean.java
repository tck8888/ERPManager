package com.tck.erpmanager.bean;

import java.util.List;

/**
 * Created by tck on 2017/8/6.
 */

public class AccountListBean {


    /**
     * status : 200
     * messgae : 查询成功
     * data : [{"id":2,"accountName":"测试账户","remark":"测试账户备注","userId":1,"balance":null}]
     */

    private int status;
    private String messgae;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * accountName : 测试账户
         * remark : 测试账户备注
         * userId : 1
         * balance : null
         */

        private int id;
        private String accountName;
        private String remark;
        private int userId;
        private double balance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }
}
