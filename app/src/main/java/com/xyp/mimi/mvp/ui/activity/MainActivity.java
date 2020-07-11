package com.xyp.mimi.mvp.ui.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jess.arms.di.component.AppComponent;
import com.next.easynavigation.view.EasyNavigationBar;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.ui.fragment.MyFragment;
import com.xyp.mimi.mvp.ui.fragment.NullFragment;
import com.xyp.mimi.mvp.ui.fragment.cart.CartFragment;
import com.xyp.mimi.mvp.ui.fragment.project.ProjectFragment;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseSupportActivity {

    @BindView(R.id.navigitionBar)
    EasyNavigationBar navigitionBar;
    private List<Fragment> fragments = new ArrayList<>();

    private String[] tabText = {"消息", "通讯录","发现","我的"};
    private int[] normalIcon = { R.mipmap.message,R.mipmap.program,R.mipmap.yingxiao,R.mipmap.me};
    //选中时icon
    private int[] selectIcon ={ R.mipmap.message_select,R.mipmap.program_select,R.mipmap.yingxiao_select,R.mipmap.me_select};


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarColor(R.color.white)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

//    @Override
//    protected void initRxBus() {
//        super.initRxBus();
//        getRxBusEvent(MessageNumberEvent.class, new MySubscriber<MessageNumberEvent>() {
//            @Override
//            public void onMyNext(MessageNumberEvent event) {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("targetType",event.getTargetType());
//                map.put("innerId", event.getInnerId());
//                map.put("userId", SPUtils.getInstance().getString(Config.user_id));
////                Log.i("MainActivity", event.getTargetType()+"=============="+event.getInnerId());
//                ApiRequest.getMessageNumber(map, new MyCallBack<MessageBean>(mContext) {
//                    @Override
//                    public void onSuccess(MessageBean s) {
//                        Log.i("MainActivity", s+"后台推送的消息数");
//
//                        ApiRequest.getReddotList(new MyCallBack<ReddotBean>(mContext) {
//                            @Override
//                            public void onSuccess(ReddotBean reddotBean) {
//                                //更新下面的小红点
//                                navigitionBar.setMsgPointCount(3,s.getNewMessagesByUser()+reddotBean.getRedNum());
//                                //更新消息列表, 点赞红点，评论红点
//                                RxBus.getInstance().post(new MessageListEvent(reddotBean.getLRedButton(),reddotBean.getCrRedButton()));
//                            }
//                        });
//
//
//                    }
//                });
//            }
//        });
//        //更新底部栏消息
//        getRxBusEvent(MessageLoginNumberEvent.class, new MySubscriber<MessageLoginNumberEvent>() {
//            @Override
//            public void onMyNext(MessageLoginNumberEvent event) {
//                navigitionBar.setMsgPointCount(3,event.getNumber());
//            }
//        });
//
//
//        // 也更新底部栏消息
//        getRxBusEvent(RefreshReddotEvent.class, new MySubscriber<RefreshReddotEvent>() {
//            @Override
//            public void onMyNext(RefreshReddotEvent event) {
//                updateBottomRedDot();
//            }
//        });
//
//
//
//
//    }
//    private void updateBottomRedDot(){
//        //更新底部消息数量
//        Map<String, String> map2 = new HashMap<String, String>();
//        map2.put("userId", SPUtils.getInstance().getString(Config.user_id));
//        com.petzm.training.module.home.network.ApiRequest.getMessageNumberLogin(map2, new MyCallBack<MessageBean>(mContext) {
//            @Override
//            public void onSuccess(MessageBean s) {
//                com.petzm.training.module.home.network.ApiRequest.getReddotList(new MyCallBack<ReddotBean>(mContext) {
//                    @Override
//                    public void onSuccess(ReddotBean reddotBean) {
////                        //更新下面的小红点
////                        navigitionBar.setMsgPointCount(3,s.getNewMessagesByUser()+reddotBean.getRedNum());
////                        //更新消息列表, 点赞红点，评论红点
////                        RxBus.getInstance().post(new MessageListEvent(reddotBean.getLRedButton(),reddotBean.getCrRedButton()));
//
//                        Log.d("MessageFragment",s.getNewMessagesByUser()+"======"+reddotBean.getRedNum());
//                        RxBus.getInstance().post(new MessageLoginNumberEvent(s.getNewMessagesByUser()+reddotBean.getRedNum()));
//                    }
//                });
//
//            }
//        });
//    }


//        UMConfigure.setLogEnabled(true);
//        PushAgent mPushAgent = PushAgent.getInstance(getApplicationContext());
//        mPushAgent.enable(new IUmengCallback() {
//            @Override
//            public void onSuccess() {
//                Log.i("mPushAgent", "再次开启推送");
//            }
//            @Override
//            public void onFailure(String s, String s1) {
//            }
//        });
//
//        if(!NotificationUtil.isNotificationEnabled(mContext)){
//            long systemTime = System.currentTimeMillis()/1000;
//            long databaseTime = SPUtils.getInstance().getLong(Config.time);
//            Log.i("MainActivity", systemTime+"系统时间");
//            Log.i("MainActivity", databaseTime+"本地时间");
//            Log.i("MainActivity", systemTime-databaseTime+"时间");
//            //第一次登陆 没有存时间，如果忽略了则存储当前时间，否则存为0
//            if(SPUtils.getInstance().getLong(Config.time)<1){
//                MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
//                mDialog.setMessage("您还未开启通知,前往开启?");
//                mDialog.setNegativeButton("忽略",(dialog, which) -> dialog.dismiss());
//                SPUtils.getInstance().put(Config.time,systemTime);//
//                mDialog.setPositiveButton((dialog, which) -> {
//                    dialog.dismiss();
//                    PermissionUtils.launchAppDetailsSettings();
//                });
//                mDialog.create().show();
//            }
//            //如果系统时间大于存储时间3天则弹出
//            else if(systemTime- SPUtils.getInstance().getLong(Config.time)>259200){
//                MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
//                mDialog.setMessage("您还未开启通知,前往开启?");
//                mDialog.setNegativeButton("忽略",(dialog, which) -> dialog.dismiss());
//                SPUtils.getInstance().put(Config.time,systemTime);//
//                mDialog.setPositiveButton((dialog, which) -> {
//                    dialog.dismiss();
//                    PermissionUtils.launchAppDetailsSettings();
//                });
//                mDialog.create().show();
//            }
//        }

//        navigitionBar.setMsgPointCount(3, SPUtils.getInstance().getInt(Config.red_dot_number));
        //如果没有存过数据，则默认为仅 wifi自动播放
//        if(SPUtils.getInstance().getString(Config.user_net_select).equals("")){
//            SPUtils.getInstance().put(Config.user_net_select, "1");
//        }
//        SPUtils.getInstance().put(Config.Toast4G,true);//弹4g提示
//
//        MainAgreementView mainAgreementView = new MainAgreementView(mContext,R.style.dialog);
//        mainAgreementView.setCancelable(false);
//
//        if(SPUtils.getInstance().getString(Config.SP_IS_FIRST_ENTER_APP).equals("")){
//            mainAgreementView.showDialog();
//        }




    @Override
    protected void onResume() {
        super.onResume();
    }

//    private void updateApp() {
//        ApiRequest.getVersion(new MyCallBack<VersionBean>(mContext) {
//            @Override
//            public void onSuccess(VersionBean obj) {
//                //分享用
//                SPUtils.getInstance().put(Constant.IParam.shareTitle,obj.getShareTitle());
//                SPUtils.getInstance().put(Constant.IParam.shareDescription,obj.getShareDescription());
//                SPUtils.getInstance().put(Constant.IParam.shareUrl,obj.getShareUrl());
//                SPUtils.getInstance().put(Constant.IParam.shareLogo,obj.getShareLogo());
//                SPUtils.getInstance().put(Constant.IParam.shareBackGroundPicture,obj.getShareBackGroundPicture());
//                if(obj.getVersionCode()>getAppVersionCode(getApplicationContext())){
//                    SPUtils.getInstance().put(Config.appDownloadUrl,obj.getApkUrl());
//                    SPUtils.getInstance().put(Constant.IParam.serviceCode,obj.getQrCodeUrl());
//                    SPUtils.getInstance().put(Constant.IParam.servicePhone,obj.getPhone());
//                    SPUtils.getInstance().put(Config.appHasNewVersion,true);
////                    MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
////                    mDialog.setMessage("检测到app有新版本\n是否更新?");
////                    mDialog.setNegativeButton((dialog, which) -> dialog.dismiss());
////                    mDialog.setPositiveButton((dialog, which) -> {
////                        dialog.dismiss();
////                        downloadApp(obj.getApkUrl());
////                    });
////                    mDialog.create().show();
//                }else{
//                    SPUtils.getInstance().put(Constant.IParam.serviceCode,obj.getQrCodeUrl());
//                    SPUtils.getInstance().put(Constant.IParam.servicePhone,obj.getPhone());
//                    SPUtils.getInstance().put(Config.appHasNewVersion,false);
//                }
//            }
//        });
//    }


    public static int getAppVersionCode(Context context) {
        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private long clickTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再次点击退出",Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            Log.e("MainActivity", "exit application");
            this.finish();
            System.exit(0);
        }
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main1;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        fragments.add(new ProjectFragment());
        fragments.add(new NullFragment());
        fragments.add(new CartFragment());
        fragments.add(new MyFragment());

        navigitionBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .iconSize(25)
                .tabTextSize(10)   //Tab文字大小
                .tabTextTop(2)     //Tab文字距Tab图标的距离
                .scaleType(ImageView.ScaleType.CENTER_INSIDE)
                .navigationHeight(48)  //导航栏高度
                .lineHeight(1)         //分割线高度  默认1px
                .msgPointLeft(-8)  //调节数字消息的位置msgPointLeft msgPointTop（看文档说明）
                .msgPointTop(-15)
                .msgPointTextSize(8)  //数字消息中字体大小
                .msgPointSize(16)    //数字消息红色背景的大小
                .lineColor(Color.parseColor("#dfdfdf"))
                .normalTextColor(Color.parseColor("#6D7278"))   //Tab未选中时字体颜色
                .selectTextColor(Color.parseColor("#FF3333"))   //Tab选中时字体颜色
                .canScroll(false)
                .build();

//        updateApp();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("userId", SPUtils.getInstance().getString(Config.user_id));
//        ApiRequest.getMessageNumberLogin(map, new MyCallBack<MessageBean>(mContext) {
//            @Override
//            public void onSuccess(MessageBean s) {
//                ApiRequest.getReddotList(new MyCallBack<ReddotBean>(mContext) {
//                    @Override
//                    public void onSuccess(ReddotBean reddotBean) {
//                        //更新下面的小红点
//                        navigitionBar.setMsgPointCount(3,s.getNewMessagesByUser()+reddotBean.getRedNum());
//                        //更新消息列表, 点赞红点，评论红点
//                        RxBus.getInstance().post(new MessageListEvent(reddotBean.getLRedButton(),reddotBean.getCrRedButton()));
//                    }
//                });
////                navigitionBar.setMsgPointCount(3,s.getNewMessagesByUser());
//
//            }
//        });
    }
}
