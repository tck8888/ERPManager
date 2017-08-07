package com.tck.erpmanager.ui.activity.account_manager;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.commonlibrary.common.CommonConstant;
import com.tck.commonlibrary.utils.AppSharePreferenceMgr;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.AccountListBean;
import com.tck.erpmanager.net.contract.AccountContract;
import com.tck.erpmanager.net.presenter.GetAccountListPresenterImpl;
import com.tck.erpmanager.ui.activity.account_manager.adapter.GetAccountListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 账户列表界面
 * Created by tck on 2017/8/6.
 */

public class GetAccountListActivity extends BaseActivity implements View.OnClickListener,
        AccountContract.GetAccountListView {

    private ListView listView;
    private List<AccountListBean.DataBean> mDataBeanList = new ArrayList<>();
    private GetAccountListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_acccount_list;
    }

    @Override
    protected void initData() {
        GetAccountListPresenterImpl getAccountListPresenter = new GetAccountListPresenterImpl(this);
        getAccountListPresenter.getAccountList((Integer) AppSharePreferenceMgr.get(this, CommonConstant.KEY_USER_ID, -1));
    }

    @Override
    protected void initView() {
        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new GetAccountListAdapter(this, mDataBeanList);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AccountListBean.DataBean dataBean = mDataBeanList.get(position);
                Intent intent = new Intent(GetAccountListActivity.this, GetAccountDetailActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_add:
                addPurchaseOrder();
                break;
        }
    }

    private void addPurchaseOrder() {
        Intent intent = new Intent(this, AddAccounActivity.class);
        startActivity(intent);
    }

    @Override
    public void showAccountList(AccountListBean accountListBean) {
        if (accountListBean != null) {
            showToast(accountListBean.getMessgae());
            if (accountListBean.getData() != null) {
                if (accountListBean.getData().size() > 0) {
                    if (mDataBeanList.size() > 0) {
                        mDataBeanList.clear();
                    }
                    mDataBeanList.addAll(accountListBean.getData());
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
