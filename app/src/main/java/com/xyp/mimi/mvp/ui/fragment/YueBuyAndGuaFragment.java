package com.xyp.mimi.mvp.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.github.customview.MyEditText;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportFragment;
import com.xyp.mimi.mvp.contract.YueAndUsdtContract;
import com.xyp.mimi.mvp.http.entity.chongzhi.Chongzhi;
import com.xyp.mimi.mvp.presenter.YuePresenter;
import com.xyp.mimi.mvp.utils.AppConstant;

import butterknife.BindView;
import butterknife.OnClick;

public class YueBuyAndGuaFragment extends BaseSupportFragment<YuePresenter> implements YueAndUsdtContract.ViewYue {

    @BindView(R.id.et_number)
    MyEditText etNumber;
    @BindView(R.id.et_account)
    MyEditText etAccount;
    @BindView(R.id.et_password)
    MyEditText etPassword;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.ll_yueChong)
    LinearLayout llYueChong;


    @BindView(R.id.btn_gua_commit)
    Button btnGuaCommit;
    @BindView(R.id.ll_yueGua)
    LinearLayout llYueGua;
    @BindView(R.id.et_gua_zhanghao)
    MyEditText etGuaZhanghao;
    @BindView(R.id.et_gua_number)
    MyEditText etGuaNumber;
    @BindView(R.id.et_gua_google)
    MyEditText etGuaGoogle;
    @BindView(R.id.et_gua_password)
    MyEditText etGuaPassword;

    private String type;
    public static YueBuyAndGuaFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putSerializable(AppConstant.Api.TYPE, type);
        YueBuyAndGuaFragment fragment = new YueBuyAndGuaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
//        DaggerYueAndUsdtComponent.builder()
//                .appComponent(appComponent)
//                .yueAndUsdtModule(new YueAndUsdtModule(this))
//                .build()
//                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yue_buy, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        type = getArguments().getString(AppConstant.Api.TYPE);
        if (type.equals("Title")) {
            llYueChong.setVisibility(View.GONE);
            llYueGua.setVisibility(View.VISIBLE);
        } else {
            llYueChong.setVisibility(View.VISIBLE);
            llYueGua.setVisibility(View.GONE);
        }

    }


    @OnClick({R.id.btn_commit, R.id.btn_gua_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                chongzhiCommit();
                break;
            case R.id.btn_gua_commit:
                guaCommit();
                break;
        }
    }

    private void guaCommit() {
        String guaZhanghao = etGuaZhanghao.getText().toString();
        String guaNumber = etGuaNumber.getText().toString();
        String guaGoogle = etGuaGoogle.getText().toString();
        String guaPassword =etGuaPassword.getText().toString();

        if (TextUtils.isEmpty(guaZhanghao)) {
            showMessage("挂靠不能为空");
            return;
        }
        if (TextUtils.isEmpty(guaNumber)) {
            showMessage("网站个数不能为空");
            return;
        }
        if (TextUtils.isEmpty(guaGoogle)) {
            showMessage("账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(guaPassword)) {
            showMessage("密码不能为空");
            return;
        }

        DeviceUtils.hideSoftKeyboard(getActivity(), getActivity().getLayoutInflater().inflate(R.layout.fragment_yue_buy, null));
        if (mPresenter != null) {
            String useId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
            mPresenter.guaYue(useId,guaNumber,guaZhanghao,guaGoogle,guaPassword);
        }

    }


    public void chongzhiCommit() {
        String number = etNumber.getText().toString();
        String account = etAccount.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(number)) {
            showMessage("会员不能为空");
            return;
        }
        if (TextUtils.isEmpty(account)) {
            showMessage("账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showMessage("密码不能为空");
            return;
        }
        DeviceUtils.hideSoftKeyboard(getActivity(), getActivity().getLayoutInflater().inflate(R.layout.fragment_yue_buy, null));
        if (mPresenter != null) {
            String useId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
            mPresenter.yue(useId, number, account, password);
        }
    }

    @Override
    public void setData(@Nullable Object data) {

    }





    @Override
    public void yueSuccess(String s) {
        showLoadSuccess();
        showLoadSuccess();
        etNumber.setText("");
        etAccount.setText("");
        etPassword.setText("");
    }

    @Override
    public void guaYueSuccess(String s) {
        showLoadSuccess();

    }

    @Override
    public void getYueSuccess(Chongzhi c) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        showLoadSuccess();
        ArmsUtils.snackbarText(message);

    }
}
