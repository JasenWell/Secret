package com.xyp.mimi.mvp.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportFragment;
import com.xyp.mimi.mvp.contract.TaskWebContract;
import com.xyp.mimi.mvp.http.api.Api;
import com.xyp.mimi.mvp.http.entity.TaskData;
import com.xyp.mimi.mvp.http.entity.product.Product;
import com.xyp.mimi.mvp.presenter.TaskWeb1Presenter;
import com.xyp.mimi.mvp.utils.AppConstant;
import com.xyp.mimi.mvp.utils.RxTimerUtil;
import com.xyp.mimi.mvp.utils.SpUtils;


import butterknife.BindView;

public class AllWebFragment extends BaseSupportFragment<TaskWeb1Presenter> implements TaskWebContract.View, RxTimerUtil.RxAction{

    @BindView(R.id.webView)
    WebView webView;
    String url;
    @BindView(R.id.progressBarLoading)
    ProgressBar mLoadingProgress;

    RxTimerUtil rxTimerUtil;
    private String type;
    private String index;
    private String useId;

    public static AllWebFragment newInstance(String type, String index) {
        Bundle args = new Bundle();
//        args.putString(AppConstant.Api.TYPE, type + "");
//        args.putString(AppConstant.Api.INDEX, index + "");
        AllWebFragment fragment = new AllWebFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
//        DaggerTaskWeb1Component.builder()
//                .appComponent(appComponent)
//                .taskWeb1Module(new TaskWeb1Module(this))
//                .build()
//                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //懒加载
//        type = getArguments().getString(AppConstant.Api.TYPE, "");
//        index = getArguments().getString(AppConstant.Api.INDEX, "");
        mPresenter.getTask( SPUtils.getInstance().getString(AppConstant.User.USER_ID));
//        Log.d("web","useId"+type);
//        Log.d("web",getArguments().getString(AppConstant.Api.INDEX, ""));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

    private void getdata(String url) {

        webView.loadUrl(url);
        WebSettings settings = webView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setBuiltInZoomControls(false); //选择内置缩放机制与单独缩放控件；
        settings.setDisplayZoomControls(false); //是否显示缩放控件
        settings.setSupportZoom(false);  //是否支持缩放
//        webView.setInitialScale(100); //第一次显示的缩放比例，例子是120%;
        //注释掉 wb_img.setInitialScale(145); 使用下面代码，则自适应屏幕居中显示
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);//启用js
        webView.getSettings().setBlockNetworkImage(false);//解决图片不显示
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //设置加载进度条
                view.setWebChromeClient(new AllWebFragment.WebChromeClientProgress());
                return true;
            }
        });
        if(rxTimerUtil!=null){
            rxTimerUtil.cancel();
        }
        rxTimerUtil = new RxTimerUtil();
        rxTimerUtil.interval(14000, AllWebFragment.this);
    }

    @Override
    public void action(long number) {
        String useid =  SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        mPresenter.getTask( useid);
        Log.d("web","useiduseid"+    useid);
    }

    private class WebChromeClientProgress extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int progress) {
            if (mLoadingProgress != null) {
                mLoadingProgress.setProgress(progress);
                if (progress == 100) mLoadingProgress.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, progress);
        }
    }
    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void getTaskSuccess(TaskData taskData) {  //获取数据然后填充
        String userName = (String) SpUtils.get(mContext, AppConstant.User.USER_ID, "");
        String ip= Api.APP_DOMAIN;
//        if( SpUtils.get(mContext, AppConstant.User.JUESE,"vip").equals("vip")){
//            ip =  Api.APP_DOMAIN_VIP_H5;
//        }else{
//            ip  = Api.APP_DOMAIN_H5;
//        }
        url = ip+"renwu/renwu.asp?appid=" + taskData.getAppid() + "&kw=" + taskData.getKw() + "&ks=" + taskData.getKs() + "&ids=" + taskData.getIds() + "&u=" + taskData.getU() + "&n=" + userName;
        Log.d("weburl",url);
        getdata(url);
    }

    @Override
    public void getRecommendList(Product data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
//
//    @Override
//    public void action(long number) {  //请求任务
//        Log.d("web","useId"+type);
//        mPresenter.getTask(type);
//    }


//    @Override
//    public void onPause() {
//        super.onPause();
//        if(rxTimerUtil!=null){
//            rxTimerUtil.cancel();
//        }
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(rxTimerUtil!=null){
            rxTimerUtil.cancel();
        }

    }
}
