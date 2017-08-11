package com.tck.erpmanager.ui.activity.purchase_order;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.tck.commonlibrary.base.BaseActivity;
import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.common.CommonConstant;
import com.tck.commonlibrary.utils.AppSharePreferenceMgr;
import com.tck.commonlibrary.widget.CustomListView;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.AccountListBean;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.bean.ProductListBean;
import com.tck.erpmanager.bean.PurchaseOrderBean;
import com.tck.erpmanager.bean.WarehouseListBean;
import com.tck.erpmanager.net.contract.AccountContract;
import com.tck.erpmanager.net.contract.PurchaseOrderContract;
import com.tck.erpmanager.net.contract.WarehouseContract;
import com.tck.erpmanager.net.presenter.AddPurchaseOrderPresenterImpl;
import com.tck.erpmanager.net.presenter.GetAccountListPresenterImpl;
import com.tck.erpmanager.net.presenter.GetWarehouseListPresenterImpl;
import com.tck.erpmanager.ui.activity.purchase_order.adapter.ProductItemAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 新增采购单
 * Created by tck on 2017/8/6.
 */

public class AddPurchaseOrderActivity extends BaseActivity implements View.OnClickListener, WarehouseContract.GetWarehouseListView, AccountContract.GetAccountListView, PurchaseOrderContract.AddPurchaseOrderView {

    private TextView selectWarehouse;
    private TextView selectAccount;
    private CustomListView listView;
    private TextView selectDate;
    private TextView totalCount_tv;
    private EditText remark;

    private OptionsPickerView pvOptions;

    private int mUserId = -1;
    private int warehouseId = -1;
    private int accountId = -1;
    private int totalCount = 0;
    private double totalprice = 0.0;

    private List<WarehouseListBean.DataBean> warhouseDataList = new ArrayList<>();
    private List<String> warhouseStrList = new ArrayList<>();
    private List<AccountListBean.DataBean> accountDataList = new ArrayList<>();
    private List<String> accountStrList = new ArrayList<>();

    private List<ProductListBean.DataBean> mProductList = new ArrayList<>();
    private ProductItemAdapter mProductItemAdapter;

    private AddPurchaseOrderPresenterImpl mAddPurchaseOrderPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_purchase_order;
    }

    @Override
    protected void initData() {

        try {
            mUserId = (Integer) AppSharePreferenceMgr.get(this, CommonConstant.KEY_USER_ID, -1);
            //获取仓库列表
            GetWarehouseListPresenterImpl getWarehouseListPresenter = new GetWarehouseListPresenterImpl(this);
            getWarehouseListPresenter.getWarehouseList(mUserId);
            //获取账户列表
            GetAccountListPresenterImpl getAccountListPresenter = new GetAccountListPresenterImpl(this);
            getAccountListPresenter.getAccountList(mUserId);
            //添加采购单
            mAddPurchaseOrderPresenter = new AddPurchaseOrderPresenterImpl(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initView() {

        selectWarehouse = (TextView) findViewById(R.id.select_warehouse);
        selectAccount = (TextView) findViewById(R.id.select_account);
        listView = (CustomListView) findViewById(R.id.list_view);
        selectDate = (TextView) findViewById(R.id.select_date);
        totalCount_tv = (TextView) findViewById(R.id.total_count);
        remark = (EditText) findViewById(R.id.remark);

        selectWarehouse.setOnClickListener(this);
        selectAccount.setOnClickListener(this);
        selectDate.setOnClickListener(this);
        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);
        findViewById(R.id.select_product).setOnClickListener(this);
        findViewById(R.id.purchase).setOnClickListener(this);

        mProductItemAdapter = new ProductItemAdapter(this, mProductList);
        listView.setAdapter(mProductItemAdapter);

        callBackInfo();
    }

    private void callBackInfo() {
        mProductItemAdapter.setIncreaseProduct(new ProductItemAdapter.IncreaseProduct() {
            @Override
            public void increase(int position) {
                int count = mProductList.get(position).getCount();
                count++;
                mProductList.get(position).setCount(count);
                mProductItemAdapter.notifyDataSetChanged();
                calculatePrice(mProductList);
            }
        });

        mProductItemAdapter.setDecreaseProduct(new ProductItemAdapter.DecreaseProduct() {
            @Override
            public void decrease(int position) {
                int count = mProductList.get(position).getCount();
                count--;
                if (count <= 0) {
                    showToast("最少为1件");
                    return;
                }
                mProductList.get(position).setCount(count);
                mProductItemAdapter.notifyDataSetChanged();
                calculatePrice(mProductList);
            }
        });
    }


    public void calculatePrice(List<ProductListBean.DataBean> mDataList) {
        totalCount = 0;
        totalprice = 0.0;
        for (int i = 0; i < mDataList.size(); i++) {
            int count = mDataList.get(i).getCount();
            double productPrice = mDataList.get(i).getProductPrice();
            totalCount += count;
            totalprice += count * productPrice;
        }

        totalCount_tv.setText("合计 " + totalCount + "件 " + totalprice + "元");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                hideSoftKeyboard();
                finish();
                break;
            /**
             * 添加仓库
             */
            case R.id.select_warehouse:
                hideSoftKeyboard();
                if (warhouseDataList.size() > 0) {
                    addWarehouse();
                } else {
                    showToast("暂无仓库，前往添加仓库");
                }
                break;
            /**
             *添加账户
             */
            case R.id.select_account:
                hideSoftKeyboard();
                if (accountDataList.size() > 0) {
                    addAccount();
                } else {
                    showToast("暂无账户，前往添加账户");
                }

                break;
            /**
             * 添加商品
             */
            case R.id.select_product:
                hideSoftKeyboard();
                addProduct();
                break;
            /**
             * 选择时间
             */
            case R.id.select_date:
                hideSoftKeyboard();
                addDate();
                break;
            /**
             * 采购
             */
            case R.id.icon_add:
                hideSoftKeyboard();
                purchaseOrder();
                break;
            case R.id.purchase:
                hideSoftKeyboard();
                purchaseOrder();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void backInfo(MessageEvent<List<ProductListBean.DataBean>> event) {
        if (event != null) {
            if (event.getTag().equals("AddPurchaseOrderSelectGoodsActivity")) {
                List<ProductListBean.DataBean> data = event.getData();
                if (mProductList.size() > 0) {
                    mProductList.clear();
                }
                mProductList.addAll(data);
                mProductItemAdapter.notifyDataSetChanged();
                calculatePrice(mProductList);
            }
        }
    }

    /**
     * 添加仓库
     */
    private void addWarehouse() {
        warhouseStrList.clear();
        for (WarehouseListBean.DataBean dataBean : warhouseDataList) {
            warhouseStrList.add(dataBean.getProductName());
        }

        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                WarehouseListBean.DataBean dataBean = warhouseDataList.get(options1);
                selectWarehouse.setText(dataBean.getProductName());
                warehouseId = dataBean.getId();
            }
        }).build();
        pvOptions.setPicker(warhouseStrList);
        pvOptions.show();

    }

    /**
     * 添加账户
     */
    private void addAccount() {
        accountStrList.clear();
        for (AccountListBean.DataBean dataBean : accountDataList) {
            accountStrList.add(dataBean.getAccountName());
        }

        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                AccountListBean.DataBean dataBean = accountDataList.get(options1);
                selectAccount.setText(dataBean.getAccountName());
                accountId = dataBean.getId();
            }
        }).build();
        pvOptions.setPicker(accountStrList);
        pvOptions.show();

    }

    /**
     * 添加商品
     */
    private void addProduct() {
        Intent intent = new Intent(this, AddPurchaseOrderSelectGoodsActivity.class);
        startActivity(intent);
    }

    /**
     * 选择时间
     */
    private void addDate() {
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String time = simpleDateFormat.format(date);
                //日期格式，精确到日
                selectDate.setText(time);
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .build();
        pvTime.show();
    }

    /**
     * 采购
     */
    private void purchaseOrder() {
        if (accountId == -1) {
            showToast("请选择账户");
            return;
        }
        if (warehouseId == -1) {
            showToast("请选择仓库");
            return;
        }
        if (mProductList.size() <= 0) {
            showToast("请添加商品");
            return;
        }
        if (TextUtils.isEmpty(selectDate.getText().toString().trim())) {
            showToast("请选择业务日期");
            return;
        }
        PurchaseOrderBean purchaseOrderBean = new PurchaseOrderBean();
        purchaseOrderBean.setUserId(mUserId);
        purchaseOrderBean.setAccountName(selectAccount.getText().toString());
        purchaseOrderBean.setAccountId(accountId);
        purchaseOrderBean.setWarehouseName(selectWarehouse.getText().toString());
        purchaseOrderBean.setWarehouseId(warehouseId);
        purchaseOrderBean.setDate(selectDate.getText().toString().trim());
        purchaseOrderBean.setRemark(remark.getText().toString().trim());
        purchaseOrderBean.setTotalCount(totalCount);
        purchaseOrderBean.setTotalprice(totalprice);
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < mProductList.size(); i++) {
            int id = mProductList.get(i).getId();
            int count = mProductList.get(i).getCount();
            if (i == mProductList.size()) {
                sb1.append(id);
                sb2.append(count);
            } else {
                sb1.append(id + ",");
                sb2.append(count + ",");
            }
        }
        purchaseOrderBean.setProductCount(sb2.toString());
        purchaseOrderBean.setProductId(sb1.toString());

        mAddPurchaseOrderPresenter.addPurchaseOrder(purchaseOrderBean);
    }

    @Override
    public void showWarehouseList(WarehouseListBean warehouseListBean) {
        if (warehouseListBean != null) {
            if (warehouseListBean.getData() != null) {
                if (warehouseListBean.getData().size() > 0) {
                    if (warhouseDataList.size() > 0) {
                        warhouseDataList.clear();
                    }
                    warhouseDataList.addAll(warehouseListBean.getData());
                }
            }
        }
    }

    @Override
    public void showAccountList(AccountListBean accountListBean) {
        if (accountListBean != null) {
            if (accountListBean.getData() != null) {
                if (accountListBean.getData().size() > 0) {
                    if (accountDataList.size() > 0) {
                        accountDataList.clear();
                    }
                    accountDataList.addAll(accountListBean.getData());
                }
            }
        }
    }

    @Override
    public void showAddPurchaseOrderSuccess(BaseData<String> data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


}
