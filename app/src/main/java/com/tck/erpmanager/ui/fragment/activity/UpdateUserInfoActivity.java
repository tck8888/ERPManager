package com.tck.erpmanager.ui.fragment.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tck.commonlibrary.base.BaseActivity;
import com.tck.commonlibrary.base.BaseData;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.net.contract.LoginAndRegisterContract;
import com.tck.erpmanager.net.presenter.UpdateUserPresenterImpl;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by tck on 2017/11/11.
 */

public class UpdateUserInfoActivity extends BaseActivity implements LoginAndRegisterContract.UpdateUserView, View.OnClickListener {


    private UpdateUserPresenterImpl mUpdateUserPresenter;
    private String mTitle = "";
    private String mType = "";

    private TextView titleTv;
    private TextView subTitle;
    private EditText contentEt;
    private String mNickname = "";
    private String mEmail = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_user_info;
    }

    @Override
    protected void initData() {
        mUpdateUserPresenter = new UpdateUserPresenterImpl(this);
        try {
            mTitle = getIntent().getStringExtra("title");
            mType = getIntent().getStringExtra("type");
            mNickname = getIntent().getStringExtra("nickname");
            mEmail = getIntent().getStringExtra("email");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initView() {

        findViewById(R.id.icon_back).setOnClickListener(this);
        titleTv = (TextView) findViewById(R.id.title_tv);
        findViewById(R.id.icon_add).setOnClickListener(this);
        subTitle = (TextView) findViewById(R.id.sub_title);
        contentEt = (EditText) findViewById(R.id.content_et);
        titleTv.setText(mTitle);
        if (mType.equals("nickname")) {
            subTitle.setText("昵称");
            contentEt.setText(mNickname);
        } else if (mType.equals("email")) {
            subTitle.setText("邮箱");
            contentEt.setText(mEmail);
        }

    }

    @Override
    public void showData(BaseData<String> data) {
        if (data != null) {
            showToast(data.getMessage());
            if (data.getStatus() == 200) {
                EventBus.getDefault().post(new MessageEvent<String>("UpdateUserInfoActivity", "success"));
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_add:
                if (TextUtils.isEmpty(contentEt.getText().toString().trim())) {
                    showToast("输入不能为空");
                    return;
                }
                mUpdateUserPresenter.update(contentEt.getText().toString().trim(), mType);
                break;
        }
    }
}
