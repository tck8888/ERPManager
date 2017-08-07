package com.tck.erpmanager.ui.activity.account_manager;

import android.view.View;
import android.widget.TextView;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.AccountDetailBean;
import com.tck.erpmanager.net.contract.AccountContract;
import com.tck.erpmanager.net.presenter.GetAccountDetailPresenterImpl;

/**
 * Created by tck on 2017/8/6.
 */

public class GetAccountDetailActivity extends BaseActivity implements View.OnClickListener, AccountContract.GetAccountDetailView {


    private TextView accountName;
    private TextView remark;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_account_detail;
    }

    @Override
    protected void initData() {
        try {
            int accountId = getIntent().getIntExtra("accountId", -1);
            GetAccountDetailPresenterImpl getAccountDetailPresenter = new GetAccountDetailPresenterImpl(this);
            getAccountDetailPresenter.getAccountDetail(accountId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {

        findViewById(R.id.icon_back).setOnClickListener(this);

        accountName = (TextView) findViewById(R.id.account_name);
        remark = (TextView) findViewById(R.id.remark);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
        }
    }

    @Override
    public void showAccountDeatilBean(AccountDetailBean accountDetailBean) {
        if (accountDetailBean != null) {
            showToast(accountDetailBean.getMessgae());
            if (accountDetailBean.getData() != null) {
                setViewData(accountDetailBean.getData());
            }
        }
    }

    private void setViewData(AccountDetailBean.DataBean data) {
        accountName.setText(data.getAccountName());
        remark.setText(data.getRemark());
    }
}
