package com.tck.erpmanager.ui.activity.goods_manager;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.bean.ProductListBean;
import com.tck.erpmanager.net.contract.ProductContract;
import com.tck.erpmanager.net.presenter.GetGoodsListPresnterImpl;
import com.tck.erpmanager.ui.activity.goods_manager.adapter.GoodsListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品列表查询
 * Created by tck on 2017/6/25.
 */

public class GetGoodsListActivity extends BaseActivity implements View.OnClickListener, ProductContract.GetGoodsListView {

    private ListView listView;

    private List<ProductListBean.DataBean> mList = new ArrayList<>();
    private GetGoodsListPresnterImpl mGetGoodsListPresnter;
    private GoodsListAdapter mGoodsListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_goods_list;
    }

    @Override
    protected void initData() {
        mGetGoodsListPresnter = new GetGoodsListPresnterImpl(this);
        mGetGoodsListPresnter.getGoodsList();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void backInfo(MessageEvent<String> event) {
        if (event != null) {
            if (event.getTag().equals("AddGoodsActivity")) {
                mGetGoodsListPresnter.getGoodsList();
            }

            if (event.getTag().equals("UpdateGoodsActivity")) {
                mGetGoodsListPresnter.getGoodsList();
            }
        }
    }

    @Override
    protected void initView() {


        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);

        mGoodsListAdapter = new GoodsListAdapter(this, mList);
        listView.setAdapter(mGoodsListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(GetGoodsListActivity.this, GetGoodsDetailActivity.class);
                intent.putExtra("goodsId", mList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            /**
             * 添加商品界面
             */
            case R.id.icon_add:
                startActivity(new Intent(this, AddGoodsActivity.class));
                break;
        }
    }

    @Override
    public void showData(ProductListBean productListBean) {
        if (productListBean != null) {
            if (productListBean.getData() != null) {
                if (productListBean.getData().size() > 0) {
                    if (mList.size() > 0) {
                        mList.clear();
                    }
                    mList.addAll(productListBean.getData());
                    mGoodsListAdapter.notifyDataSetChanged();
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
