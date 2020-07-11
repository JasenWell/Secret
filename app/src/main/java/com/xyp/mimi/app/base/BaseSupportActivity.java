package com.xyp.mimi.app.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.billy.android.loading.Gloading;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.BaseDividerListItem;
import com.github.baseclass.rx.IOCallBack;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.mvp.IPresenter;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.event.ResponseErrorEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

public  abstract  class  BaseSupportActivity<P extends IPresenter> extends BaseActivity<P> implements ISupportActivity {

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

    protected Gloading.Holder mHolder;

    private CompositeSubscription mCSubscription;

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
        super.onCreate(savedInstanceState);

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
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ImmersionBar.with(this).init();
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
}
