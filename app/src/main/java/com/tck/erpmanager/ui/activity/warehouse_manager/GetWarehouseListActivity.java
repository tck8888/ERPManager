package com.tck.erpmanager.ui.activity.warehouse_manager;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.bean.WarehouseListBean;
import com.tck.erpmanager.net.contract.WarehouseContract;
import com.tck.erpmanager.net.presenter.GetWarehouseListPresenterImpl;
import com.tck.erpmanager.ui.activity.warehouse_manager.adapter.GetWarehouseListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:仓库列表界面
 * <p>
 * Created by tck on 2017/8/4.
 */

public class GetWarehouseListActivity extends BaseActivity implements View.OnClickListener,
        WarehouseContract.GetWarehouseListView {

    private ListView listView;
    private GetWarehouseListPresenterImpl mGetWarehouseListPresenter;

    private List<WarehouseListBean.DataBean> mWarehouseList = new ArrayList<>();
    private GetWarehouseListAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_warehouse_list;
    }

    @Override
    protected void initData() {
        mGetWarehouseListPresenter = new GetWarehouseListPresenterImpl(this);
        mGetWarehouseListPresenter.getWarehouseList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void initView() {

        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new GetWarehouseListAdapter(this, mWarehouseList);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WarehouseListBean.DataBean dataBean = mWarehouseList.get(position);
                Intent intent = new Intent(GetWarehouseListActivity.this, GetWarehouseDetailActivity.class);
                intent.putExtra("warehouseId", dataBean.getId());
                startActivity(intent);

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void backInfo(MessageEvent<String> event) {
        if (event != null) {
            if (event.getTag().equals("AddWarehouseActivity")) {
                mGetWarehouseListPresenter.getWarehouseList();
            }
        }
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

    @Override
    public void showWarehouseList(WarehouseListBean warehouseListBean) {
        if (warehouseListBean != null) {
            showToast(warehouseListBean.getMessgae());
            if (warehouseListBean.getStatus() == 200) {
                if (warehouseListBean.getData() != null) {
                    if (warehouseListBean.getData().size() != 0) {
                        if (mWarehouseList.size() > 0) {
                            mWarehouseList.clear();
                        }
                        mWarehouseList.addAll(warehouseListBean.getData());
                        mAdapter.notifyDataSetChanged();
                    }
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
