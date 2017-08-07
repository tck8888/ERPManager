package com.tck.erpmanager.ui.activity.purchase_order;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;

/**
 * 采购单列表界面
 * Created by tck on 2017/8/6.
 */

public class GetPurchaseOrderListActivity extends BaseActivity implements View.OnClickListener {


    private ListView listView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_purchase_order_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_add:
                addPurchaseOrder();
                break;
        }
    }

    private void addPurchaseOrder() {
        Intent intent = new Intent(this, AddPurchaseOrderActivity.class);
        startActivity(intent);
    }
}
