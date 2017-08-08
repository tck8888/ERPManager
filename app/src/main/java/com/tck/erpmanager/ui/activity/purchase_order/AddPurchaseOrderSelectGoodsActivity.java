package com.tck.erpmanager.ui.activity.purchase_order;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.ProductListBean;
import com.tck.erpmanager.net.contract.ProductContract;
import com.tck.erpmanager.net.presenter.GetGoodsListPresnterImpl;
import com.tck.erpmanager.ui.activity.purchase_order.adapter.AddPurchaseOrderSelectGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tck on 2017/8/8.
 */

public class AddPurchaseOrderSelectGoodsActivity extends BaseActivity implements View.OnClickListener,
        ProductContract.GetGoodsListView {

    private ListView listView;

    private List<ProductListBean.DataBean> mList = new ArrayList<>();
    private GetGoodsListPresnterImpl mGetGoodsListPresnter;
    private AddPurchaseOrderSelectGoodsAdapter mAddPurchaseOrderSelectGoodsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_purchase_order_select_goods;
    }

    @Override
    protected void initData() {
        mGetGoodsListPresnter = new GetGoodsListPresnterImpl(this);
        mGetGoodsListPresnter.getGoodsList();
    }

    @Override
    protected void initView() {

        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.btn_ok).setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);

        mAddPurchaseOrderSelectGoodsAdapter = new AddPurchaseOrderSelectGoodsAdapter(this, mList);
        listView.setAdapter(mAddPurchaseOrderSelectGoodsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mList.get(position).isSelected()) {
                    mList.get(position).setSelected(false);
                } else {
                    mList.get(position).setSelected(true);
                }
                mAddPurchaseOrderSelectGoodsAdapter.notifyDataSetChanged();
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
             * 选择好了
             */
            case R.id.btn_ok:
                selectOk();
                break;
        }
    }

    private void selectOk() {

    }

    @Override
    public void showData(ProductListBean productListBean) {
        if (productListBean != null) {
            showToast(productListBean.getMessgae());
            if (productListBean.getData() != null) {
                if (productListBean.getData().size() > 0) {
                    if (mList.size() > 0) {
                        mList.clear();
                    }
                    mList.addAll(productListBean.getData());
                    mAddPurchaseOrderSelectGoodsAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
