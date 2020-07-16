package com.xyp.mimi.app.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.billy.android.loading.Gloading;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.BaseDividerListItem;
import com.github.baseclass.rx.IOCallBack;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.utils.ArmsUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.im.common.IntentExtra;
import com.xyp.mimi.im.im.IMManager;
import com.xyp.mimi.im.net.hjh.callback.IBaseCallBack;
import com.xyp.mimi.im.net.hjh.imp.AsynModelImp;
import com.xyp.mimi.im.utils.ToastUtils;
import com.xyp.mimi.im.utils.log.SLog;
import com.xyp.mimi.mvp.event.ResponseErrorEvent;
import com.xyp.mimi.mvp.ui.activity.login.LoginActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public  abstract  class  BaseSupportActivity<P extends IPresenter> extends BaseActivity<P> implements ISupportActivity, IBaseCallBack {

    final SupportActivityDelegate mDelegate = new SupportActivityDelegate(this);
    public BaseSupportActivity mContext;
    protected Toolbar toolbar;
    private boolean showNavigationIcon =true;
    private int navigationIcon =-1;
    private String appTitle,appRightTitle,appLeftTitle;
    private int appTitleColor,appRightTitleColor,appLeftTitleColor;
    private int appRightImg;
    private int titleBackgroud= R.color.white;
    //    private int statusBarBackgroud= R.color.green;
    protected TextView app_title,app_right_tv,app_left_tv;
    protected ImageView app_right_iv;
    boolean istoolbar=true;
    protected ImmersionBar mImmersionBar;
    protected AsynModelImp asynModelImp;
    protected Gloading.Holder mHolder;

    private CompositeSubscription mCSubscription;
    //-----hjh-------------
    private boolean mEnableListenKeyboardState = false;
    private Handler handler = new Handler();
    private long lastClickTime;
    //-----hjh--------------

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return mDelegate;
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    @Override
    public ExtraTransaction extraTransaction() {
        return mDelegate.extraTransaction();
    }



    //onCreate –> onContentChanged –> onStart –> onPostCreate –> onResume –> onPostResume –> onPause –> onStop –> onDestroy
//onContentChanged()是Activity中的一个回调方法
//
//当Activity的布局改动时，即setContentView()或者addContentView()方法执行完毕时就会调用该方法



    protected void setAppTitle(String title){
        appTitle=title;
        if(app_title!=null){
            app_title.setText(appTitle==null?"":appTitle);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        mDelegate.onCreate(savedInstanceState);
        asynModelImp = new AsynModelImp(this);
        /*
         * 修复部分 Android 8.0 手机在TargetSDK 大于 26 时，在透明主题时指定 Activity 方向时崩溃的问题
         */
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            fixOrientation();
        }
        super.onCreate(savedInstanceState);
        if (isFullScreen()) {
            // 隐藏Activity顶部的状态栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if(istoolbar){
            if (null != findViewById(R.id.toolbar)) {
                toolbar = (Toolbar) findViewById(R.id.toolbar);
                toolbar.setTitle("");
                setSupportActionBar(toolbar);
                toolbar.setNavigationOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            finishAfterTransition();
                        } else {
                            finish();
                        }
                    }
                });
                toolbar.setBackgroundColor(ContextCompat.getColor(mContext, titleBackgroud));
            }
        }
        if(null!=findViewById(R.id.app_title)){
            app_title= (TextView) findViewById(R.id.app_title);
            app_title.setText(appTitle==null?"":appTitle);
//            if(null!=findViewById(R.id.v_bottom_line)){
//                if(TextUtils.isEmpty(appTitle)||hiddenBottomLine){
//                    findViewById(R.id.v_bottom_line).setVisibility(View.GONE);
//                }
//            }

            if(appTitleColor!=0){
                app_title.setTextColor(appTitleColor);
            }
        }
        if(null!=findViewById(R.id.app_right_tv)){
            app_right_tv= (TextView) findViewById(R.id.app_right_tv);
        }
        if(null!=findViewById(R.id.app_right_iv)){
            app_right_iv= (ImageView) findViewById(R.id.app_right_iv);
        }
        if(appRightImg!=0){
            app_right_iv.setImageResource(appRightImg);

            app_right_tv.setVisibility(View.GONE);
            app_right_iv.setVisibility(View.VISIBLE);
        }
        if(appRightTitle!=null){
            app_right_tv.setText(appRightTitle);
            app_right_tv.setVisibility(View.VISIBLE);
            app_right_iv.setVisibility(View.GONE);
            if(appRightTitleColor!=0){
                app_right_tv.setTextColor(appRightTitleColor);
            }
        }

        if(appLeftTitle!=null){
            app_left_tv.setText(appLeftTitle);
            app_left_tv.setVisibility(View.VISIBLE);
            if(appLeftTitleColor!=0){
                app_left_tv.setTextColor(appLeftTitleColor);
            }
        }

        if(toolbar!=null){
            if(navigationIcon != -1){
                Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(navigationIcon);
            }else{
                Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.mipmap.back_arrow);
            }
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
            getSupportActionBar().setDisplayHomeAsUpEnabled(showNavigationIcon);
        }

        if (isImmersionBarEnabled())
            initImmersionBar();

//        if(null!=findViewById(R.id.pl_load)){
//            pl_load = (ProgressLayout) findViewById(R.id.pl_load);
//            pl_load.setInter(this);
//        }

        // 监听退出
        if (isObserveLogout()) {
            registerLogoutBoardcast();
            IMManager.getInstance().getKickedOffline().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isLogout) {
                    /*
                     * 只有当前显示的 Activity 会走此段逻辑
                     */
                    if (isLogout) {
                        SLog.d(BaseActivity.class.getCanonicalName(), "Log out.");
                        sendLogoutNotify();
                        IMManager.getInstance().resetKickedOfflineState();
                        Intent intent = new Intent(BaseSupportActivity.this, LoginActivity.class);
                        intent.putExtra(IntentExtra.BOOLEAN_KICKED_BY_OTHER_USER, true);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }

        // 清除已存在的 Fragment 防止因没有复用导致叠加显示
        clearAllFragmentExistBeforeCreate();
    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toolbar的事件---返回
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public  void setToolbarFailture(){
        istoolbar = false;
    }
    protected void setBackIcon(int resId){
        navigationIcon=resId;
    }
    protected void hiddenBackIcon(){
        showNavigationIcon=false;
    }
    protected void hiddenBackIcon(boolean show){
        showNavigationIcon=show;
    }

    protected String getSStr(View view){
        if(view instanceof TextView){
            return ((TextView)view).getText().toString();
        } else if (view instanceof EditText) {
            return ((EditText)view).getText().toString();
        }else{
            return null;
        }
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ResponseErrorEvent event) {
        showLoadSuccess();

    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        defaultImmersionBar();
    }

    public void defaultImmersionBar(){
        mImmersionBar
                .statusBarDarkFont(true, 0.2f)//设置状态栏图片为深色，(如果android 6.0以下就是半透明)
                .fitsSystemWindows(true)//设置这个是为了防止布局和顶部的状态栏重叠
                .statusBarColor(R.color.seal_main_title_bg)//这里的颜色，你可以自定义。
                .init();
    }

    protected BaseDividerListItem getItemDivider(int height){
        return new BaseDividerListItem(mContext,BaseDividerListItem.VERTICAL_LIST,height,R.color.background_f2);
    }

    protected BaseDividerListItem getItemDivider(int height,int color){
        return new BaseDividerListItem(mContext,BaseDividerListItem.VERTICAL_LIST,height,color);
    }



    public void STActivityForResult(Class clazz, int requestCode) {
        this.startActivityForResult(new Intent(this, clazz), requestCode);
    }

    public void STActivityForResult(Intent intent, Class clazz, int requestCode) {
        intent.setClass(this, clazz);
        this.startActivityForResult(intent, requestCode);
    }

    public void STActivity(Class clazz) {
        this.startActivity(new Intent(this, clazz));
    }

    public void STActivity(Intent intent, Class clazz) {
        intent.setClass(this, clazz);
        this.startActivity(intent);
    }

    public void showMsg(String msg) {
        com.github.androidtools.ToastUtils.showToast(this.getApplicationContext(), msg);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ImmersionBar.with(this).init();
        recreate();
    }
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(this).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
    }


    protected  void onLoadRetry(){

    };


    public void showLoading() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoading();
    }

    public void showLoadSuccess() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadSuccess();
    }

    public void showLoadFailed() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadFailed();
    }

    public void showEmpty() {
        initLoadingStatusViewIfNeed();
        mHolder.showEmpty();
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDelegate.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态

        // 注销广播
        if (isObserveLogout()) {
            unRegisterLogoutBroadcast();
        }
        //移除所有
        handler.removeCallbacksAndMessages(null);
    }


    protected void addSubscription(Subscription subscription) {
        if (this.mCSubscription == null) {
            this.mCSubscription = new CompositeSubscription();
        }

        this.mCSubscription.add(subscription);
    }
    public <T> void RXStart(final IOCallBack<T> callBack) {
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<T>() {
            public void call(Subscriber<? super T> subscriber) {
                callBack.call(subscriber);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<T>() {
            public void onCompleted() {
                callBack.onMyCompleted();
            }

            public void onError(Throwable e) {
                callBack.onMyError(e);
            }

            public void onNext(T t) {
                callBack.onMyNext(t);
            }
        });
        this.addSubscription(subscribe);
    }

    /**
     * Note： return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mDelegate.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mDelegate.setFragmentAnimator(fragmentAnimator);
    }
    //设置转场动画
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        // return new DefaultHorizontalAnimator();
        // 设置无动画
        // return new DefaultNoAnimator();
        // 设置自定义动画
        // return new FragmentAnimator(enter,exit,popEnter,popExit);

        // 默认竖向(和安卓5.0以上的动画相同)
        return mDelegate.onCreateFragmentAnimator();
    }


    // 任意Fragment的onBackPressedSupport()返回true，该方法都不会被回调
    @Override
    public void onBackPressedSupport() {
        mDelegate.onBackPressedSupport();
    }

    /****************************************以下为可选方法(Optional methods)******************************************************/

    // 选择性拓展其他方法
    public void loadRootFragment(int containerId, @NonNull ISupportFragment toFragment) {
        mDelegate.loadRootFragment(containerId, toFragment);
    }

    public void loadMultipleRootFragment(int containerId, int showPosition, @NonNull ISupportFragment... toFragments) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }

    public void start(ISupportFragment toFragment) {
        mDelegate.start(toFragment);
    }

    /**
     * @param launchMode Same as Activity's LaunchMode.
     */
    public void start(ISupportFragment toFragment, @ISupportFragment.LaunchMode int launchMode) {
        mDelegate.start(toFragment, launchMode);
    }

    /**
     * Pop the fragment.
     */
    public void pop() {
        mDelegate.pop();
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * icon_back stack.
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment);
    }

    public void showHideFragment(ISupportFragment showFragment) {
        mDelegate.showHideFragment(showFragment);
    }

    public void showHideFragment(ISupportFragment showFragment, ISupportFragment preFragment) {
        mDelegate.showHideFragment(showFragment, preFragment);
    }

    /**
     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim);
    }

    /**
     * 得到位于栈顶Fragment
     */
    public ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getSupportFragmentManager());
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }

    //----------------------------hjh------------------------
    /**
     * 清除所有已存在的 Fragment 防止因重建 Activity 时，前 Fragment 没有销毁和重新复用导致界面重复显示
     * 如果有自己实现 Fragment 的复用，请复写此方法并不实现内容
     */
    public void clearAllFragmentExistBeforeCreate() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments.size() == 0) return;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : fragments) {
            fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.commitNow();
    }

    /**
     * 是否隐藏状态栏全屏
     *
     * @return
     */
    protected boolean isFullScreen() {
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mEnableListenKeyboardState) {
            addKeyboardStateListener();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        removeKeyBoardStateListener();
    }

    /**
     * 隐藏键盘
     */
    public void hideInputKeyboard() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    /**
     * 设置沉浸式状态栏
     */
    public void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 隐藏导航键
     */
    public void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    /**
     * 启动键盘状态监听
     *
     * @param enable
     */
    public void enableKeyboardStateListener(boolean enable) {
        mEnableListenKeyboardState = enable;
    }

    /**
     * 添加键盘显示监听
     */
    private void addKeyboardStateListener() {
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(onKeyboardStateChangedListener);
    }

    /**
     * 移除键盘显示监听
     */
    private void removeKeyBoardStateListener() {
        getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(onKeyboardStateChangedListener);
    }

    /**
     * 监听键盘显示状态
     */
    private ViewTreeObserver.OnGlobalLayoutListener onKeyboardStateChangedListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        int mScreenHeight = 0;
        boolean isCurrentActive = false;

        private int getScreenHeight() {
            if (mScreenHeight > 0) {
                return mScreenHeight;
            }
            Point point = new Point();
            ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
            mScreenHeight = point.y;
            return mScreenHeight;
        }

        @Override
        public void onGlobalLayout() {
            Rect rect = new Rect();
            // 获取当前窗口显示范围
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int screenHeight = getScreenHeight();
            int keyboardHeight = screenHeight - rect.bottom; // 输入法的高度
            boolean isActive = false;
            if (Math.abs(keyboardHeight) > screenHeight / 4) {
                isActive = true; // 超过屏幕1/4则表示弹出了输入法
            }

            if (isCurrentActive != isActive) {
                isCurrentActive = isActive;
                onKeyboardStateChanged(isActive, keyboardHeight);
            }
        }
    };

    /**
     * 当软键盘显示时回调
     * 此回调在调用{@link BaseSupportActivity#enableKeyboardStateListener(boolean)}启用监听键盘显示
     *
     * @param isShown
     * @param height
     */
    public void onKeyboardStateChanged(boolean isShown, int height) {

    }

    /**
     * 判断当前主题是否是透明悬浮
     *
     * @return
     */
    private boolean isTranslucentOrFloating() {
        boolean isTranslucentOrFloating = false;
        try {
            int[] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            final TypedArray ta = obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (boolean) m.invoke(null, ta);
            m.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }

    /**
     * 改变当前的 Activity 的显示方向
     * 解决当前Android 8.0 系统在透明主题时设定显示方向时崩溃的问题
     *
     * @return
     */
    private boolean fixOrientation() {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ActivityInfo o = (ActivityInfo) field.get(this);
            o.screenOrientation = -1;
            field.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        /*
         * 修复 Android 8.0 手机在TargetSDK 大于 26 时，指定 Activity 方向时崩溃的问题
         */
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            return;
        }
        super.setRequestedOrientation(requestedOrientation);
    }


    public void showToast(String text) {
        //toast
        ToastUtils.showToast(text);
    }

    public void showToast(int resId) {
        showToast(getString(resId));
    }

    // 退出应用
    /**
     * 是否监听退出应用操作，默认监听， 如果不像监听， 可复写
     * 此方法并返回 false
     *
     * @return
     */
    public boolean isObserveLogout() {
        return true;
    }

    private void registerLogoutBoardcast() {
        IntentFilter intentFilter = new IntentFilter("com.rong.im.action.logout");
        registerReceiver(logoutRecevier, intentFilter);
    }

    private void unRegisterLogoutBroadcast() {
        unregisterReceiver(logoutRecevier);
    }

    /**
     * 通知通其他注册了登出广播的 Activity 关闭
     */
    public void sendLogoutNotify() {
        //发送广播
        Intent intent = new Intent("com.rong.im.action.logout");
        sendBroadcast(intent);
    }

    private BroadcastReceiver logoutRecevier = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };


    /**
     * 为防止多次重复点击
     *
     * @return
     */
    public synchronized boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    @Override
    public void showErrorInfo(int code, String devMsg) {
//        ArmsUtils.snackbarText(devMsg);
        showToast(devMsg);
    }

    @Override
    public void onSuccess(Object object, int type) {

    }

    @Override
    public BaseSupportActivity getCallBackActivity() {
        return this;
    }

    //----------------------------hjh------------------------
}
