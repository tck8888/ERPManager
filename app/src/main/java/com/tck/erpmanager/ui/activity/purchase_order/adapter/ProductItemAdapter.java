package com.tck.erpmanager.ui.activity.purchase_order.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tck.commonlibrary.base.BasicAdapter;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.ProductListBean;

import java.util.List;

/**
 * Description:
 * <p>
 * Created by tck on 2017/8/9.
 */

public class ProductItemAdapter extends BasicAdapter<ProductListBean.DataBean> {


    public ProductItemAdapter(Context context, List<ProductListBean.DataBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void onInitView(View convertView, int position) {

        TextView productName = get(convertView, R.id.product_name);
        TextView productPrice = get(convertView, R.id.product_price);
        ImageView decreaseProduct = get(convertView, R.id.decrease_product);
        TextView productCount = get(convertView, R.id.product_count);
        ImageView increaseProduct = get(convertView, R.id.increase_product);

        ProductListBean.DataBean dataBean = mDataList.get(position);

        productName.setText(dataBean.getProductName());
        productPrice.setText(dataBean.getProductPrice() + "");
        productCount.setText(dataBean.getCount() + "");

    }

    @Override
    protected int getContentView() {
        return R.layout.purchase_product_item;
    }
}
