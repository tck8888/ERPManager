package com.tck.erpmanager.bean;

import java.util.List;

/**
 * Description:
 * <p>
 * Created by tck on 2017/7/28.
 */

public class ProductListBean {


    /**
     * status : 200
     * messgae : 查询成功
     * data : [{"id":1,"productName":"984","productPrice":888,"productImage":"1122","remark":"21jhj","userId":1},{"id":3,"productName":"惨咯","productPrice":55555,"productImage":"","remark":"咯哦喔喔","userId":1}]
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
         * productName : 984
         * productPrice : 888.0
         * productImage : 1122
         * remark : 21jhj
         * userId : 1
         */

        private int id;
        private String productName;
        private double productPrice;
        private String productImage;
        private String remark;
        private int userId;

        private boolean isSelected = false;
        private int count = 1;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

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

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
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
