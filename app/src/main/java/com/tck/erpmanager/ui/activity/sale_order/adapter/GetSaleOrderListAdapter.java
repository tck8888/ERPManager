package com.tck.erpmanager.ui.activity.sale_order.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tck.commonlibrary.base.BasicAdapter;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.SaleOrderListBean;

import java.util.List;

/**
 * Created by tck on 2017/8/6.
 */

public class GetSaleOrderListAdapter extends BasicAdapter<SaleOrderListBean.DataBean> {

    public GetSaleOrderListAdapter(Context context, List<SaleOrderListBean.DataBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void onInitView(View convertView, int position) {
        SaleOrderListBean.DataBean dataBean = mDataList.get(position);
        TextView purchaseOrderId = get(convertView, R.id.purchase_order_id);
        TextView purchaseOrderPrice = get(convertView, R.id.purchase_order_price);
        TextView purchaseOrderTime = get(convertView, R.id.purchase_order_time);
        purchaseOrderId.setText("订单号: " + dataBean.getId());
        purchaseOrderPrice.setText("订单总价: " + dataBean.getTotalPrice());
        purchaseOrderTime.setText(dataBean.getDate());
    }

    @Override
    protected int getContentView() {
        return R.layout.get_purchase_order_list_item;
    }
}
