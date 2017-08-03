package com.tck.commonlibrary.common;

/**
 * Created by tck on 2017/7/1.
 */

public interface HttpUrlList {


    //String BASE_URL = "http://139.224.233.137:8080/ssm1_web-1.0-SNAPSHOT";
    // String BASE_URL = "http://192.168.0.107:8080";
    String BASE_URL = "http://10.10.6.120:8080";

    String USER_MODULE = "/user";
    String PRODUCT_MODULE = "/product";
    String PURCHASEORDER_MODULE = "/purchaseOrder";
    String WAREHOUSE_MODULE = "/warehouse";

    String UP_SINGLE_IMAGE_URL = BASE_URL + "/upload";

    interface MemberModule {
        String LOGIN_URL = BASE_URL + USER_MODULE + "/login/";
        String REGISTER_URL = BASE_URL + USER_MODULE + "/register/";
    }

    interface ProductModule {
        String ADD_GOODS = BASE_URL + PRODUCT_MODULE + "/addProduct";
        String GET_GOODS_LIST = BASE_URL + PRODUCT_MODULE + "/findAll";
        String GET_GOODS_DETAIL = BASE_URL + PRODUCT_MODULE + "/findProductById";

    }

    interface PurchaseOrderModule {

    }

    interface WarehouseModule {

    }
}
