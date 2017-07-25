package com.tck.erpmanager.ui.activity.goods_manager;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.tck.commonlibrary.base.BasicAdapter;
import com.tck.erpmanager.R;

import java.util.List;

/**
 * Created by tck on 2017/6/26.
 */

public class GoodsListAdapter extends BasicAdapter<String> {

    public GoodsListAdapter(Context context, List<String> dataList) {
        super(context, dataList);
    }

    @Override
    protected void onInitView(View convertView, int position) {

        ImageView mGoodImage = get(convertView, R.id.good_image);
        TextView mGoodsName = get(convertView, R.id.goods_name);
        TextView mGoodsNumber = get(convertView, R.id.goods_number);
        TextView mGoodsBuyPrice = get(convertView, R.id.goods_buy_price);
        TextView mGoodsSalePrice = get(convertView, R.id.goods_sale_price);

    }

    @Override
    protected int getContentView() {
        return R.layout.goods_list_item;
    }
}
