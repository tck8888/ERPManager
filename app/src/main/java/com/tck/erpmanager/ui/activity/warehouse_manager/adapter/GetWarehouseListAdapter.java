package com.tck.erpmanager.ui.activity.warehouse_manager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tck.commonlibrary.base.BasicAdapter;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.WarehouseListBean;

import java.util.List;

/**
 * Description:
 * <p>
 * Created by tck on 2017/8/4.
 */

public class GetWarehouseListAdapter extends BasicAdapter<WarehouseListBean.DataBean> {

    public GetWarehouseListAdapter(Context context, List<WarehouseListBean.DataBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void onInitView(View convertView, int position) {

        TextView warehoueseWame = get(convertView, R.id.warehouese_name);
        WarehouseListBean.DataBean dataBean = mDataList.get(position);
        warehoueseWame.setText(dataBean.getProductName());
    }

    @Override
    protected int getContentView() {
        return R.layout.warehouse_list_item;
    }
}
