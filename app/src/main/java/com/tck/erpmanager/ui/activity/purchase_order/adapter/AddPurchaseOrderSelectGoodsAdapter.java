package com.tck.erpmanager.ui.activity.purchase_order.adapter;

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
 * Created by tck on 2017/8/8.
 */

public class AddPurchaseOrderSelectGoodsAdapter extends BasicAdapter<ProductListBean.DataBean> {

    public AddPurchaseOrderSelectGoodsAdapter(Context context, List<ProductListBean.DataBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void onInitView(View convertView, int position) {


        ImageView unselectGoodsIv = get(convertView, R.id.unselect_goods_iv);
        ImageView selectGoodsIv = get(convertView, R.id.select_goods_iv);
        ImageView goodImage = get(convertView, R.id.good_image);
        TextView goodsName = get(convertView, R.id.goods_name);
        TextView goodsNumber = get(convertView, R.id.goods_number);
        TextView goodsBuyPrice = get(convertView, R.id.goods_buy_price);
        TextView goodsSalePrice = get(convertView, R.id.goods_sale_price);

        ProductListBean.DataBean dataBean = mDataList.get(position);
        goodsName.setText(dataBean.getProductName());

        ImageLoadUtils.getInstance().load(mContext, goodImage, dataBean.getProductImage());
        goodsBuyPrice.setText(dataBean.getProductPrice() + "");

        if (dataBean.isSelected()) {
            unselectGoodsIv.setVisibility(View.GONE);
            selectGoodsIv.setVisibility(View.VISIBLE);
        } else {
            unselectGoodsIv.setVisibility(View.VISIBLE);
            selectGoodsIv.setVisibility(View.GONE);
        }

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_add_purchase_order_select_goods_item;
    }
}
