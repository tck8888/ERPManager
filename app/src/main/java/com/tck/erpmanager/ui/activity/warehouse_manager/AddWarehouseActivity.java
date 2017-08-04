package com.tck.erpmanager.ui.activity.warehouse_manager;

import android.view.View;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;

/**
 * Description:新增仓库
 * <p>
 * Created by tck on 2017/8/4.
 */

public class AddWarehouseActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_warehouse;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {


        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_add:
                addWarehouse();
                break;
        }
    }

    private void addWarehouse() {

    }
}
