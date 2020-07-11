//package com.yiwuzhijia.ddyp.mvp.ui.activity;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.blankj.utilcode.util.SPUtils;
//import com.github.customview.MyEditText;
//import com.jess.arms.di.component.AppComponent;
//import com.jess.arms.utils.ArmsUtils;
//import com.jess.arms.utils.DeviceUtils;
//
//import com.yiwuzhijia.ddyp.R;
//import com.yiwuzhijia.ddyp.app.base.BaseSupportActivity;
//import com.yiwuzhijia.ddyp.mvp.contract.BindContract;
//import com.yiwuzhijia.ddyp.mvp.http.entity.product.Product;
//import com.yiwuzhijia.ddyp.mvp.presenter.BindPresenter;
//import com.yiwuzhijia.ddyp.mvp.ui.adapter.BindRecommendQuickAdapter;
//import com.yiwuzhijia.ddyp.mvp.utils.AppConstant;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
//public class BindActivity extends BaseSupportActivity<BindPresenter> implements BindContract.View {
//    @BindView(R.id.et_acc)
//    MyEditText etAcc;
//    @BindView(R.id.et_impwd)
//    MyEditText etImpwd;
//    @BindView(R.id.btn_bind)
//    Button btnBind;
//    @BindView(R.id.recycler_view)
//    RecyclerView mRecyclerView;
//
//    @BindView(R.id.app_title)
//    TextView appTitle;
//
//    Product product;
//
//    @Inject
//    List<Product.ConBean> bindRecommendProductsBeans;
//
//
//    @Inject
//    BindRecommendQuickAdapter bindRecommendQuickAdapter;
//
//    @Override
//    public void setupActivityComponent(@NonNull AppComponent appComponent) {
////        DaggerBindComponent.builder()
////                .appComponent(appComponent)
////                .bindModule(new BindModule(this))
////                .build()
////                .inject(this);
//    }
//
//
//    @Override
//    public int initView(@Nullable Bundle savedInstanceState) {
//        return R.layout.activity_bind;
//    }
//
//    @Override
//    public void initData(@Nullable Bundle savedInstanceState) {
//        appTitle.setText("绑定");
////        setStatusBar();
////        product = getIntent().getParcelableExtra("list");
//        initRecycler();
//
//    }
//
//
//
//    private void initRecycler() {
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
//        mRecyclerView.setAdapter(bindRecommendQuickAdapter);
//        bindRecommendQuickAdapter.setOnClickDeleteListener(new BindRecommendQuickAdapter.OnClickDeleteListener() {
//            @Override
//            public void onClick(Product.ConBean c) {
//                mPresenter.delete( SPUtils.getInstance().getString(AppConstant.User.ID),c.getId());
//            }
//        });
////        recommendQuickAdapter.setEmptyView(LayoutInflater.from(_mActivity).inflate(R.layout.view_empty, null));
////        recommendQuickAdapter.setOnItemClickListener((adapter, view, position) -> {
////                    Intent intent = new Intent(_mActivity, ProductDetailsActivity.class);
////                    Bundle extras = new Bundle();
////                    extras.putSerializable(AppConstant.ActivityIntent.BEAN,
////                            ((Product) (adapter.getData()).get(position)));
////                    intent.putExtras(extras);
////                    startActivity(intent);
////                }
////        );
//    }
//
//
//    @Override
//    public void showMessage(@NonNull String message) {
//        if(message.equals("没有记录")){
//            bindRecommendProductsBeans.clear();
//            bindRecommendQuickAdapter.notifyDataSetChanged();
//        }
//        ArmsUtils.snackbarText(message);
//    }
//
//    //    @Override
////    public void initData(@Nullable Bundle savedInstanceState) {
////        recommendProductsBeans = getIntent().getParcelableExtra("list");
////    }
////
////
//
//
//
//    @OnClick({R.id.btn_bind,R.id.app_left_iv})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_bind:
//                DeviceUtils.hideSoftKeyboard(mContext,getCurrentFocus());
//                attemptBind();
//                break;
//            case R.id.app_left_iv:
//                finish();
//                break;
////            case R.id.btn_register:
////                startActivity(new Intent(mContext, RegisterActivity.class));
////                break;
//        }
//    }
//    //
//    private void attemptBind() {
//        String acc = etAcc.getText().toString();
//        String password = etImpwd.getText().toString();
//
//        if (TextUtils.isEmpty(password)) {
//            showMessage("密码不能为空/不可用");
//            return;
//        }
//        if (TextUtils.isEmpty(acc)) {
//            showMessage("账号不能为空");
//            return;
//        }
//        showLoading();
//        if(mPresenter !=null){
//            mPresenter.bind( SPUtils.getInstance().getString(AppConstant.User.ID),acc,password);
//        }
//    }
//    //
//
//
//    @Override
//    public void bindSuccess(String s) {
//        etAcc.setText("");
//        etImpwd.setText("");
//        ArmsUtils.snackbarText("绑定成功");
//        // 发布事件
////        EventBus.getDefault().post(new HomeRefreshEvent());
//        mPresenter.getBindRecommend( SPUtils.getInstance().getString(AppConstant.User.ID));
//
//
//    }
//
//    @Override
//    public void getBindRecommendSuccess(Product data) {
//        showLoadSuccess();
//        bindRecommendProductsBeans.clear();
//        bindRecommendProductsBeans.addAll(data.getCon());
//        bindRecommendQuickAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void deleteSuccess(String s) {
//        showLoadSuccess();
//        ArmsUtils.snackbarText(s);
//        mPresenter.getBindRecommend( SPUtils.getInstance().getString(AppConstant.User.ID));
//    }
//
//
//
//
//
//}
