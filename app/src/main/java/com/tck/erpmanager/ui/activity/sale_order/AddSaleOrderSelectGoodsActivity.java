package com.tck.erpmanager.ui.activity.sale_order;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.bean.ProductListBean;
import com.tck.erpmanager.net.contract.ProductContract;
import com.tck.erpmanager.net.presenter.GetGoodsListWithStockPresnterImpl;
import com.tck.erpmanager.ui.activity.sale_order.adapter.AddSaleOrderSelectGoodsAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 销售单选择商品
 * <p>
 * Created by tck on 2017/8/8.
 */

public class AddSaleOrderSelectGoodsActivity extends BaseActivity implements View.OnClickListener,
        ProductContract.GetGoodsListWithStockView {

    private ListView listView;

    private List<ProductListBean.DataBean> mList = new ArrayList<>();
    private List<ProductListBean.DataBean> mSelectList = new ArrayList<>();
    private GetGoodsListWithStockPresnterImpl mGetGoodsListWithStockPresnter;
    private AddSaleOrderSelectGoodsAdapter mAddSaleOrderSelectGoodsAdapter;
    private int mWarehouseId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_purchase_order_select_goods;
    }

    @Override
    protected void initData() {
        try {
            mWarehouseId = getIntent().getIntExtra("warehouseId", -1);
            mGetGoodsListWithStockPresnter = new GetGoodsListWithStockPresnterImpl(this);
            mGetGoodsListWithStockPresnter.getGoodsList(mWarehouseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {

        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.btn_ok).setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);

        mAddSaleOrderSelectGoodsAdapter = new AddSaleOrderSelectGoodsAdapter(this, mList);
        listView.setAdapter(mAddSaleOrderSelectGoodsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mList.get(position).isSelected()) {
                    mList.get(position).setSelected(false);
                } else {
                    mList.get(position).setSelected(true);
                }
                mAddSaleOrderSelectGoodsAdapter.notifyDataSetChanged();
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

    /**
     * 选择好了
     */
    private void selectOk() {

        if (mList.size() > 0) {
            if (mSelectList.size() > 0) {
                mSelectList.clear();
            }
            for (int i = 0; i < mList.size(); i++) {
                ProductListBean.DataBean dataBean = mList.get(i);
                if (dataBean.isSelected()) {
                    mSelectList.add(dataBean);
                }
            }
            EventBus.getDefault().post(new MessageEvent<List<ProductListBean.DataBean>>("AddSaleOrderSelectGoodsActivity", mSelectList));
        }
        finish();
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
                    mAddSaleOrderSelectGoodsAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
