package com.tck.erpmanager.bean;

/**
 * Description:
 * <p>
 * Created by tck on 2017/8/3.
 */

public class ProductDetailBean {

    /**
     * status : 200
     * messgae : 查询成功
     * data : {"id":1,"productName":"984","productPrice":888,"productImage":"http://tck.oss-cn-shanghai.aliyuncs.com/image/IMG_20160407_200713.jpg","remark":"21jhj","userId":1}
     */

    private int status;
    private String messgae;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * productName : 984
         * productPrice : 888
         * productImage : http://tck.oss-cn-shanghai.aliyuncs.com/image/IMG_20160407_200713.jpg
         * remark : 21jhj
         * userId : 1
         */

        private int id;
        private String productName;
        private double productPrice;
        private String productImage;
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
