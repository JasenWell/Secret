package com.xyp.mimi.mvp.ui.activity.address;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.suke.widget.SwitchButton;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.address.DaggerAddressComponent;
import com.xyp.mimi.di.module.address.AddressModule;
import com.xyp.mimi.mvp.contract.address.AddressContract;
import com.xyp.mimi.mvp.event.RefreshAddressEvent;
import com.xyp.mimi.mvp.http.Constant;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.address.AddAddressPost;
import com.xyp.mimi.mvp.http.entity.address.AddressListResult;
import com.xyp.mimi.mvp.presenter.address.EditAddressPresenter;
import com.xyp.mimi.mvp.utils.AppConstant;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import static com.jess.arms.utils.Preconditions.checkNotNull;

public class AddAddressActivity extends BaseSupportActivity<EditAddressPresenter>  implements AddressContract.EditView {
    @BindView(R.id.et_editaddress_name)
    MyEditText etEditaddressName;
    @BindView(R.id.et_editaddress_phone)
    MyEditText etEditaddressPhone;
    @BindView(R.id.tv_editaddress_area)
    TextView tvEditaddressArea;
    @BindView(R.id.et_editaddress_detail)
    MyEditText etEditaddressDetail;
    @BindView(R.id.sb_address_default)
    SwitchButton sbAddressDefault;
    @BindView(R.id.tv_editaddress_commit)
    MyTextView tvEditaddressCommit;


    CityPickerView mCityPickerView = new CityPickerView();//选择城市

    String ProvinceCode,CityCode,DistrictCode;
    String ProvinceName,CityName,DistrictName;

    private boolean isEdit;
    private String userId ;
    private String token ;

    private int Id;//要修改的地址id
    AddressListResult.DataBean dataBean;

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
        DaggerAddressComponent.builder()
                .appComponent(appComponent)
                .addressModule(new AddressModule(this))
                .build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_add_address;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        isEdit = getIntent().getBooleanExtra(Constant.editAddress, false);
        userId =  SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);

        if (isEdit) {
            setAppTitle("编辑地址");
            //获取里面的Persion里面的数据
            dataBean = getIntent().getParcelableExtra(Constant.Address);
            setAddress(dataBean);
        } else {
            setAppTitle("添加新地址");
        }
        mCityPickerView.init(this);
    }


    private void setAddress(AddressListResult.DataBean dataBean) {

        String[] address =  dataBean.getAddress().split(" ");
        etEditaddressName.setText(dataBean.getConsignee());
        etEditaddressPhone.setText(dataBean.getMobile());
        tvEditaddressArea.setText(address[0]+address[1]+address[2]);
        etEditaddressDetail.setText(address[4]);
//        addressAreaCode = dataBean.getProvince();//地区编号
        sbAddressDefault.setChecked(dataBean.getIsDefault()==1?true:false);//是否选中
        ProvinceName = address[0];
        ProvinceCode = dataBean.getProvinceCode();
        CityName = address[1];
        CityCode = "0";
        DistrictName = address[2];
        DistrictCode = "0";
        Id = dataBean.getId();

    }



    @OnClick({R.id.tv_editaddress_area, R.id.tv_editaddress_commit})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_editaddress_area:
                selectCity();
                break;
            case R.id.tv_editaddress_commit:
                String name = etEditaddressName.getText().toString();
                String phone = etEditaddressPhone.getText().toString();
                String area = tvEditaddressArea.getText().toString();
                String detail =etEditaddressDetail.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    showMessage("收货人不能为空");
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    showMessage("手机号码不能为空");
                    return;
                } else if (TextUtils.isEmpty(area)) {
                    showMessage("所在地区不能为空");
                    return;
                } else if (TextUtils.isEmpty(detail)) {
                    showMessage("详细地址不能为空");
                    return;
                }
                if (isEdit) {
                    editAddress(name, phone, detail);
                } else {
                    addAddress(name, phone, detail);
                }
                break;
        }
    }

    private void editAddress(String name, String phone, String detail) {
        mPresenter.updateAddress(new AddAddressPost(userId,
                token,
                Id,
                name,
                phone,
                sbAddressDefault.isChecked() ? 1 : 0,
                ProvinceCode,
                ProvinceName,
                CityCode,
                CityName,
                DistrictCode,
                DistrictName,detail));

    }

    private void addAddress(String name, String phone, String detail) {
        mPresenter.addAddress(new AddAddressPost(userId,
                token,
                name,
                phone,
                sbAddressDefault.isChecked() ? 1 : 0,
                ProvinceCode,
                ProvinceName,
                CityCode,
                CityName,
                DistrictCode,
                DistrictName,detail));
    }


    /**
     * 弹出城市选择器
     */
    private void selectCity() {
        DeviceUtils.hideSoftKeyboard(mContext, getCurrentFocus());
        CityConfig cityConfig = new CityConfig.Builder().title("选择城市")
                .province("四川省")
                .city("成都市")
                .district("锦江区").build();
        mCityPickerView.setConfig(cityConfig);
        mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                StringBuilder sb = new StringBuilder();
//                sb.append("选择的结果：\n");
                if (province != null) {
                    sb.append(province.getName());
                    ProvinceCode = province.getId();
                    ProvinceName = province.getName();
                }
                if (city != null) {
                    sb.append(city.getName());
                    CityCode = city.getId();
                    CityName = city.getName();
                }
                if (district != null) {
                    sb.append(district.getName());
                    DistrictCode = district.getId();
                    DistrictName = district.getName();
                }
                tvEditaddressArea.setText(sb.toString().trim());
//                addressAreaCode = district.getId();
            }
            @Override
            public void onCancel() {
                ToastUtils.showLongToast(AddAddressActivity.this, "已取消");
            }
        });
        mCityPickerView.showCityPicker();
    }


    @Override
    public void addAddressResult(BaseResponse baseResponse) {
        showLoadSuccess();
        EventBus.getDefault().post(new RefreshAddressEvent());
        setResult(RESULT_OK);
        finish();

    }

    @Override
    public void updateAddressResult(BaseResponse baseResponse) {
        showLoadSuccess();
        EventBus.getDefault().post(new RefreshAddressEvent());//编辑地址
        finish();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }
}
