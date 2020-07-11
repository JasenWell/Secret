package com.xyp.mimi.mvp.ui.activity.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.AddBankPost;
import com.xyp.mimi.mvp.http.entity.wallet.CodePost;
import com.xyp.mimi.mvp.presenter.wallet.AddBankPresenter;
import com.xyp.mimi.mvp.utils.AppConstant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class AddBankCardActivity extends BaseSupportActivity<AddBankPresenter> implements BankContract.AddBankView {

    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.et_name)
    MyEditText etName;
    @BindView(R.id.rl_bank)
    RelativeLayout rlBank;
    @BindView(R.id.et_bankNumber)
    MyEditText etBankNumber;
    @BindView(R.id.et_bankName)
    MyEditText etBankName;
    @BindView(R.id.et_phone)
    MyEditText etPhone;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.et_code)
    MyEditText etCode;
    @BindView(R.id.tv_commit)
    MyTextView tvCommit;
    @BindView(R.id.et_bankBigName)
    TextView etBankBigName;
    private String userId ;
    private String token ;
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
//                .injectAddBankCard(AddBankCardActivity.this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_bank_add;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        appTitle.setText("添加银行卡");
         userId =  SPUtils.getInstance().getString(AppConstant.User.USER_ID);
         token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
    }

    @OnClick({R.id.tv_commit,R.id.rl_bank,R.id.tv_getcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_bank:
                STActivityForResult(SelelctBankActivity.class,100);

                break;
            case R.id.tv_getcode:
                String phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    ArmsUtils.snackbarText("请输入手机号码");
                    return;
                }

                mPresenter.getCode(new CodePost(userId,token,phone));
                break;
            case R.id.tv_commit:
                commit();
                break;
        }
    }

    private void commit() {
        DeviceUtils.hideSoftKeyboard(mContext, getCurrentFocus());
        String name = etName.getText().toString();
        String bankBigName = etBankBigName.getText().toString();
        String bankNumber = etBankNumber.getText().toString();
        String bankName = etBankName.getText().toString();
        String phone = etPhone.getText().toString();
        String code = etCode.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ArmsUtils.snackbarText("请输入持卡人姓名");
            return;
        }
        if (TextUtils.isEmpty(bankBigName)) {
            ArmsUtils.snackbarText("请选择银行名称");
            return;
        }
        if (TextUtils.isEmpty(bankNumber)) {
            ArmsUtils.snackbarText("请输入银行卡号");
            return;
        }
        if (TextUtils.isEmpty(bankName)) {
            ArmsUtils.snackbarText("请输入支行名称");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            ArmsUtils.snackbarText("请输入手机号码");
            return;
        }if (TextUtils.isEmpty(code)) {
            ArmsUtils.snackbarText("请输入验证码");
            return;
        }
        if (mPresenter != null) {
            mPresenter.addBank(new AddBankPost(userId,token,name,bankBigName,bankNumber,bankName,phone,code));
        }
    }

    @Override
    public void getAddBankResult(BaseResponse baseResponse) {
        showLoadSuccess();
    }

    @Override
    public void getCodeResult(BaseResponse baseResponse) {
          showLoadSuccess();
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 100:
                    String name =  data.getStringExtra("BANK_NAME");
                    etBankBigName.setText(name);
                    break;
            }
        }
    }
}
