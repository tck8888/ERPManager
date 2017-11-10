package com.tck.erpmanager.ui.activity.goods_manager;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.commonlibrary.utils.ImageLoadUtils;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.ProductDetailBean;
import com.tck.erpmanager.net.contract.ProductContract;
import com.tck.erpmanager.net.presenter.GetGoodsDetailPresenterImpl;

/**
 * 商品详情页面
 * Created by tck on 2017/6/26.
 */
public class GetGoodsDetailActivity extends BaseActivity implements View.OnClickListener, ProductContract.GetGoodsDetailView {

    private TextView goodsName;
    private TextView goodsBuyPrice;
    private ImageView goodImage;
    private TextView mRemark;
    /**
     * 商品id
     */
    private int mGoodsId;
    private GetGoodsDetailPresenterImpl mGetGoodsDetailPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_goods_detail;
    }

    @Override
    protected void initData() {
        try {
            mGoodsId = getIntent().getIntExtra("goodsId", -1);

            mGetGoodsDetailPresenter = new GetGoodsDetailPresenterImpl(this);
            mGetGoodsDetailPresenter.getGoodsDetail(mGoodsId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {

        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);
        goodsName = (TextView) findViewById(R.id.goods_name);
        goodsBuyPrice = (TextView) findViewById(R.id.goods_buy_price);
        goodImage = (ImageView) findViewById(R.id.good_image);
        mRemark = (TextView) findViewById(R.id.remark);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            /**
             * 商品编辑
             */
            case R.id.icon_add:
                jumpUpdate();
                break;
        }
    }

    private void jumpUpdate() {
        Intent intent = new Intent(this, UpdateGoodsActivity.class);
        intent.putExtra("goodsId", mGoodsId);
        startActivity(intent);
        finish();
    }

    @Override
    public void showData(ProductDetailBean productDetailBean) {

        if (productDetailBean != null) {
            if (productDetailBean.getStatus() == 200) {
                if (productDetailBean.getData() != null) {
                    setViewData(productDetailBean.getData());
                }
            }
        }
    }

    private void setViewData(ProductDetailBean.DataBean data) {
        goodsName.setText(data.getProductName());
        goodsBuyPrice.setText("" + data.getProductPrice());
        mRemark.setText(data.getRemark());
        ImageLoadUtils.getInstance().load(this, goodImage, data.getProductImage());
    }

}
