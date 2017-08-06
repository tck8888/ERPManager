package com.tck.erpmanager.ui.activity.warehouse_manager;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.commonlibrary.base.BaseData;
import com.tck.commonlibrary.common.CommonConstant;
import com.tck.commonlibrary.utils.AppSharePreferenceMgr;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.bean.WarehouseBean;
import com.tck.erpmanager.net.contract.WarehouseContract;
import com.tck.erpmanager.net.presenter.AddWarehousePresenterImpl;

import org.greenrobot.eventbus.EventBus;

/**
 * Description:新增仓库
 * <p>
 * Created by tck on 2017/8/4.
 */

public class AddWarehouseActivity extends BaseActivity implements View.OnClickListener, WarehouseContract.AddWarehouseView {

    private EditText warehoueseName;
    private EditText remark;
    private AddWarehousePresenterImpl mAddWarehousePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_warehouse;
    }

    @Override
    protected void initData() {
        mAddWarehousePresenter = new AddWarehousePresenterImpl(this);
    }

    @Override
    protected void initView() {


        findViewById(R.id.icon_back).setOnClickListener(this);
        findViewById(R.id.icon_add).setOnClickListener(this);


        warehoueseName = (EditText) findViewById(R.id.warehouese_name);
        remark = (EditText) findViewById(R.id.remark);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_add:
                addWarehouse();
                break;
        }
    }

    private void addWarehouse() {
        String warehoueseName = this.warehoueseName.getText().toString().trim();
        String remark = this.remark.getText().toString().trim();

        if (TextUtils.isEmpty(warehoueseName)) {
            showToast("仓库名称不能为空");
            return;
        }

        WarehouseBean warehouseBean = getWarehouseBean(warehoueseName, remark);

        mAddWarehousePresenter.addWarehouse(warehouseBean);
    }

    private WarehouseBean getWarehouseBean(String warehoueseName, String remark) {
        WarehouseBean warehouseBean = new WarehouseBean();
        warehouseBean.setRemark(remark);
        warehouseBean.setWarehouseName(warehoueseName);
        warehouseBean.setId((Integer) AppSharePreferenceMgr.get(this, CommonConstant.KEY_USER_ID, -1));
        return warehouseBean;
    }

    @Override
    public void showAddWarehouseSuccess(BaseData<String> infoBean) {
        if (infoBean != null) {
            showToast(infoBean.getMessage());
            if (infoBean.getStatus() == 200) {
                EventBus.getDefault().post(new MessageEvent<String>("AddWarehouseActivity", "succcess"));
                finish();
            }
        }
    }
}
