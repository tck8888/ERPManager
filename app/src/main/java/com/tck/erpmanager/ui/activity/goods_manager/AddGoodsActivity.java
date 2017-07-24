package com.tck.erpmanager.ui.activity.goods_manager;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.ProductBean;
import com.tck.erpmanager.net.contract.ProductContract;
import com.tck.erpmanager.net.presenter.AddGoodsPresenterImpl;

/**
 * 添加商品界面
 * Created by tck on 2017/6/25.
 */

public class AddGoodsActivity extends BaseActivity implements View.OnClickListener, ProductContract.AddGoodsView {

    private EditText mGoodsName;
    private EditText mGoodsNumber;
    private EditText mGoodsBuyPrice;
    private EditText mGoodsSalePrice;
    private EditText mRemark;

    private AddGoodsPresenterImpl mAddGoodsPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_goods;
    }

    @Override
    protected void initData() {
        mAddGoodsPresenter = new AddGoodsPresenterImpl(this);
    }

    @Override
    protected void initView() {
        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);

        mGoodsName = (EditText) findViewById(R.id.goods_name);
        mGoodsNumber = (EditText) findViewById(R.id.goods_number);
        mGoodsBuyPrice = (EditText) findViewById(R.id.goods_buy_price);
        mGoodsSalePrice = (EditText) findViewById(R.id.goods_sale_price);
        mRemark = (EditText) findViewById(R.id.remark);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            /**
             * 保存
             */
            case R.id.icon_add:
                saveProduct();
                break;
        }
    }

    private void saveProduct() {
        String goodsName = mGoodsName.getText().toString().trim();
        String goodsNumber = mGoodsNumber.getText().toString().trim();
        String goodsBuyPrice = mGoodsBuyPrice.getText().toString().trim();
        String goodsSalePrice = mGoodsSalePrice.getText().toString().trim();

        if (TextUtils.isEmpty(goodsName)) {
            showToast("商品名称不能为空");
            return;
        }
        if (TextUtils.isEmpty(goodsNumber)) {
            showToast("商品货号不能为空");
            return;
        }
        if (TextUtils.isEmpty(goodsBuyPrice)) {
            showToast("商品采购价不能为空");
            return;
        }
        if (TextUtils.isEmpty(goodsSalePrice)) {
            showToast("商品零售价不能为空");
            return;
        }

        mAddGoodsPresenter.addGoods(getProductBean(goodsName, goodsNumber, goodsBuyPrice, goodsSalePrice));
    }

    private ProductBean getProductBean(String goodsName, String goodsNumber, String goodsBuyPrice, String goodsSalePrice) {
        ProductBean productBean = new ProductBean();
        productBean.setProductName(goodsName);
        productBean.setProductNumber(goodsNumber);
        productBean.setProductBuyPrice(goodsBuyPrice);
        productBean.setProductSalePrice(goodsSalePrice);
        productBean.setRemark(mRemark.getText().toString().trim());
        return productBean;
    }

    @Override
    public void showData(String msg) {
        showToast(msg);
        finish();
    }
}
