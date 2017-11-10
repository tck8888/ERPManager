package com.tck.erpmanager.bean;

import java.util.List;

/**
 * Created by tck on 2017/11/10.
 */

public class SaleOrderListBean {

    /**
     * status : 200
     * messgae : 查询成功
     * data : [{"id":1,"userId":1,"productId":1,"warehouseId":1,"totalCount":1,"date":"2017-11-11","remark":"休息一下","totalPrice":666}]
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
         * id : 1
         * userId : 1
         * productId : 1
         * warehouseId : 1
         * totalCount : 1
         * date : 2017-11-11
         * remark : 休息一下
         * totalPrice : 666.0
         */

        private int id;
        private int userId;
        private int productId;
        private int warehouseId;
        private int totalCount;
        private String date;
        private String remark;
        private double totalPrice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }
    }
}
