package com.tck.commonlibrary.common;

/**
 * Created by tck on 2017/7/1.
 */

public interface HttpUrlList {


    //String BASE_URL = "http://139.224.233.137:8080/ssm1_web-1.0-SNAPSHOT";
    String BASE_URL = "http://192.168.0.107:8080";

    String USER_MODULE = "/user";
    String PRODUCT_MODULE = "/product";
    String PURCHASEORDER_MODULE = "/purchaseOrder";
    String WAREHOUSE_MODULE = "/warehouse";

    interface MemberModule {
        String LOGIN_URL = BASE_URL + USER_MODULE + "/login/";
        String REGISTER_URL = BASE_URL + USER_MODULE + "/register/";
    }

    interface ProductModule {

    }

    interface PurchaseOrderModule {

    }

    interface WarehouseModule {

    }
}
