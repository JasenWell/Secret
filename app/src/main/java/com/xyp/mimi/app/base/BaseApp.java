package com.xyp.mimi.app.base;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.billy.android.loading.Gloading;
import com.jess.arms.base.App;
import com.jess.arms.base.delegate.AppDelegate;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.Preconditions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.ui.adapter.SpecialAdapter;

//
public class BaseApp extends Application implements App {

    private AppDelegate mAppDelegate;

    private static Context mContext;

    /**
     * nickname for current user, the nickname instead of ID be shown when user receive notification from APNs
     */
    public static String currentUserNick = "";
    public static Context getInstance(){
        return  mContext;
    }

    //static ???????????
    static {
        //?????Header???
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.black);//????????
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("??? %s"));//?????Header???? ?????Header
            }
        });
        //?????Footer???
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //?????Footer???? BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = base ;
        if(this.mAppDelegate == null){
            this.mAppDelegate = new AppDelegate(base);
        }
        this.mAppDelegate.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (this.mAppDelegate != null) {
            this.mAppDelegate.onCreate(this);
        }
        Gloading.initDefault(new SpecialAdapter());

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (this.mAppDelegate != null) {
            this.mAppDelegate.onTerminate(this);
        }
    }

    @NonNull
    @Override
    public AppComponent getAppComponent() {
        Preconditions.checkNotNull(this.mAppDelegate, "%s cannot be null", new Object[]{AppDelegate.class.getName()});
        Preconditions.checkState(this.mAppDelegate instanceof App, "%s must be implements %s", new Object[]{AppDelegate.class.getName(), App.class.getName()});
        return ((App) this.mAppDelegate).getAppComponent();
    }
}
