package com.tck.commonlibrary.common;

/**
 * Created by tck on 2017/7/1.
 */

public interface HttpUrlList {


    //String BASE_URL = "http://139.224.233.137:8080/ssm1_web-1.0-SNAPSHOT";
  //String BASE_URL = "http://192.168.0.109:8080";
    //String BASE_URL = "http://10.10.2.189:8080";
    String BASE_URL = "http://192.168.137.1:8080";

    String USER_MODULE = "/user";
    String PRODUCT_MODULE = "/product";
    String PURCHASEORDER_MODULE = "/purchaseOrder";
    String SALE_ORDER_MODULE = "/saleOrder";
    String WAREHOUSE_MODULE = "/warehouse";
    String ACCOUNT_MODULE = "/account";


    String UP_SINGLE_IMAGE_URL = BASE_URL + "/upload";

    interface MemberModule {
        String LOGIN_URL = BASE_URL + USER_MODULE + "/login";
        String REGISTER_URL = BASE_URL + USER_MODULE + "/register";

        String UPDATE_USER_INFO = BASE_URL + USER_MODULE + "/update";
        String FIND_USER_BY_ID = BASE_URL + USER_MODULE + "/findUserById";
    }

    interface ProductModule {
        String ADD_GOODS = BASE_URL + PRODUCT_MODULE + "/addProduct";
        String GET_GOODS_LIST = BASE_URL + PRODUCT_MODULE + "/findAll";
        String GET_GOODS_WITH_STOCK_LIST = BASE_URL + PRODUCT_MODULE + "/findAllByStock";
        String GET_GOODS_DETAIL = BASE_URL + PRODUCT_MODULE + "/findProductById";
        String UPDATE_GOODS_DETAIL = BASE_URL + PRODUCT_MODULE + "/updateProduct";

    }

    interface PurchaseOrderModule {
        String ADD_PURCHASE_ORDER_URL = BASE_URL + PURCHASEORDER_MODULE + "/addOrder";
        String ADD_PURCHASE_ORDER_LIST_URL = BASE_URL + PURCHASEORDER_MODULE + "/findOrderByUserId";
    }

    interface SaleOrderModule {
        String ADD_SALE_ORDER_URL = BASE_URL + SALE_ORDER_MODULE + "/addOrder";
        String GET_SALE_ORDER_LIST_URL = BASE_URL + SALE_ORDER_MODULE + "/findOrderByUserId";
    }

    interface WarehouseModule {
        String GET_WAREHOUSE_LIST_URL = BASE_URL + WAREHOUSE_MODULE + "/getWarehouseList";
        String GET_WAREHOUSE_DETAIL_URL = BASE_URL + WAREHOUSE_MODULE + "/findWarehouseByWarehouseId";
        String ADD_WAREHOUSE_URL = BASE_URL + WAREHOUSE_MODULE + "/addWarehouse";

    }

    interface AccountModule {
        String GET_ACCOUNT_LIST_URL = BASE_URL + ACCOUNT_MODULE + "/getAccountList";
        String GET_ACCOUNT_DETAIL_URL = BASE_URL + ACCOUNT_MODULE + "/findAccountById";
        String ADD_ACCOUNT_URL = BASE_URL + ACCOUNT_MODULE + "/addAccount";
    }
}
