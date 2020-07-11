package com.xyp.mimi.mvp.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.xyp.mimi.mvp.presenter.GuaPresenter;
import com.xyp.mimi.mvp.utils.AppConstant;

import butterknife.BindView;
import butterknife.OnClick;

public class USDTBuyAndGuaFragment extends BaseSupportFragment<GuaPresenter> implements YueAndUsdtContract.ViewUsdt {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.usdt_addres)
    MyEditText usdtAddres;
    @BindView(R.id.et_member)
    MyEditText etMember;
    @BindView(R.id.et_id)
    MyEditText etId;
    @BindView(R.id.et_money)
    MyEditText etMoney;

    public static USDTBuyAndGuaFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putSerializable(AppConstant.Api.TYPE, type);
        USDTBuyAndGuaFragment fragment = new USDTBuyAndGuaFragment();
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
        return inflater.inflate(R.layout.fragment_usdt_buy, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


    @OnClick({R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                chongzhiCommit();
                break;
        }
    }


    public void chongzhiCommit() {

        String address = usdtAddres.getText().toString();
        String member = etMember.getText().toString();
        String id = etId.getText().toString();
        String money = etMoney.getText().toString();
        if (TextUtils.isEmpty(address)) {
            showMessage("地址不能为空");
            return;
        }
        if (TextUtils.isEmpty(member)) {
            showMessage("会员不能为空");
            return;
        }
        if (TextUtils.isEmpty(id)) {
            showMessage("交易ID不能为空");
            return;
        }
        if (TextUtils.isEmpty(money)) {
            showMessage("应付金额不能为空");
            return;
        }
        DeviceUtils.hideSoftKeyboard(getActivity(), getActivity().getLayoutInflater().inflate(R.layout.fragment_yue_buy, null));
        if (mPresenter != null) {
            String useId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
            mPresenter.usdt(useId,money,member,address,id);
        }
    }

    @Override
    public void setData(@Nullable Object data) {

    }


    @Override
    public void showMessage(@NonNull String message) {
        showLoadSuccess();
        ArmsUtils.snackbarText(message);

    }

    @Override
    public void usdtSuccess(String s) {

    }

    @Override
    public void guaUSDTSuccess(String s) {

    }


}
