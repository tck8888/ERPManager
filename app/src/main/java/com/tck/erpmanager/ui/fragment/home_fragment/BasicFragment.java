package com.tck.erpmanager.ui.fragment.home_fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tck.commonlibrary.base.BaseFragment;
import com.tck.commonlibrary.common.CommonConstant;
import com.tck.commonlibrary.utils.AppSharePreferenceMgr;
import com.tck.commonlibrary.widget.LoadingDialog;
import com.tck.erpmanager.R;
import com.tck.erpmanager.bean.MessageEvent;
import com.tck.erpmanager.bean.User;
import com.tck.erpmanager.net.contract.LoginAndRegisterContract;
import com.tck.erpmanager.net.presenter.FindUserPresenterImpl;
import com.tck.erpmanager.ui.fragment.activity.UpdateUserInfoActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by tck on 2017/6/24.
 */

public class BasicFragment extends BaseFragment implements View.OnClickListener, LoginAndRegisterContract.FindUserView {

    private LoadingDialog mLoadingDialog;

    private TextView nicknameTv;
    private TextView emailTv;
    //private TextView balanceTv;
    private FindUserPresenterImpl mFindUserPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_basic;
    }

    @Override
    protected void initData() {
        mLoadingDialog = new LoadingDialog(getContext());
        mFindUserPresenter = new FindUserPresenterImpl(this);
        mFindUserPresenter.findUserById((Integer) AppSharePreferenceMgr.get(getContext(), CommonConstant.KEY_USER_ID, -1));
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.select_nickname).setOnClickListener(this);
        view.findViewById(R.id.select_email).setOnClickListener(this);
        nicknameTv = (TextView) view.findViewById(R.id.nickname_tv);
        emailTv = (TextView) view.findViewById(R.id.email_tv);
        //balanceTv = (TextView) view.findViewById(R.id.balance_tv);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_nickname:
                openActivity("修改昵称", "nickname");
                break;
            case R.id.select_email:
                openActivity("修改邮箱", "email");
                break;
        }
    }

    public void openActivity(String title, String type) {
        Intent intent = new Intent(getContext(), UpdateUserInfoActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        intent.putExtra("nickname",nicknameTv.getText().toString().trim());
        intent.putExtra("email",emailTv.getText().toString().trim());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent<String> event) {
        if (event.getTag().equals("UpdateUserInfoActivity")) {
            mFindUserPresenter.findUserById((Integer) AppSharePreferenceMgr.get(getContext(), CommonConstant.KEY_USER_ID, -1));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void showLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void dimissloading() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void showError(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showData(User user) {
        if (user != null) {
            if (user.getData() != null) {
                nicknameTv.setText(user.getData().getNickName());
                emailTv.setText(user.getData().getEmail());
               //balanceTv.setText(user.getData().getNickName());
            }
        }
    }
}
