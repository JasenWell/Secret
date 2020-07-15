package com.xyp.mimi.app.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.billy.android.loading.Gloading;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.ImmersionOwner;
import com.gyf.barlibrary.ImmersionProxy;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.mvp.IPresenter;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.event.ResponseErrorEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

//
public abstract class BaseSupportFragment<P extends IPresenter> extends BaseFragment<P> implements ISupportFragment, ImmersionOwner {

    final SupportFragmentDelegate mDelegate = new SupportFragmentDelegate(this);

    protected FragmentActivity _mActivity;

    protected Activity mActivity;
    protected Handler handler = new Handler();
    private Unbinder mUnbinder;
    protected ImmersionBar mImmersionBar;

 /**
     * ImmersionBar代理类
     */
    private ImmersionProxy immersionProxy = new ImmersionProxy(this);

    protected Gloading.Holder mHolder;
    @Override
    public SupportFragmentDelegate getSupportDelegate() {
        return mDelegate;
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ResponseErrorEvent event) {
        showLoadSuccess();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mDelegate.onAttach(activity);
        _mActivity = mDelegate.getActivity();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return mDelegate.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDelegate.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //绑定到butterknife
        mUnbinder = ButterKnife.bind(this,getView());
        onInitView(savedInstanceState, getActivity().getIntent());
        onInitViewModel();
    }

    @Override
    public void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this).keyboardEnable(true);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mDelegate.onSaveInstanceState(outState);
    }

    public void STActivity(Class clazz) {
        this.startActivity(new Intent(this.getActivity(), clazz));
    }

    public void STActivity(Intent intent, Class clazz) {
        intent.setClass(this.getActivity(), clazz);
        this.startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDelegate.onResume();
        immersionProxy.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mDelegate.onPause();
        immersionProxy.onPause();
    }

    @Override
    public void onDestroyView() {
        mDelegate.onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
        immersionProxy.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;
        //移除所有
        handler.removeCallbacksAndMessages(null);
    }

    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(getActivity()).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
    }

    protected  void onLoadRetry(){

    };

    /**
     * 初始化 view
     *
     * @param savedInstanceState
     * @param intent
     */
    public  void onInitView(Bundle savedInstanceState, Intent intent){

    }

    public void onInitViewModel() {

    }

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

    /**
     * 用户可见时候调用
     * On visible.
     */
    @Override
    public void onVisible() {
    }

    /**
     * 用户不可见时候调用
     * On invisible.
     */
    @Override
    public void onInvisible() {
    }

    @Override
    public boolean immersionBarEnabled() {
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        immersionProxy.onConfigurationChanged(newConfig);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mDelegate.onHiddenChanged(hidden);
        immersionProxy.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mDelegate.setUserVisibleHint(isVisibleToUser);
        immersionProxy.setUserVisibleHint(isVisibleToUser);
    }
    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    @Override
    public ExtraTransaction extraTransaction() {
        return mDelegate.extraTransaction();
    }


    /**
     * If you want to call the start()/pop()/showHideFragment() on the onCreateXX/onActivityCreated,
     * call this method to deliver the transaction to the queue.
     * <p>
     * 在onCreate/onCreateView/onActivityCreated中使用 start()/pop()/showHideFragment(),请使用该方法把你的任务入队
     *
     * @param runnable start() , pop() or showHideFragment()
     */

    @Override
    public void enqueueAction(Runnable runnable) {
         mDelegate.enqueueAction(runnable);
    }

    /**
     * Called when the enter-animation end.
     * 入栈动画 结束时,回调
     */
    @Override
    public void onEnterAnimationEnd(@Nullable Bundle savedInstanceState) {
     //设置listenr各种adapter,请求数据等
        mDelegate.onEnterAnimationEnd(savedInstanceState);
    }


    /**
     * Lazy initial，Called when fragment is first called.
     * <p>
     * 同级下的 懒加载 ＋ ViewPager下的懒加载  的结合回调方法
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
      mDelegate.onLazyInitView(savedInstanceState);
    }


    //等生命周期调用顺序：onActivityCreated() -> onResume() -> onSupportVisible ->
    // onLazyInitView() => onSupportInvisible() -> onPause()
    @Override
    public void onSupportVisible() {
        //当fragment对用户可见时
        mDelegate.onSupportVisible();

    }

    @Override
    public void onSupportInvisible() {
        //当fragment对用户不可见时
       mDelegate.onSupportInvisible();
    }

    /**
     * Return true if the fragment has been supportVisible.
     */
    @Override
    public boolean isSupportVisible() {
        return mDelegate.isSupportVisible();
    }

    /**
     * Set fragment animation with a higher priority than the ISupportActivity
     * 设定当前Fragmemt动画,优先级比在SupportActivity里高
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mDelegate.onCreateFragmentAnimator();
    }
    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mDelegate.getFragmentAnimator();
    }
    /**
     * 设置Fragment内的全局动画
     */
    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
      mDelegate.setFragmentAnimator(fragmentAnimator);
    }


    //设置传给上个Fragment的数据
    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {
        mDelegate.setFragmentResult(resultCode,bundle);
    }

    //启动新的Fragment，并能接收到新Fragment的数据返回：
    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        mDelegate.onFragmentResult(requestCode, resultCode, data);
    }

    /**
     * 在start(TargetFragment,LaunchMode)时,启动模式为SingleTask/SingleTop, 回调TargetFragment的该方法
     *
     * @param args putNewBundle(Bundle newBundle)
     * @see #start(ISupportFragment, int)
     */
    @Override
    public void onNewBundle(Bundle args) {
        // 在此可以接收到SINGLETASK/SINGTOP启动模式传递的数据  类似Activity中的onNewIntent()
        mDelegate.onNewBundle(args);
    }


    /**
     * 添加NewBundle,用于启动模式为SingleTask/SingleTop时
     *
     * @see #start(ISupportFragment, int)
     */
    @Override
    public void putNewBundle(Bundle newBundle) {
      mDelegate.putNewBundle(newBundle);
    }

    //如果return true，则消费该事件，不再向上传递。
    @Override
    public boolean onBackPressedSupport() {
        return mDelegate.onBackPressedSupport();
    }


    /****************************************以下为可选方法(Optional methods)******************************************************/
    // 自定制Support时，可移除不必要的方法

    /**
     * 隐藏软键盘
     */
    protected void hideSoftInput() {
        mDelegate.hideSoftInput();
    }

    /**
     * 显示软键盘,调用该方法后,会在onPause时自动隐藏软键盘
     */
    protected void showSoftInput(final View view) {
        mDelegate.showSoftInput(view);
    }

    /**
     * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
     *
     * @param containerId 容器id
     * @param toFragment  目标Fragment
     */
    public void loadRootFragment(int containerId, ISupportFragment toFragment) {
        mDelegate.loadRootFragment(containerId, toFragment);
    }

    public void loadRootFragment(int containerId, ISupportFragment toFragment, boolean addToBackStack, boolean allowAnim) {
        mDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnim);
    }

    /**
     * 加载多个同级根Fragment,类似Wechat, QQ主页的场景
     */
    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }

    /**
     * show一个Fragment,hide其他同栈所有Fragment
     * 使用该方法时，要确保同级栈内无多余的Fragment,(只有通过loadMultipleRootFragment()载入的Fragment)
     * <p>
     * 建议使用更明确的{@link #showHideFragment(ISupportFragment, ISupportFragment)}
     *
     * @param showFragment 需要show的Fragment
     */
    public void showHideFragment(ISupportFragment showFragment) {
        mDelegate.showHideFragment(showFragment);
    }

    /**
     * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
     */
    public void showHideFragment(ISupportFragment showFragment, ISupportFragment hideFragment) {
        mDelegate.showHideFragment(showFragment, hideFragment);
    }

    public void start(ISupportFragment toFragment) {
        mDelegate.start(toFragment);
    }

    /**
     * @param launchMode Similar to Activity's LaunchMode.
     */
    public void start(final ISupportFragment toFragment, @LaunchMode int launchMode) {
        mDelegate.start(toFragment, launchMode);
    }

    /**
     * Launch an fragment for which you would like a result when it poped.
     */
    public void startForResult(ISupportFragment toFragment, int requestCode) {
        mDelegate.startForResult(toFragment, requestCode);
    }

    /**
     * Launch a fragment while poping self.
     */
    public void startWithPop(ISupportFragment toFragment) {
        mDelegate.startWithPop(toFragment);
    }

    public void replaceFragment(ISupportFragment toFragment, boolean addToBackStack) {
        mDelegate.replaceFragment(toFragment, addToBackStack);
    }

    public void pop() {
        mDelegate.pop();
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * icon_back stack.
     * <p>
     * 出栈到目标fragment
     *
     * @param targetFragmentClass   目标fragment
     * @param includeTargetFragment 是否包含该fragment
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment);
    }

    /**
     * Pop the child fragment.
     */
    public void popChild() {
        mDelegate.popChild();
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findChildFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getChildFragmentManager(), fragmentClass);
    }
}