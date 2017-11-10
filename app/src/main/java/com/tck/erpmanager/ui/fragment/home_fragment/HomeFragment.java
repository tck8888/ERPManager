package com.tck.erpmanager.ui.fragment.home_fragment;

import android.content.Intent;
import android.view.View;

import com.tck.commonlibrary.base.BaseFragment;
import com.tck.erpmanager.R;
import com.tck.erpmanager.ui.activity.account_manager.GetAccountListActivity;
import com.tck.erpmanager.ui.activity.goods_manager.GetGoodsListActivity;
import com.tck.erpmanager.ui.activity.purchase_order.GetPurchaseOrderListActivity;
import com.tck.erpmanager.ui.activity.sale_order.GetSaleOrderListActivity;
import com.tck.erpmanager.ui.activity.warehouse_manager.GetWarehouseListActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tck on 2017/6/24.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private Banner mBanner;
    private List<String> mStringList = new ArrayList<>();


   /* private String url = "http://tck.oss-cn-shanghai.aliyuncs.com/image/IMG_20160407_200713.jpg";*/

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
     /*   mStringList.add(url);
        mStringList.add(url);
        mStringList.add(url);
        mStringList.add(url);*/
    }

    @Override
    protected void initView(View view) {
     /*   mBanner = (Banner) view.findViewById(R.id.banner);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(mStringList);
        //设置banner动画效果
        //banner设置方法全部调用完毕时最后调用
        mBanner.setDelayTime(3000);
        mBanner.start();*/

        view.findViewById(R.id.goods_manager).setOnClickListener(this);
        view.findViewById(R.id.buy_order).setOnClickListener(this);
        view.findViewById(R.id.sale_order).setOnClickListener(this);
        view.findViewById(R.id.stock_query).setOnClickListener(this);
        view.findViewById(R.id.account_manager).setOnClickListener(this);


    }


    @Override
    public void onStart() {
        super.onStart();
      /*  //开始轮播
        mBanner.startAutoPlay();*/
    }

    @Override
    public void onStop() {
        super.onStop();
      /*  //结束轮播
        mBanner.stopAutoPlay();*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 商品管理
             */
            case R.id.goods_manager:
                startActivity(new Intent(getContext(), GetGoodsListActivity.class));
                break;
            /**
             * 采购单
             */
            case R.id.buy_order:
                startActivity(new Intent(getContext(), GetPurchaseOrderListActivity.class));
                break;
            /**
             * 销售单
             */
            case R.id.sale_order:
                startActivity(new Intent(getContext(), GetSaleOrderListActivity.class));
                break;
            /**
             * 库存查询
             */
            case R.id.stock_query:
                startActivity(new Intent(getContext(), GetWarehouseListActivity.class));
                break;
            /**
             * 账户管理
             */
            case R.id.account_manager:
                startActivity(new Intent(getContext(), GetAccountListActivity.class));
                break;
        }
    }
}
