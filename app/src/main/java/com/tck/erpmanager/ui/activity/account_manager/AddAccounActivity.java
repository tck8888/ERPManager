package com.tck.erpmanager.ui.activity.account_manager;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.common.CommonConstant;
import com.tck.commonlibrary.utils.AppSharePreferenceMgr;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.AccountBean;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.net.contract.AccountContract;
import com.tck.erpmanager.net.presenter.AddAccountPresenterImpl;

import org.greenrobot.eventbus.EventBus;

/**
 * 新增账户
 * Created by tck on 2017/8/6.
 */

public class AddAccounActivity extends BaseActivity implements View.OnClickListener,
        AccountContract.AddAccountView {

    private EditText accountName;
    private EditText remark;
    private AddAccountPresenterImpl mAddAccountPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_account;
    }

    @Override
    protected void initData() {
        mAddAccountPresenter = new AddAccountPresenterImpl(this);
    }

    @Override
    protected void initView() {

        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);

        accountName = (EditText) findViewById(R.id.account_name);
        remark = (EditText) findViewById(R.id.remark);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_add:
                addAccount();
                break;
        }
    }

    private void addAccount() {
        String accountName = this.accountName.getText().toString().trim();
        String remark = this.remark.getText().toString().trim();
        if (TextUtils.isEmpty(accountName)) {
            showToast("账户名称不能为空");
            return;
        }

        mAddAccountPresenter.addAccount(getAccountBean(accountName, remark));
    }

    private AccountBean getAccountBean(String accountName, String remark) {
        AccountBean accountBean = new AccountBean();
        accountBean.setAccountName(accountName);
        accountBean.setRemark(remark);
        accountBean.setUserId((Integer) AppSharePreferenceMgr.get(this, CommonConstant.KEY_USER_ID, -1));
        return accountBean;
    }

    @Override
    public void showAddAccountSuccess(BaseData<String> data) {
        if (data != null) {
            showToast(data.getMessage());
            if (data.getStatus() == 200) {
                EventBus.getDefault().post(new MessageEvent<String>("AddAccounActivity", "success"));
                finish();
            }
        }
    }
}
