package com.tck.erpmanager.ui.activity.account_manager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tck.commonlibrary.base.BasicAdapter;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.AccountListBean;

import java.util.List;

/**
 * Created by tck on 2017/8/6.
 */

public class GetAccountListAdapter extends BasicAdapter<AccountListBean.DataBean> {

    public GetAccountListAdapter(Context context, List<AccountListBean.DataBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void onInitView(View convertView, int position) {

        TextView accountName = get(convertView, R.id.account_name);

        AccountListBean.DataBean dataBean = mDataList.get(position);
        accountName.setText(dataBean.getAccountName());
    }

    @Override
    protected int getContentView() {
        return R.layout.account_list_item;
    }
}
