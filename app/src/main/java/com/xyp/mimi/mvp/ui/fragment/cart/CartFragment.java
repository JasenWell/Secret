package com.xyp.mimi.mvp.ui.fragment.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.github.baseclass.BaseDividerListItem;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportFragment;
import com.xyp.mimi.di.module.cart.CartModule;
import com.xyp.mimi.mvp.contract.cart.CartContract;
import com.xyp.mimi.mvp.http.entity.cart.CartListPost;
import com.xyp.mimi.mvp.http.entity.cart.CartListResult;
import com.xyp.mimi.mvp.presenter.cart.CartPresenter;
import com.xyp.mimi.mvp.ui.adapter.cart.CartListAdapter;
import com.xyp.mimi.mvp.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CartFragment extends BaseSupportFragment<CartPresenter> implements CartContract.View {

    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.app_right_tv)
    TextView appRightTv;
    @BindView(R.id.rv_shopping_cart)
    RecyclerView rvShoppingCart;

    private String userId;
    private String token;

    CartListAdapter cartListAdapter;

    List<CartListResult.DataBean.CartListBean> data = new ArrayList<>();
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
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
//        DaggerCartComponent.builder()
//                .appComponent(appComponent)
//                .cartModule(new CartModule(this))
//                .build()
//                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_shopping_cart, container, false);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        appTitle.setText("购物车");
        appRightTv.setText("管理");
        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
        mPresenter.getCartListResult(new CartListPost(userId, token));
//        adapter = new ShoppingCartAdapter(mContext, R.layout.item_shopping_cart_child);
        cartListAdapter = new CartListAdapter(data);
        rvShoppingCart.setLayoutManager(new LinearLayoutManager(mContext));
        rvShoppingCart.addItemDecoration(new BaseDividerListItem(mContext,BaseDividerListItem.VERTICAL_LIST, ConvertUtils.dp2px(1),R.color.gray_f4));
        rvShoppingCart.setAdapter(cartListAdapter);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void getCartListResult(CartListResult cartListResult) {
        data.addAll(cartListResult.getData().getCartList());
        cartListAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.tv_shopingcart_jiesuan, R.id.app_right_tv, R.id.cb_shopingcart_select})
    protected void onViewClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.app_right_tv:
//                if (isEmpty(adapter.getList())) {
//                    showMsg("暂无商品编辑");
//                    return;
//                }
                cartListAdapter.setEdit(!cartListAdapter.isEdit());
                if (cartListAdapter.isEdit()) {
                    appRightTv.setText("完成");
                } else {
                    appRightTv.setText("管理");
                }
                cartListAdapter.notifyDataSetChanged();
                break;
            case R.id.cb_shopingcart_select:
//                if (cb_shopingcart_select.isChecked()) {
//                    adapter.setSelectAll(true);
//                } else {
//                    tv_shopingcart_total.setText("¥0.0");
//                    adapter.setSelectAll(false);
//                }
                break;
            case R.id.tv_shopingcart_jiesuan:
//                if (isEmpty(adapter.getList())) {
//                    showMsg("暂无商品结算");
//                    return;
//                }
//                ArrayList<ShoppingCartObj> list = new ArrayList<>();
//                for (int i = 0; i < adapter.getList().size(); i++) {
//                    ShoppingCartObj obj = adapter.getList().get(i);
//                    if (obj.isSelect()) {
//                        list.add(obj);
//                    }
//                }
//                if (list == null || list.size() == 0) {
//                    showMsg("请选择商品");
//                    return;
//                }
//                intent = new Intent();
//                intent.putParcelableArrayListExtra(Constant.IParam.shoppingGoods, list);
//                intent.putExtra(com.sk.jintang.module.orderclass.Constant.IParam.hourDao,true);
//                intent.putExtra(com.sk.jintang.module.orderclass.Constant.IParam.is_buy_now,"0");
//                STActivity(intent, SureGoodsActivity.class);
                break;
        }
    }



}
