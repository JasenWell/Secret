package com.xyp.mimi.mvp.ui.activity.market;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.github.androidtools.PhoneUtils;
import com.github.customview.FlowLayout;
import com.github.customview.MyImageView;
import com.github.customview.MyTextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.market.DaggerGoodsDetailActivityComponent;
import com.xyp.mimi.mvp.contract.market.GoodsDetailActivityContract;
import com.xyp.mimi.mvp.http.Constant;
import com.xyp.mimi.mvp.http.entity.market.EvaluationListPost;
import com.xyp.mimi.mvp.http.entity.market.EvalutionListResult;
import com.xyp.mimi.mvp.http.entity.market.GoodsDetailPost;
import com.xyp.mimi.mvp.http.entity.market.GoodsDetailResult;
import com.xyp.mimi.mvp.presenter.market.GoodsDetailActivityPresenter;
import com.xyp.mimi.mvp.ui.adapter.evalution.EvalutionAdapter;
import com.xyp.mimi.mvp.utils.AppConstant;
import com.xyp.mimi.mvp.utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class GoodsDetailActivity extends BaseSupportActivity<GoodsDetailActivityPresenter> implements GoodsDetailActivityContract.View ,  BGANinePhotoLayout.Delegate{

    @BindView(R.id.nsv_goods_detail)
    NestedScrollView nsvGoodsDetail;
    @BindView(R.id.ll_order_detail_top)
    LinearLayout llOrderDetailTop;
    @BindView(R.id.tv_goods_detail_name)
    TextView tvGoodsDetailName;
    @BindView(R.id.ll_goods_detail_pj)
    LinearLayout llGoodsDetailPj;
    @BindView(R.id.detail_tab)
    TabLayout detailTab;
    @BindView(R.id.bn_goods_detail)
    Banner bnGoodsDetail;
    @BindView(R.id.tv_indicator)
    MyTextView tvIndicator;
    @BindView(R.id.fl_label)
    FlowLayout flLabel;
    @BindView(R.id.tv_map_address)
    TextView tvMapAddress;
    @BindView(R.id.rv_goods_detail_evaluate)
    RecyclerView rvGoodsDetailEvaluate;
    @BindView(R.id.tv_goods_detail_evaluate_num)
    TextView tvGoodsDetailEvaluateNum;
    @BindView(R.id.floatButton)
    FloatingActionButton floatButton;

    private String userId;
    private String token;
    private int productId;

    EvalutionAdapter evalutionAdapter;
    private BGANinePhotoLayout mCurrentClickNpl;
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarView(R.id.top_view)
                .navigationBarColor(R.color.colorPrimary)
                .addTag("PicAndColor")
                .init();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerGoodsDetailActivityComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_goods_detail; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        productId = getIntent().getIntExtra(Constant.GoodsId, 0);
        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
        mPresenter.getGoodsDetail(new GoodsDetailPost(userId, token, productId));
        mPresenter.getEvaluation(new EvaluationListPost(userId, token, 0, Integer.toString(productId), 1, 2));
        setTabTitle();
        int screenWidth = ScreenUtils.getScreenHeight();
        llOrderDetailTop.setVisibility(View.GONE);
        detailTab.setAlpha(0);
        nsvGoodsDetail.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY >= 0 && scrollY <= screenWidth / 2) {
                    float alpha = (float) scrollY / screenWidth * 2;
                    Log.d(TAG, scrollY + "onScrollChange: " + alpha);
                    llOrderDetailTop.setAlpha(alpha);
                    detailTab.setAlpha(alpha);
                    ImmersionBar.with(GoodsDetailActivity.this).getTag("PicAndColor").init();//恢复透明
                    floatButton.setVisibility(View.GONE);
                    llOrderDetailTop.setVisibility(View.VISIBLE);//顶部菜单栏展示
                } else if (scrollY == 0){
                    llOrderDetailTop.setVisibility(View.GONE);
                  }else {
                    ImmersionBar.with(GoodsDetailActivity.this)
                            .statusBarDarkFont(true, 0.2f)
                            .statusBarColor(R.color.colorAccent).init();//状态栏变灰
                    llOrderDetailTop.setAlpha(1);
                    detailTab.setAlpha(1);
                    floatButton.setVisibility(View.VISIBLE);
                    llOrderDetailTop.setVisibility(View.VISIBLE);
                }
                if (getLocalVisibleRect(mContext, tvGoodsDetailName, tvGoodsDetailName.getScrollY())) {
                    detailTab.setScrollPosition(0, 0, true);
                } else if (getLocalVisibleRect(mContext, llGoodsDetailPj, llGoodsDetailPj.getScrollY())) {
                    detailTab.setScrollPosition(0, 1, true);
                }
            }
        });
    }

    /**
     * 判断当前view是否在屏幕可见
     */
    public static boolean getLocalVisibleRect(Context context, View view, int offsetY) {
        Point p = new Point();
        int screenWidth = p.x;
        int screenHeight = p.y;
        Rect rect = new Rect(0, 0, screenWidth, screenHeight);
        int[] location = new int[2];
        location[1] = location[1] + ConvertUtils.dp2px(offsetY);
        view.getLocationInWindow(location);
        view.setTag(location[1]);//存储y方向的位置
        if (view.getLocalVisibleRect(rect)) {
            return true;
        } else {
            return false;
        }
    }

    private void setTabTitle() {
        detailTab.addTab(detailTab.newTab().setText("商品"));
        detailTab.addTab(detailTab.newTab().setText("评价"));
        detailTab.addTab(detailTab.newTab().setText("详情"));
        detailTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        nsvGoodsDetail.smoothScrollTo(0, 0);
                        break;
                    case 1:
                        nsvGoodsDetail.smoothScrollTo(0, llGoodsDetailPj.getTop() - ConvertUtils.dp2px(getResources().getDimension(R.dimen.app_title_height)));
                        break;
                    case 2:
                        nsvGoodsDetail.smoothScrollTo(0, llGoodsDetailPj.getTop() - ConvertUtils.dp2px(getResources().getDimension(R.dimen.app_title_height)));
                        break;
//                    case 3:
//                        nsv_goods_detail.smoothScrollTo(0, ll_goods_detail_tuijian.getTop() - PhoneUtils.dip2px(mContext, getResources().getDimension(R.dimen.app_title_height)));
//                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    @OnClick({R.id.iv_goods_detail_back,R.id.iv_wenhao, R.id.ll_shuxing, R.id.floatButton,R.id.ll_goods_detail_kefu,R.id.ll_goods_detail_shop,R.id.ll_goods_detail_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_goods_detail_back:
                finish();
                break;
            case R.id.iv_wenhao:
                showPopWenhao();
                break;
            case R.id.ll_shuxing:
                showPopShuxing();
                break;
            case R.id.floatButton://浮动按钮
                nsvGoodsDetail.smoothScrollTo(0, 0);
                break;
            case R.id.ll_goods_detail_kefu://客服

                break;
            case R.id.ll_goods_detail_shop://店铺

                break;
            case R.id.ll_goods_detail_collect://收藏

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
    }

    private void showPopShuxing() {
        BottomSheetDialog shuxingDialog = new BottomSheetDialog(mContext, R.style.BottomSheetDialog);
        shuxingDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        shuxingDialog.setCanceledOnTouchOutside(true);
        shuxingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (shuxingDialog.isShowing() && keyCode == KeyEvent.KEYCODE_BACK) {
                    shuxingDialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        View payView = LayoutInflater.from(mContext).inflate(R.layout.popu_shuxing, null);

        MyTextView cancel = payView.findViewById(R.id.tv_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shuxingDialog.cancel();
            }
        });
        shuxingDialog.setContentView(payView);
        shuxingDialog.show();
    }

    private void showPopWenhao() {
        BottomSheetDialog payDialog = new BottomSheetDialog(mContext, R.style.BottomSheetDialog);
        payDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        payDialog.setCanceledOnTouchOutside(true);
        payDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (payDialog.isShowing() && keyCode == KeyEvent.KEYCODE_BACK) {
                    payDialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        payDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
//                finish();
            }
        });
        View payView = LayoutInflater.from(mContext).inflate(R.layout.popu_butie, null);

        MyImageView cancel = payView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payDialog.cancel();
            }
        });
        payDialog.setContentView(payView);
        payDialog.show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void goodsDetailResult(GoodsDetailResult goodsDetailResult) {
        injectBanner(goodsDetailResult);
        injectService(goodsDetailResult.getData().getServiceInfo());
        injectMap(goodsDetailResult.getData().getLng(), goodsDetailResult.getData().getLat());
        tvMapAddress.setText(goodsDetailResult.getData().getAreaSite() + goodsDetailResult.getData().getAddress());
    }

    @Override
    public void getEvaluationResult(EvalutionListResult evalutionListResult) {
        evalutionAdapter = new EvalutionAdapter(evalutionListResult.getData(),GoodsDetailActivity.this);
        LinearLayoutManager xLinearLayoutManager = new LinearLayoutManager(mContext);
        rvGoodsDetailEvaluate.setAdapter(evalutionAdapter);
        evalutionAdapter.notifyDataSetChanged();
        rvGoodsDetailEvaluate.setLayoutManager(xLinearLayoutManager);
        tvGoodsDetailEvaluateNum.setText("宝贝评价(" + evalutionListResult.getCount() + ")");
    }

    private void injectMap(double lng, double lat) {

    }

    //轮播图
    private void injectBanner(GoodsDetailResult goodsDetailResult) {
        int pageSize = goodsDetailResult.getData().getPicData().size();
        tvIndicator.setText(1 + "/" + pageSize);
        List<String> bannerList = new ArrayList<String>();
        for (GoodsDetailResult.DataBean.PicDataBean url : goodsDetailResult.getData().getPicData()) {
            bannerList.add(url.getPicUrl());
        }
        bnGoodsDetail.isAutoPlay(false);
        bnGoodsDetail.setImages(bannerList);
        bnGoodsDetail.setImageLoader(new GlideImageLoader());
        bnGoodsDetail.start();
        bnGoodsDetail.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("xyp", (position + "===================" + pageSize));
                if (tvIndicator != null) {
                    Log.i("xyp", (position + "===========GoodsDetailActivity.class========" + pageSize));
                    tvIndicator.setText(position + 1 + "/" + pageSize);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //服务
    private void injectService(List<GoodsDetailResult.DataBean.ServiceInfoBean> serviceInfo) {
        for (int i = 0; i < serviceInfo.size(); i++) {
            MyTextView textView = new MyTextView(mContext);
            textView.setText(serviceInfo.get(i).getName());
            textView.setPadding(PhoneUtils.dip2px(mContext, 8), 5, PhoneUtils.dip2px(mContext, 8), 5);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(14);
            textView.setIncludeFontPadding(false);
            textView.setTextColor(getResources().getColor(R.color.c76));
            textView.setMinHeight(PhoneUtils.dip2px(mContext, 20));
            FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, PhoneUtils.dip2px(mContext, 13), PhoneUtils.dip2px(mContext, 12));
            textView.setLayoutParams(layoutParams);
            textView.setRadius(PhoneUtils.dip2px(mContext, 15));
            Drawable icon = getResources().getDrawable(R.mipmap.check_circle);
            icon.setBounds(0, 0, 40, 44);
            textView.setCompoundDrawables(icon, null, null, null);
            textView.setCompoundDrawablePadding(5);
            textView.complete();
            flLabel.addView(textView);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {
        mCurrentClickNpl = ninePhotoLayout;
        photoPreviewWrapper();
    }

    @Override
    public void onClickExpand(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {
        ninePhotoLayout.setIsExpand(true);
        ninePhotoLayout.flushItems();
    }

    //    @AfterPermissionGranted(PRC_PHOTO_PREVIEW)
    private void photoPreviewWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        if (EasyPermissions.hasPermissions(this, perms)) {
        File downloadDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerDownload");
        BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(mContext)
                .saveImgDir(downloadDir); // 保存图片的目录，如果传 null，则没有保存图片功能
        photoPreviewIntentBuilder.saveImgDir(null);
        if (mCurrentClickNpl.getItemCount() == 1) {
            // 预览单张图片
            photoPreviewIntentBuilder.previewPhoto(mCurrentClickNpl.getCurrentClickItem());
        } else if (mCurrentClickNpl.getItemCount() > 1) {
            // 预览多张图片
            photoPreviewIntentBuilder.previewPhotos(mCurrentClickNpl.getData())
                    .currentPosition(mCurrentClickNpl.getCurrentClickItemPosition()); // 当前预览图片的索引
        }

        mContext.startActivity(photoPreviewIntentBuilder.build());
//        } else {
//            EasyPermissions.requestPermissions(this, "图片预览需要以下权限:\n\n1.访问设备上的照片", PRC_PHOTO_PREVIEW, perms);
//        }
    }

}
