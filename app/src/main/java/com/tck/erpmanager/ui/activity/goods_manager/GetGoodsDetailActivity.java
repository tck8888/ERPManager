package com.tck.erpmanager.ui.activity.goods_manager;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;


import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;
import com.tck.erpmanager.net.contract.ProductContract;
import com.tck.erpmanager.net.presenter.GetGoodsDetailPresenterImpl;

/**
 * 商品详情页面
 * Created by tck on 2017/6/26.
 */
public class GetGoodsDetailActivity extends BaseActivity implements View.OnClickListener, ProductContract.GetGoodsDetailView {

    private TextView goodsName;
    private TextView goodsNumber;
    private TextView goodsBuyPrice;
    private TextView goodsSalePrice;
    private TextView mRemark;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_goods_detail;
    }

    @Override
    protected void initData() {
        try {
            String objectId = getIntent().getStringExtra("objectId");
            System.out.println("=============="+objectId);
            GetGoodsDetailPresenterImpl getGoodsDetailPresenter = new GetGoodsDetailPresenterImpl(this);
            getGoodsDetailPresenter.getGoodsDetail(objectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {

        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);
        goodsName = (TextView) findViewById(R.id.goods_name);
        goodsNumber = (TextView) findViewById(R.id.goods_number);
        goodsBuyPrice = (TextView) findViewById(R.id.goods_buy_price);
        goodsSalePrice = (TextView) findViewById(R.id.goods_sale_price);
        mRemark = (TextView) findViewById(R.id.remark);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_add:
                jumpUpdate();
                break;
        }
    }

    private void jumpUpdate() {
        startActivity(new Intent(this, UpdateGoodsActivity.class));
    }

    @Override
    public void showData(String avObject) {

    }
}
