package com.xyp.mimi.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportFragment;
import com.xyp.mimi.mvp.ui.activity.order.OrderListActivity;
import com.xyp.mimi.mvp.ui.activity.address.AddressListActivity;
import com.xyp.mimi.mvp.ui.activity.history.HistoryActivity;
import com.xyp.mimi.mvp.ui.activity.invoice.InvoiceListActivity;
import com.xyp.mimi.mvp.ui.activity.setting.SettingActivity;
import com.xyp.mimi.mvp.ui.activity.user.UserInformationActivity;
import com.xyp.mimi.mvp.ui.activity.wallet.MyEarningActivity;
import com.xyp.mimi.mvp.ui.activity.wallet.WalletActivity;
import com.xyp.mimi.mvp.utils.AppConstant;

import butterknife.OnClick;

public class MyFragment extends BaseSupportFragment {


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColorTransformEnable(false)
                .keyboardEnable(false)
                .init();
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_my, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        toolbar.setBackgroundColor(getResources().getColor(R.color.red_3233));

    }

    @Override
    public void setData(@Nullable Object data) {


    }

    @OnClick({R.id.ll_my_top,R.id.ll_order,
               R.id.ll_daifukuan,R.id.ll_daishenhe,R.id.ll_daifahuo,R.id.ll_daishouhuo,R.id.ll_daipingjia,
                 R.id.ll_wallet,R.id.ll_shouyi,R.id.ll_invoice,R.id.ll_address,R.id.ll_record,R.id.ll_problem,R.id.ll_setting})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.ll_my_top://用户信息
                STActivity(UserInformationActivity.class);
                break;
            case R.id.ll_order://我的订单
                STActivity(OrderListActivity.class);
                break;
            case R.id.ll_wallet://我的钱包
                STActivity(WalletActivity.class);
                break;
            case R.id.ll_daifukuan:
                intent.putExtra(AppConstant.User.ORDER_INDEX, 1);
                STActivity(intent, OrderListActivity.class);
                break;
            case R.id.ll_daishenhe:
                intent.putExtra(AppConstant.User.ORDER_INDEX, 2);
                STActivity(intent, OrderListActivity.class);
                break;
            case R.id.ll_daifahuo:
                intent.putExtra(AppConstant.User.ORDER_INDEX, 3);
                STActivity(intent, OrderListActivity.class);
                break;
            case R.id.ll_daishouhuo:
                intent.putExtra(AppConstant.User.ORDER_INDEX, 4);
                STActivity(intent, OrderListActivity.class);
                break;
            case R.id.ll_daipingjia:
                intent.putExtra(AppConstant.User.ORDER_INDEX, 5);
                STActivity(intent, OrderListActivity.class);
                break;
            case R.id.ll_shouyi://我的收益
                STActivity(MyEarningActivity.class);
                break;
            case R.id.ll_invoice: //发票
                STActivity(InvoiceListActivity.class);
                break;
            case R.id.ll_address: //地址管理
                STActivity(AddressListActivity.class);
                break;
            case R.id.ll_record://浏览记录
                STActivity(HistoryActivity.class);
                break;
//            case R.id.ll_problem://常见问题
//                STActivity(ProblemActivity.class);
//                break;
            case R.id.ll_setting://设置
                STActivity(SettingActivity.class);
                break;

        }
    }

}
