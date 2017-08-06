package com.tck.erpmanager.ui.activity.warehouse_manager;

import android.view.View;
import android.widget.TextView;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.WarehouseDetailBean;
import com.tck.erpmanager.net.contract.WarehouseContract;
import com.tck.erpmanager.net.presenter.GetWarehouseDetailPresenterImpl;

/**
 * 仓库详情界面
 * Created by tck on 2017/8/6.
 */

public class GetWarehouseDetailActivity extends BaseActivity implements View.OnClickListener,
        WarehouseContract.GetWarehouseDetailView {

    private TextView warehoueseName;
    private TextView remark;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_warehouse_detail;
    }

    @Override
    protected void initData() {
        GetWarehouseDetailPresenterImpl getWarehouseDetailPresenter = new GetWarehouseDetailPresenterImpl(this);

        try {
            int warehouseId = getIntent().getIntExtra("warehouseId", -1);
            getWarehouseDetailPresenter.getWarehouseDetail(warehouseId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initView() {

        findViewById(R.id.icon_back).setOnClickListener(this);

        warehoueseName = (TextView) findViewById(R.id.warehouese_name);
        remark = (TextView) findViewById(R.id.remark);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
        }
    }

    @Override
    public void showWarehouseDetailSuccess(WarehouseDetailBean warehouseDetailBean) {
        if (warehouseDetailBean != null) {
            showToast(warehouseDetailBean.getMessgae());
            if (warehouseDetailBean.getData() != null) {
                setViewData(warehouseDetailBean.getData());
            }
        }
    }

    private void setViewData(WarehouseDetailBean.DataBean data) {
        warehoueseName.setText(data.getProductName());
        remark.setText(data.getRemark());
    }
}
