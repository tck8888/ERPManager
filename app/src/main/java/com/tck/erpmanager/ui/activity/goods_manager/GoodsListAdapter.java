package com.tck.erpmanager.ui.activity.goods_manager;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.tck.commonlibrary.base.BasicAdapter;
import com.tck.commonlibrary.utils.ImageLoadUtils;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.ProductListBean;

import java.util.List;

/**
 * Created by tck on 2017/6/26.
 */

public class GoodsListAdapter extends BasicAdapter<ProductListBean.DataBean> {

    public GoodsListAdapter(Context context, List<ProductListBean.DataBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void onInitView(View convertView, int position) {

        ImageView mGoodImage = get(convertView, R.id.good_image);
        TextView mGoodsName = get(convertView, R.id.goods_name);
        TextView mGoodsNumber = get(convertView, R.id.goods_number);
        TextView mGoodsBuyPrice = get(convertView, R.id.goods_buy_price);
        TextView mGoodsSalePrice = get(convertView, R.id.goods_sale_price);

        ProductListBean.DataBean dataBean = mDataList.get(position);
        ImageLoadUtils.getInstance().load(mContext,mGoodImage,dataBean.getProductImage());
        mGoodsName.setText(dataBean.getProductName());
        mGoodsNumber.setText(dataBean.getId()+"");
        mGoodsBuyPrice.setText(dataBean.getProductPrice()+"");
    }

    @Override
    protected int getContentView() {
        return R.layout.goods_list_item;
    }
}
