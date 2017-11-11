package com.tck.erpmanager.ui.activity.sale_order;

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
import com.tck.erpmanager.bean.SaleOrderListBean;
import com.tck.erpmanager.net.contract.SaleOrderContract;
import com.tck.erpmanager.net.presenter.GetSaleOrderListPresenterImpl;
import com.tck.erpmanager.ui.activity.sale_order.adapter.GetSaleOrderListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 销售单列表界面
 * Created by tck on 2017/8/6.
 */

public class GetSaleOrderListActivity extends BaseActivity implements View.OnClickListener,
        OnRefreshListener,
        SaleOrderContract.GetSaleOrderListView {


    private ListView listView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private List<SaleOrderListBean.DataBean> mDataBeanList = new ArrayList<>();
    private GetSaleOrderListAdapter mGetSaleOrderListAdapter;
    private GetSaleOrderListPresenterImpl mGetSaleOrderListPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_sale_order_list;
    }

    @Override
    protected void initData() {
        mGetSaleOrderListPresenter = new GetSaleOrderListPresenterImpl(this);
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
        mGetSaleOrderListAdapter = new GetSaleOrderListAdapter(this, mDataBeanList);
        listView.setAdapter(mGetSaleOrderListAdapter);
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
        Intent intent = new Intent(this, AddSaleOrderActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mGetSaleOrderListPresenter.getSaleOrderList((Integer) AppSharePreferenceMgr.get(this, CommonConstant.KEY_USER_ID, -1));
        refreshlayout.finishRefresh();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent<String> event) {
        if (event.getTag().equals("AddSaleOrderActivity")) {
            mSmartRefreshLayout.autoRefresh();
        }
    }


    @Override
    public void showSaleOrderList(SaleOrderListBean saleOrderListBean) {
        if (saleOrderListBean != null) {
            if (saleOrderListBean.getStatus() == 200) {
                if (saleOrderListBean.getData() != null) {
                    if (!saleOrderListBean.getData().isEmpty()) {
                        if (!mDataBeanList.isEmpty()) {
                            mDataBeanList.clear();
                        }
                        mDataBeanList.addAll(saleOrderListBean.getData());
                        mGetSaleOrderListAdapter.notifyDataSetChanged();
                    } else {
                        if (!mDataBeanList.isEmpty()) {
                            mDataBeanList.clear();
                        }
                        mGetSaleOrderListAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}
