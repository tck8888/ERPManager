package com.tck.erpmanager.ui.activity.purchase_order;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tck.commonlibrary.base.BaseActivity;
import com.tck.commonlibrary.common.CommonConstant;
import com.tck.commonlibrary.utils.AppSharePreferenceMgr;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.bean.PurchaseOrderListBean;
import com.tck.erpmanager.net.contract.PurchaseOrderContract;
import com.tck.erpmanager.net.presenter.GetPurchaseOrderListPresenterImpl;
import com.tck.erpmanager.ui.activity.purchase_order.adapter.GetPurchaseOrderListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 采购单列表界面
 * Created by tck on 2017/8/6.
 */

public class GetPurchaseOrderListActivity extends BaseActivity implements View.OnClickListener, OnRefreshListener, PurchaseOrderContract.GetPurchaseOrderListView {


    private ListView listView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private GetPurchaseOrderListPresenterImpl mGetPurchaseOrderListPresenter;

    private List<PurchaseOrderListBean.DataBean> mDataBeanList = new ArrayList<>();
    private GetPurchaseOrderListAdapter mGetPurchaseOrderListAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_purchase_order_list;
    }

    @Override
    protected void initData() {
        mGetPurchaseOrderListPresenter = new GetPurchaseOrderListPresenterImpl(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    @Override
    protected void initView() {
        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);
        mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        mSmartRefreshLayout.setOnRefreshListener(this);

        listView = (ListView) findViewById(R.id.listView);
        mGetPurchaseOrderListAdapter = new GetPurchaseOrderListAdapter(this, mDataBeanList);
        listView.setAdapter(mGetPurchaseOrderListAdapter);
        mSmartRefreshLayout.autoRefresh();
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

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mGetPurchaseOrderListPresenter.getPurchaseOrderList((Integer) AppSharePreferenceMgr.get(this, CommonConstant.KEY_USER_ID, -1));
        refreshlayout.finishRefresh();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent<String> event) {
        if (event.getTag().equals("AddPurchaseOrderActivity")) {
            mSmartRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void showPurchaseOrderList(PurchaseOrderListBean purchaseOrderListBean) {
        if (purchaseOrderListBean != null) {
            if (purchaseOrderListBean.getStatus() == 200) {
                if (purchaseOrderListBean.getData() != null) {
                    if (!purchaseOrderListBean.getData().isEmpty()) {
                        if (!mDataBeanList.isEmpty()) {
                            mDataBeanList.clear();
                        }
                        mDataBeanList.addAll(purchaseOrderListBean.getData());
                        mGetPurchaseOrderListAdapter.notifyDataSetChanged();
                    } else {
                        if (!mDataBeanList.isEmpty()) {
                            mDataBeanList.clear();
                        }
                        mGetPurchaseOrderListAdapter.notifyDataSetChanged();
                    }
                }
            }
        }

    }
}
