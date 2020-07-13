package com.xyp.mimi.app.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

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
import com.xyp.mimi.im.common.ErrorCode;
import com.xyp.mimi.im.contact.PhoneContactManager;
import com.xyp.mimi.im.im.IMManager;
import com.xyp.mimi.im.utils.SearchUtils;
import com.xyp.mimi.mvp.ui.activity.MainActivity;
import com.xyp.mimi.mvp.ui.adapter.SpecialAdapter;

import io.rong.imlib.ipc.RongExceptionHandler;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

//
public class BaseApp extends MultiDexApplication implements App {

    private AppDelegate mAppDelegate;

    private static Context mContext;
    /**
     * ???????
     */
    private boolean isAppInForeground;
    private String lastVisibleActivityName;
    private Intent nextOnForegroundIntent;
    private boolean isMainActivityIsCreated;
    private static BaseApp appInstance;

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
        appInstance = this;
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

        ErrorCode.init(this);

        /*
         * ?????????????
         */
        if (!getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            return;
        }

        /*
         * ??????????????
         */
        // ?????IM SDK???? SDK ?????????????
        IMManager.getInstance().init(this);
//        Stetho.initializeWithDefaults(this);

        SearchUtils.init(this);

        Thread.setDefaultUncaughtExceptionHandler(new RongExceptionHandler(this));

        // ???????
//        WXManager.getInstance().init(this);

        PhoneContactManager.getInstance().init(this);

        // ?? App ?????
        observeAppInBackground();
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


    /**
     * ??????????
     */
    private void observeAppInBackground() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if(activity instanceof MainActivity){
                    isMainActivityIsCreated = true;
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
                // ??????????????????
                if (isMainActivityIsCreated && !isAppInForeground && nextOnForegroundIntent != null) {
                    activity.startActivity(nextOnForegroundIntent);
                    nextOnForegroundIntent = null;
                }

                lastVisibleActivityName = activity.getClass().getSimpleName();
                isAppInForeground = true;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                String pauseActivityName = activity.getClass().getSimpleName();
                /*
                 * ?? Activity ??????????????????? onResume?
                 * ??????? onPause??????????????????????? onPause?
                 * ?????????????
                 */
                if (pauseActivityName.equals(lastVisibleActivityName)) {
                    isAppInForeground = false;
                }
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if(activity instanceof MainActivity){
                    isMainActivityIsCreated = false;
                }
            }
        });
    }

    /**
     * ?? App ?????
     *
     * @return
     */
    public boolean isAppInForeground() {
        return isAppInForeground;
    }

    /**
     * ???????? Activity ??
     *
     * @return
     */
    public String getLastVisibleActivityName() {
        return lastVisibleActivityName;
    }

    /**
     * ??? App ????????? intent?? intent ??????
     *
     * @param intent
     */
    public void setOnAppForegroundStartIntent(Intent intent) {
        nextOnForegroundIntent = intent;
    }

    /**
     * ????????????? intent
     *
     * @return
     */
    public Intent getLastOnAppForegroundStartIntent() {
        return nextOnForegroundIntent;
    }

    /**
     * ???????????
     * @return
     */
    public boolean isMainActivityCreated(){
        return isMainActivityIsCreated;
    }

    public static BaseApp getApplication() {
        return appInstance;
    }
}
