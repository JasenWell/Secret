//package com.yiwuzhijia.ddyp;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.KeyEvent;
//import com.jaeger.library.StatusBarUtil;
//import com.jess.arms.di.component.AppComponent;
//import com.jess.arms.utils.ArmsUtils;
//import com.tbruyelle.rxpermissions2.RxPermissions;
//import com.yiwuzhijia.ddyp.app.base.BaseSupportActivity;
//import com.yiwuzhijia.ddyp.mvp.ui.fragment.MyFragment;
//import com.yiwuzhijia.ddyp.mvp.ui.fragment.NullFragment;
//import com.yiwuzhijia.ddyp.mvp.ui.fragment.cart.CartFragment;
//import com.yiwuzhijia.ddyp.mvp.ui.widget.bottombar.BottomBar;
//import com.yiwuzhijia.ddyp.mvp.ui.widget.bottombar.BottomBarTab;
//import butterknife.BindView;
//import me.yokeyword.fragmentation.ISupportFragment;
//import timber.log.Timber;
//
//public class MainActivity extends BaseSupportActivity {
//
//    @BindView(R.id.bottom_bar)
//    BottomBar mBottomBar;
//
//    private ISupportFragment[] mFragments = new ISupportFragment[4];
//
//    private BottomBarTab homeTab;
//    private BottomBarTab recommendTab;
//    private BottomBarTab cartTab;
//    private BottomBarTab myTab;
//
//    @Override
//    protected void initImmersionBar() {
//        super.initImmersionBar();
//        mImmersionBar.statusBarColor(R.color.white)
//                .fitsSystemWindows(true)
//                .statusBarDarkFont(true, 0.2f)
//                .init();
//    }
//
//    //    private double firstTime = 0;
//    private void initBottomBar() {
//        addFragment();
//        homeTab = new BottomBarTab(mContext, R.drawable.ft1, "首页");
//        recommendTab = new BottomBarTab(mContext, R.drawable.ft2, "推荐");
//        cartTab = new BottomBarTab(mContext, R.drawable.ft4, "购物车");
//        myTab = new BottomBarTab(mContext, R.drawable.ft5, "我的");
//        mBottomBar
//                .addItem(homeTab)
//                .addItem(recommendTab)
//                .addItem(cartTab)
//                .addItem(myTab);
//
//        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(int position, int prePosition) {
//                showHideFragment(mFragments[position], mFragments[prePosition]);
//            }
//
//            @Override
//            public void onTabUnselected(int position) {
//
//            }
//
//            @Override
//            public void onTabReselected(int position) {
//
//            }
//        });
//    }
//
//    private void addFragment() {
//        ISupportFragment recommendFragment = findFragment(NullFragment.class);
//        if (recommendFragment == null) {
//            mFragments[1] = new NullFragment();
//            mFragments[2] = new NullFragment();
//            mFragments[3] = new CartFragment();
//            mFragments[4] = new MyFragment();
//            loadMultipleRootFragment(R.id.fragment_contain, 0, mFragments);
//        } else {
//            mFragments[1] = findFragment(NullFragment.class);
//            mFragments[2] = findFragment(NullFragment.class);
//            mFragments[3] = findFragment(CartFragment.class);
//            mFragments[4] = findFragment(MyFragment.class);
//        }
//    }
//
//
//    @Override
//    public void setupActivityComponent(@NonNull AppComponent appComponent) {
//
//    }
//
//    @Override
//    public int initView(@Nullable Bundle savedInstanceState) {
//        return R.layout.activity_main;
//    }
//
//    @Override
//    public void initData(@Nullable Bundle savedInstanceState) {
////        RetrofitUrlManager.getInstance().setGlobalDomain((String) SpUtils.get(mContext, AppConstant.Api.BASE_URL,Api.APP_DOMAIN));
////        setStatusBar();
//        initBottomBar();
//        requestPermissions();
////        Main1AgreementView mainAgreementView = new Main1AgreementView(mContext,R.style.dialog);
////        mainAgreementView.setCancelable(false);
////
////        if(SPUtils.getInstance().getString(AppConstant.SP_IS_FIRST_ENTER_APP).equals("")){
////            mainAgreementView.showDialog();
////        }
//    }
//
//    @SuppressLint("CheckResult")
//    private void requestPermissions() {
//        RxPermissions rxPermission = new RxPermissions(MainActivity.this);
//        rxPermission
//                .requestEach(
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                )
//                .subscribe(permission -> {
//                    if (permission.granted) {
//                        // 用户已经同意该权限
//                        Timber.e("%s is granted.", permission.name);
//                    } else if (permission.shouldShowRequestPermissionRationale) {
//                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
//                        Timber.d("%s is denied. More info should be provided.", permission.name);
//                    } else {
//                        // 用户拒绝了该权限，并且选中『不再询问』
//                        Timber.e("%s is denied.", permission.name);
//                    }
//                });
//    }
//
//    @SuppressLint("ResourceAsColor")
//    protected void setStatusBar() {
//        StatusBarUtil.setColorNoTranslucent(MainActivity.this, R.color.colorPrimary);
//    }
//
////    @Override
////    public void onBackPressedSupport() {
////        long secondTime = System.currentTimeMillis();
////        if (secondTime - firstTime > 2000) {
////            ArmsUtils.snackbarText("再按一次退出程序");
////            firstTime = secondTime;
////        } else {
////            ArmsUtils.exitApp();
////        }
////    }
//
//
//
//    private long clickTime=0;
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exit();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private void exit() {
//        if ((System.currentTimeMillis() - clickTime) > 2000) {
//            ArmsUtils.snackbarText("再按一次退出程序");
//            clickTime = System.currentTimeMillis();
//        } else {
//            Log.e("MainActivity", "exit application");
//            this.finish();
//            System.exit(0);
//        }
//    }
//
//}
