package com.tck.erpmanager.ui.activity.goods_manager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.utils.ImageLoadUtils;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.InfoBean;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.bean.ProductBean;
import com.tck.erpmanager.bean.ProductDetailBean;
import com.tck.erpmanager.net.contract.ProductContract;
import com.tck.erpmanager.net.contract.UpImageContract;
import com.tck.erpmanager.net.presenter.AddGoodsPresenterImpl;
import com.tck.erpmanager.net.presenter.GetGoodsDetailPresenterImpl;
import com.tck.erpmanager.net.presenter.UpImagePresenterImpl;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * 更新商品
 * Created by tck on 2017/6/26.
 */

public class UpdateGoodsActivity extends BaseActivity implements View.OnClickListener,
        ProductContract.GetGoodsDetailView,
        UpImageContract.UpImageView,
        ProductContract.AddGoodsView {

    private EditText mGoodsName;
    private EditText mGoodsBuyPrice;
    private EditText mRemark;
    private ImageView goodImage;

    private UpImagePresenterImpl mUpImagePresenter;
    private AddGoodsPresenterImpl mAddGoodsPresenter;
    /**
     * 商品id
     */
    private int mGoodsId;
    private String url = "";
    private static final int REQUEST_IMAGE = 2;
    private static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;

    private ArrayList<String> mSelectPath = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_goods;
    }

    @Override
    protected void initData() {
        /**
         * 上传商品图片
         */
        mUpImagePresenter = new UpImagePresenterImpl(this);
        mAddGoodsPresenter = new AddGoodsPresenterImpl(this);
        try {
            mGoodsId = getIntent().getIntExtra("goodsId", -1);
            /**
             * 获取商品详情
             */
            GetGoodsDetailPresenterImpl getGoodsDetailPresenter = new GetGoodsDetailPresenterImpl(this);
            getGoodsDetailPresenter.getGoodsDetail(mGoodsId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {
        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);
        findViewById(R.id.select_image).setOnClickListener(this);

        mGoodsName = (EditText) findViewById(R.id.goods_name);
        mGoodsBuyPrice = (EditText) findViewById(R.id.goods_buy_price);
        goodImage = (ImageView) findViewById(R.id.good_image);
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
            case R.id.select_image:
                pickImage();
                break;
        }
    }

    private void saveProduct() {
        String goodsName = mGoodsName.getText().toString().trim();
        String goodsBuyPrice = mGoodsBuyPrice.getText().toString().trim();


        if (TextUtils.isEmpty(goodsName)) {
            showToast("商品名称不能为空");
            return;
        }

        if (TextUtils.isEmpty(goodsBuyPrice)) {
            showToast("商品采购价不能为空");
            return;
        }

        if (TextUtils.isEmpty(url)) {
            showToast("商品图片不能为空");
            return;
        }


        mAddGoodsPresenter.addGoods(getProductBean(goodsName, url, goodsBuyPrice), "update");
    }

    private ProductBean getProductBean(String goodsName, String imageUrl, String goodsBuyPrice) {
        ProductBean productBean = new ProductBean();
        productBean.setProductId(mGoodsId);
        productBean.setProductName(goodsName);
        productBean.setImageUrl(imageUrl);
        productBean.setProductBuyPrice(goodsBuyPrice);
        productBean.setRemark(mRemark.getText().toString().trim());
        return productBean;
    }

    @Override
    public void showData(ProductDetailBean productDetailBean) {

        if (productDetailBean != null) {
            showToast(productDetailBean.getMessgae());
            if (productDetailBean.getStatus() == 200) {
                if (productDetailBean.getData() != null) {
                    setViewData(productDetailBean.getData());
                }
            }
        }
    }

    private void setViewData(ProductDetailBean.DataBean data) {
        mGoodsName.setText(data.getProductName());
        mGoodsBuyPrice.setText("" + data.getProductPrice());
        mRemark.setText(data.getRemark());
        url = data.getProductImage();
        ImageLoadUtils.getInstance().load(this, goodImage, url);
    }

    /**
     * 上传附件
     */
    private void pickImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            MultiImageSelector.create(this)
                    .showCamera(true) // show camera or not. true by
                    .count(1)
                    .single() // single mode
                    .multi()
                    .origin(mSelectPath) // original select data set, used width #.multi()
                    .start(this, REQUEST_IMAGE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED ||
                    grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                showToast("获取权限失败");
                return;
            }
            pickImage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                if (mSelectPath.size() != 0) {
                    mSelectPath.clear();
                }
                mSelectPath.addAll(data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT));
                mUpImagePresenter.upImage(new File(mSelectPath.get(0)));
            }
        }
    }

    @Override
    public void showSuccess(InfoBean str) {
        if (str != null) {
            showToast(str.getMessage());
            if (str.getStatus() == 200) {
                url = str.getData();
                ImageLoadUtils.getInstance().load(this, goodImage, url);
            }
        }
    }

    @Override
    public void showData(BaseData<String> msg) {
        if (msg != null) {
            showToast(msg.getMessage());
            if (msg.getStatus() == 200) {
                finish();
                EventBus.getDefault().post(new MessageEvent<String>("UpdateGoodsActivity", "success"));
            }
        }
    }
}
