package com.tck.erpmanager.ui.activity.purchase_order;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.tck.commonlibrary.base.BaseActivity;
import com.tck.commonlibrary.common.CommonConstant;
import com.tck.commonlibrary.utils.AppSharePreferenceMgr;
import com.tck.commonlibrary.widget.CustomListView;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.AccountListBean;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.bean.WarehouseListBean;
import com.tck.erpmanager.net.contract.AccountContract;
import com.tck.erpmanager.net.contract.WarehouseContract;
import com.tck.erpmanager.net.presenter.GetAccountListPresenterImpl;
import com.tck.erpmanager.net.presenter.GetWarehouseListPresenterImpl;

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

public class AddPurchaseOrderActivity extends BaseActivity implements View.OnClickListener, WarehouseContract.GetWarehouseListView, AccountContract.GetAccountListView {

    private TextView selectWarehouse;
    private TextView selectAccount;
    private CustomListView listView;
    private TextView selectDate;
    private EditText remark;

    private OptionsPickerView pvOptions;

    private int mUserId;
    private int warehouseId;
    private int accountId;

    private List<WarehouseListBean.DataBean> warhouseDataList = new ArrayList<>();
    private List<String> warhouseStrList = new ArrayList<>();
    private List<AccountListBean.DataBean> accountDataList = new ArrayList<>();
    private List<String> accountStrList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_purchase_order;
    }

    @Override
    protected void initData() {

        try {
            mUserId = (Integer) AppSharePreferenceMgr.get(this, CommonConstant.KEY_USER_ID, -1);

            GetWarehouseListPresenterImpl getWarehouseListPresenter = new GetWarehouseListPresenterImpl(this);
            getWarehouseListPresenter.getWarehouseList(mUserId);

            GetAccountListPresenterImpl getAccountListPresenter = new GetAccountListPresenterImpl(this);
            getAccountListPresenter.getAccountList(mUserId);

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
        remark = (EditText) findViewById(R.id.remark);

        selectWarehouse.setOnClickListener(this);
        selectAccount.setOnClickListener(this);
        selectDate.setOnClickListener(this);
        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);
        findViewById(R.id.select_product).setOnClickListener(this);
        findViewById(R.id.purchase).setOnClickListener(this);

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
    public void backInfo(MessageEvent event) {

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
    protected void onDestroy() {
        super.onDestroy();

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
