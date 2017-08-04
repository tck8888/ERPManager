package com.tck.erpmanager.ui.activity.warehouse_manager;

import android.content.Intent;
import android.view.View;

import android.widget.ListView;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;


/**
 * Description:仓库列表界面
 * <p>
 * Created by tck on 2017/8/4.
 */

public class GetWarehouseListActivity extends BaseActivity implements View.OnClickListener {

    private ListView listView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_warehouse_list;
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_add:
                startActivity(new Intent(this, AddWarehouseActivity.class));
                break;
        }
    }
}
