package com.tck.erpmanager.bean;

/**
 * Created by tck on 2017/6/26.
 */

public class ProductBean {

    private String productName;
    private String productNumber;
    private String productBuyPrice;
    private String productSalePrice;
    private String remark;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String imageUrl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductBuyPrice() {
        return productBuyPrice;
    }

    public void setProductBuyPrice(String productBuyPrice) {
        this.productBuyPrice = productBuyPrice;
    }

    public String getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(String productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
