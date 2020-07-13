package com.xyp.mimi.mvp.ui.activity.invoice;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.suke.widget.SwitchButton;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.module.invoice.InvoiceModule;
import com.xyp.mimi.mvp.contract.invoice.InvoiceContract;
import com.xyp.mimi.mvp.event.RefreshInvoiceEvent;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceAddPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceDeletePost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceEditorPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceInfoResult;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceListResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.presenter.invoice.InvoiceAddAndEditorPresenter;
import com.xyp.mimi.mvp.utils.AppConstant;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class InvoiceAddAndEditorActivity extends BaseSupportActivity<InvoiceAddAndEditorPresenter> implements InvoiceContract.InvoiceAddAndEditorView {
    @BindView(R.id.rb_geren)
    RadioButton rbGeren;
    @BindView(R.id.rb_company)
    RadioButton rbCompany;
    @BindView(R.id.rg_fapiao_type)
    RadioGroup rgFapiaoType;
    @BindView(R.id.et_invoice_title)
    MyEditText etInvoiceTitle;
    @BindView(R.id.et_geren_phone)
    MyEditText etGerenPhone;
    @BindView(R.id.sb_invoice_default)
    SwitchButton sbInvoiceDefault;
    @BindView(R.id.ll_geren)
    LinearLayout llGeren;
    @BindView(R.id.et_company_invoice_title)
    MyEditText etCompanyInvoiceTitle;
    @BindView(R.id.et_company_invoice_code)
    MyEditText etCompanyInvoiceCode;
    @BindView(R.id.et_address)
    MyEditText etAddress;
    @BindView(R.id.et_company_phone)
    MyEditText etCompanyPhone;
    @BindView(R.id.et_bank_name)
    MyEditText etBankName;
    @BindView(R.id.et_banknumber)
    MyEditText etBanknumber;
    @BindView(R.id.ll_company)
    LinearLayout llCompany;
    @BindView(R.id.tv_fapiao_commit)
    MyTextView tvFapiaoCommit;


    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.ll_zhuanyongfapiao)
    LinearLayout llZhuanyongfapiao;
    @BindView(R.id.sb_zengzhishui)
    SwitchButton sbZengzhishui;
    @BindView(R.id.sb_company_default)
    SwitchButton sbCompanyDefault;

    private int InvoiceTitle = 1;//1-个人  2-单位（公司）

    private String userId;
    private String token;

    InvoiceListResult.DataBean dataBean;//发票item信息

    String  type;
    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarColor(R.color.white)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
//        DaggerInvoiceComponent.builder()
//                .appComponent(appComponent)
//                .invoiceModule(new InvoiceModule(this))
//                .build().injectAddInvoice(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_invoice_add;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
        type = getIntent().getStringExtra("TYPE");
        if(type.equals("ADD")){          //新增发票
            setAppTitle("新增发票");  //初始化时，显示个人的
            llGeren.setVisibility(View.VISIBLE);
            llCompany.setVisibility(View.GONE);

        }else{
            setAppTitle("编辑发票信息"); //编辑发票
            //获取发票item的信息
            dataBean = getIntent().getParcelableExtra("INVOICE_INFO");
            mPresenter.invoideInfo(new InvoiceDeletePost(userId,token,dataBean.getId()));

            if (dataBean.getInvoiceTitle() == 1) {// 展示内容  1代表个人发票   2代表公司
                InvoiceTitle = 1;
                llGeren.setVisibility(View.VISIBLE);
                llCompany.setVisibility(View.GONE);

            } else {
                InvoiceTitle = 2;
                rbCompany.setChecked(true);
                llGeren.setVisibility(View.GONE);
                llCompany.setVisibility(View.VISIBLE);
            }

        }


        //增值税按钮
        sbZengzhishui.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    llZhuanyongfapiao.setVisibility(View.VISIBLE);
                } else {
                    llZhuanyongfapiao.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.rb_geren, R.id.rb_company, R.id.ll_zhuanyongfapiao, R.id.tv_fapiao_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_geren:
                InvoiceTitle = 1;
                llGeren.setVisibility(View.VISIBLE);
                llCompany.setVisibility(View.GONE);
                break;
            case R.id.rb_company:
                InvoiceTitle = 2;
                llGeren.setVisibility(View.GONE);
                llCompany.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_fapiao_commit://提交

                if (InvoiceTitle == 1) {  //个人发票
                    String invoiceTitle = etInvoiceTitle.getText().toString();
                    String phone =  etGerenPhone.getText().toString().trim();
                    if (TextUtils.isEmpty(invoiceTitle)) {
                        ArmsUtils.snackbarText("请输入发票抬头");
                        return;
                    }

                    if(type.equals("ADD")){
                        mPresenter.addInvoice(new InvoiceAddPost(userId, token, 1, invoiceTitle,phone, sbInvoiceDefault.isChecked() ? 1 : 0));
                    }else{
                        mPresenter.editorInvoice(new InvoiceEditorPost(userId, token,dataBean.getId(), 1, invoiceTitle,phone, sbInvoiceDefault.isChecked() ? 1 : 0));
                    }

                } else {
                    //公司发票
                    String companyInvoiceTitle = etCompanyInvoiceTitle.getText().toString();
                    if (TextUtils.isEmpty(companyInvoiceTitle)) {
                        ArmsUtils.snackbarText("请输入发票抬头");
                        return;
                    }
                    String companyInvoiceCode = etCompanyInvoiceCode.getText().toString();
                    if (TextUtils.isEmpty(companyInvoiceCode)) {
                        ArmsUtils.snackbarText("请输入公司纳税人税号");
                        return;
                    }
                    if (sbZengzhishui.isChecked()) {//需要增值税专用发票

                        String address = etAddress.getText().toString();
                        if (TextUtils.isEmpty(address)) {
                            ArmsUtils.snackbarText("请输入公司地址");
                            return;
                        }
                        String phone = etCompanyPhone.getText().toString();
                        if (TextUtils.isEmpty(phone)) {
                            ArmsUtils.snackbarText("请输入公司电话");
                            return;
                        }
                        String bankName = etBankName.getText().toString();
                        if (TextUtils.isEmpty(companyInvoiceTitle)) {
                            ArmsUtils.snackbarText("请输入公司开户行名称");
                            return;
                        }
                        String bankNumber = etBanknumber.getText().toString();
                        if (TextUtils.isEmpty(companyInvoiceCode)) {
                            ArmsUtils.snackbarText("请输入公司银行账号");
                            return;
                        }
                        if(type.equals("ADD")){
                            mPresenter.addInvoice(new InvoiceAddPost(userId, token, 2, companyInvoiceTitle, sbCompanyDefault.isChecked() ? 1 : 0, companyInvoiceCode, address, phone, bankName, bankNumber));
                        }else{
                            mPresenter.editorInvoice(new InvoiceEditorPost(userId, token,dataBean.getId(), 2, companyInvoiceTitle, sbCompanyDefault.isChecked() ? 1 : 0, companyInvoiceCode, address, phone, bankName, bankNumber));
                        }

                    } else {

                        //不需要增值税发票
                        if(type.equals("ADD")){
                            mPresenter.addInvoice(new InvoiceAddPost(userId, token, 2, companyInvoiceTitle, sbCompanyDefault.isChecked() ? 1 : 0, companyInvoiceCode));
                        }else{
                            mPresenter.editorInvoice(new InvoiceEditorPost(userId, token,dataBean.getId(), 2, companyInvoiceTitle, sbCompanyDefault.isChecked() ? 1 : 0, companyInvoiceCode));
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void addInvoiceResult(BaseResponse baseResponse) {
        showLoadSuccess();
        finish();
        EventBus.getDefault().post(new RefreshInvoiceEvent());
    }

    @Override
    public void editorInvoiceResult(BaseResponse baseResponse) {
        showLoadSuccess();
        finish();
        EventBus.getDefault().post(new RefreshInvoiceEvent());
    }

    //获取发票信息结果
    @Override
    public void invoiceInfoResult(InvoiceInfoResult invoiceInfoResult) {
        showLoadSuccess();

        etInvoiceTitle.setText(invoiceInfoResult.getData().getHeaderName()); //个人的发票抬头
        etGerenPhone.setText(invoiceInfoResult.getData().getPhone()); //个人的电话号码
        if (invoiceInfoResult.getData().getIsDefault() == 1) {
            sbInvoiceDefault.setChecked(true);
        } else {
            sbInvoiceDefault.setChecked(false);
        }
        //公司信息
        etCompanyInvoiceTitle.setText(invoiceInfoResult.getData().getHeaderName());
        etCompanyInvoiceCode.setText(invoiceInfoResult.getData().getTaxNumber());
        if (invoiceInfoResult.getData().getIsDefault() == 1) {
            sbCompanyDefault.setChecked(true);
        } else {
            sbCompanyDefault.setChecked(false);
        }
        //如果注册地址，公司电话等有填写，按钮选中
        if(invoiceInfoResult.getData().getRegAddress().length()>0||
                invoiceInfoResult.getData().getRegCall().length()>0||
                invoiceInfoResult.getData().getBankName().length()>0||
                invoiceInfoResult.getData().getBankAccount().length()>0){
            sbZengzhishui.setChecked(true);
            etAddress.setText(invoiceInfoResult.getData().getRegAddress());
            etCompanyPhone.setText(invoiceInfoResult.getData().getRegCall());
            etBankName.setText(invoiceInfoResult.getData().getBankName());
            etBanknumber.setText(invoiceInfoResult.getData().getBankAccount());
        }else{
            sbZengzhishui.setChecked(false);
        }
//        if(invoiceInfoResult.getData().g){
//
//        }


    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
