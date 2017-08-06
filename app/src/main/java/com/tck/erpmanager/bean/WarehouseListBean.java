package com.tck.erpmanager.bean;

import java.util.List;

/**
 * Created by tck on 2017/8/6.
 */

public class WarehouseListBean {

    /**
     * status : 200
     * messgae : 查询成功
     * data : [{"id":3,"productName":"测试","remark":"测试新增加仓库","userId":1}]
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
         * id : 3
         * productName : 测试
         * remark : 测试新增加仓库
         * userId : 1
         */

        private int id;
        private String productName;
        private String remark;
        private int userId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
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
    }
}
