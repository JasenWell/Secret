package com.xyp.mimi.app;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.http.log.RequestInterceptor;
import com.jess.arms.integration.ConfigModule;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DataHelper;
import com.squareup.leakcanary.RefWatcher;
import com.xyp.mimi.BuildConfig;
import com.xyp.mimi.app.config.ActivityLifecycleCallbacksImpl;
import com.xyp.mimi.app.config.AppLifecyclesImpl;
import com.xyp.mimi.app.config.applyOptions.MyGlobalHttpHandler;
import com.xyp.mimi.app.config.applyOptions.MyGsonConfiguration;
import com.xyp.mimi.app.config.applyOptions.MyResponseErrorListener;
import com.xyp.mimi.app.config.applyOptions.MyRxCacheConfiguration;
import com.xyp.mimi.mvp.http.api.Api;

import java.io.File;
import java.util.List;

import timber.log.Timber;

public final class GlobalConfiguration implements ConfigModule {

    public static String PRE_HEADER_TOKEN = "Bearer ";

    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        if (!BuildConfig.LOG_DEBUG) { //Release 时,让框架不再打印 Http 请求和响应的信息
            builder.printHttpLogLevel(RequestInterceptor.Level.NONE);
        }
        Timber.e("applyOptions");
        //使用builder可以为框架配置一些配置信息
        builder.baseurl(Api.APP_DOMAIN)
                //使用自定义App-Token
//                .addInterceptor(new HeaderInterceptor()) //头部拦截器
                .rxCacheConfiguration(new MyRxCacheConfiguration())//缓存
                .globalHttpHandler(new MyGlobalHttpHandler())//全局捕捉 Http 请求和响应
                .responseErrorListener(new MyResponseErrorListener())//返回出错
                .cacheFile(new File(DataHelper.getCacheFile(context), "rxCache"))
                .gsonConfiguration(new MyGsonConfiguration());
    }

    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {
        lifecycles.add(new AppLifecyclesImpl());
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        lifecycles.add(new ActivityLifecycleCallbacksImpl());
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
        lifecycles.add(new FragmentManager.FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
                // 在配置变化的时候将这个 Fragment 保存下来,在 Activity 由于配置变化重建时重复利用已经创建的 Fragment。
                // https://developer.android.com/reference/android/app/Fragment.html?hl=zh-cn#setRetainInstance(boolean)
                // 如果在 XML 中使用 <Fragment/> 标签,的方式创建 Fragment 请务必在标签中加上 android:id 或者 android:tag 属性,否则 setRetainInstance(true) 无效
                // 在 Activity 中绑定少量的 Fragment 建议这样做,如果需要绑定较多的 Fragment 不建议设置此参数,如 ViewPager 需要展示较多 Fragment
                f.setRetainInstance(true);
            }

            @Override
            public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
                ((RefWatcher) ArmsUtils
                        .obtainAppComponentFromContext(f.getActivity())
                        .extras()
                        .get(RefWatcher.class.getName()))
                        .watch(f);
            }
        });
    }

}

