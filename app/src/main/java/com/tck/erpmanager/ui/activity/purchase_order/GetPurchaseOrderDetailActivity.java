package com.tck.erpmanager.ui.activity.purchase_order;

import android.view.View;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;

/**
 * 采购单详情界面
 * Created by tck on 2017/8/6.
 */

public class GetPurchaseOrderDetailActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_purchase_order_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.icon_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
        }
    }
}
