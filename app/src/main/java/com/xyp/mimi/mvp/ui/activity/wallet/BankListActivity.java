package com.xyp.mimi.mvp.ui.activity.wallet;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.github.baseclass.view.MyDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.AddBankPost;
import com.xyp.mimi.mvp.http.entity.wallet.DeleteMyBankCardPost;
import com.xyp.mimi.mvp.http.entity.wallet.MyBankListResult;
import com.xyp.mimi.mvp.presenter.wallet.MyBankListPresenter;
import com.xyp.mimi.mvp.ui.adapter.wallet.MyBankListAdapter;
import com.xyp.mimi.mvp.utils.AppConstant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class BankListActivity extends BaseSupportActivity<MyBankListPresenter> implements BankContract.MyBankCardView {

    @BindView(R.id.app_title)
    TextView appTitle;

    @BindView(R.id.rv_bank)
    RecyclerView rvBank;

    private String userId;
    private String token;

    MyBankListAdapter myBankListAdapter;
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
//        DaggerBankComponent.builder()
//                .appComponent(appComponent)
//                .bankModule(new BankModule(this))
//                .build()
//                .injectBankList(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_banklist;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        appTitle.setText("我的银行卡");
        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
        mPresenter.getMyBankCard(new AddBankPost(userId, token));

        myBankListAdapter = new MyBankListAdapter(mContext, 0);
        rvBank.setLayoutManager(new LinearLayoutManager(mContext));
        rvBank.addItemDecoration(getItemDivider(ConvertUtils.dp2px(1)));
        rvBank.setAdapter(myBankListAdapter);
        myBankListAdapter.setOnBankDeleteListener(new MyBankListAdapter.OnClickBankDeleteListener() {
            @Override
            public void deleteBank(int id) {
                MyDialog.Builder mDialog = new MyDialog.Builder(mContext);
                mDialog.setMessage("是否确认删除?");
                mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mPresenter.deleteMyBankCard(new DeleteMyBankCardPost(userId,token,id));
                    }
                });
                mDialog.create().show();
            }
        });
    }


    @OnClick({R.id.tv_bank_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_bank_add:
                STActivity(AddBankCardActivity.class);
                break;
        }
    }


    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void getMyBankCardResult(MyBankListResult myBankListResult) {
        showLoadSuccess();
        myBankListAdapter.setList(myBankListResult.getData(),true);

    }

    @Override
    public void getDeleteBankCardResult(BaseResponse baseResponse) {

        showLoadSuccess();
    }

}
