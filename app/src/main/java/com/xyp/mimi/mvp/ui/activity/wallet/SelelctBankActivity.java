package com.xyp.mimi.mvp.ui.activity.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.wallet.DaggerBankComponent;
import com.xyp.mimi.di.module.wallet.BankModule;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.entity.wallet.BankInfoListResult;
import com.xyp.mimi.mvp.presenter.wallet.BankPresenter;
import com.xyp.mimi.mvp.ui.adapter.wallet.BankListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SelelctBankActivity extends BaseSupportActivity<BankPresenter> implements BankContract.BankInfoListView {

    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.rv_get_vouchers)
    RecyclerView rvGetVouchers;

    BankListAdapter bankListAdapter;

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .keyboardEnable(false)
                .init();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBankComponent.builder()
                .appComponent(appComponent)
                .bankModule(new BankModule(SelelctBankActivity.this))
                .build().injectSelelctBank(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_bank_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        bankListAdapter = new BankListAdapter(mContext, 0);
        appTitle.setText("选择银行名称");
        rvGetVouchers.setLayoutManager(new LinearLayoutManager(mContext));
        rvGetVouchers.addItemDecoration(getItemDivider(ConvertUtils.dp2px(1)));
        rvGetVouchers.setAdapter(bankListAdapter);
        mPresenter.getBankInfoList();
        bankListAdapter.setOnBankListener(new BankListAdapter.OnClickBankListener() {
            @Override
            public void onClick(String s) {
                Intent intent=new Intent();
                intent.putExtra("BANK_NAME",s);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
    }


    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void getBankInfoListResult(BankInfoListResult bankInfoListResult) {
        showLoadSuccess();
        bankListAdapter.setList(bankInfoListResult.getData(),true);
    }

}
