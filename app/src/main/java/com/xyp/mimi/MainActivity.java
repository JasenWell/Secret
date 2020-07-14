package com.xyp.mimi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xyp.mimi.im.common.IntentExtra;
import com.xyp.mimi.im.db.model.FriendShipInfo;
import com.xyp.mimi.im.event.MessageEvent;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.model.Status;
import com.xyp.mimi.im.model.VersionInfo;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.im.ui.activity.AddFriendActivity;
import com.xyp.mimi.im.ui.activity.SelectCreateGroupActivity;
import com.xyp.mimi.im.ui.activity.SelectSingleFriendActivity;
import com.xyp.mimi.im.ui.dialog.MorePopWindow;
import com.xyp.mimi.im.ui.fragment.MainContactsListFragment;
import com.xyp.mimi.im.ui.fragment.MainConversationListFragment;
import com.xyp.mimi.im.ui.fragment.MainDiscoveryFragment;
import com.xyp.mimi.im.ui.fragment.MainMeFragment;
import com.xyp.mimi.im.ui.view.MainBottomTabGroupView;
import com.xyp.mimi.im.ui.view.MainBottomTabItem;
import com.xyp.mimi.im.ui.view.SealTitleBar;
import com.xyp.mimi.im.ui.widget.DragPointView;
import com.xyp.mimi.im.ui.widget.TabGroupView;
import com.xyp.mimi.im.ui.widget.TabItem;
import com.xyp.mimi.im.utils.log.SLog;
import com.xyp.mimi.im.viewmodel.AppViewModel;
import com.xyp.mimi.im.viewmodel.MainViewModel;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import me.yokeyword.fragmentation.ISupportFragment;
import timber.log.Timber;
//
public class MainActivity extends BaseSupportActivity implements MorePopWindow.OnPopWindowItemClickListener{
    public static final String PARAMS_TAB_INDEX = "tab_index";
    private static final int REQUEST_START_CHAT = 0;
    private static final int REQUEST_START_GROUP = 1;
    private static final String TAG = "MainActivity";

    @BindView(R.id.vp_main_container)
    ViewPager vpFragmentContainer;

    @BindView(R.id.tg_bottom_tabs)
    MainBottomTabGroupView tabGroupView;

    @BindView(R.id.title_bar)
    SealTitleBar titleBar;

    private ImageView ivSearch;
    private ImageView ivMore;
    private AppViewModel appViewModel;
    public MainViewModel mainViewModel;
    int currentIndex = 0;

    /**
     * tab 项枚举
     */
    public enum Tab {
        /**
         * 聊天
         */
        CHAT(0),
        /**
         * 联系人
         */
        CONTACTS(1),
        /**
         * 发现
         */
        FIND(2),
        /**
         * 我的
         */
        ME(3);

        private int value;

        Tab(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Tab getType(int value) {
            for (Tab type : Tab.values()) {
                if (value == type.getValue()) {
                    return type;
                }
            }
            return null;
        }
    }

    /**
     * tabs 的图片资源
     */
    private int[] tabImageRes = new int[]{
            R.drawable.seal_tab_chat_selector,
            R.drawable.seal_tab_contact_list_selector,
            R.drawable.seal_tab_find_selector,
            R.drawable.seal_tab_me_selector
    };

    /**
     * 各个 Fragment 界面
     */
    private List<Fragment> fragments = new ArrayList<>();


//    @OnClick({R.id.btn_search, R.id.btn_more})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_search:
//                ArmsUtils.snackbarText("查询功能开发中...");
//                Intent intent = new Intent(MainActivity.this, SealSearchActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.btn_more:
//                ArmsUtils.snackbarText("更多功能开发中...");
//                break;
//        }
//    }
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.main_activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        RetrofitUrlManager.getInstance().setGlobalDomain((String) SpUtils.get(mContext, AppConstant.Api.BASE_URL,Api.APP_DOMAIN));
        requestPermissions();
//        Main1AgreementView mainAgreementView = new Main1AgreementView(mContext,R.style.dialog);
//        mainAgreementView.setCancelable(false);
//
//        if(SPUtils.getInstance().getString(AppConstant.SP_IS_FIRST_ENTER_APP).equals("")){
//            mainAgreementView.showDialog();
//        }
        titleBar.setType(SealTitleBar.Type.HOME);
        titleBar.setTitle("消息");
        titleBar.setBtnRightDrawable(R.drawable.seal_ic_main_more);
        titleBar.setOnBtnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndex == 0) {
                    MorePopWindow morePopWindow = new MorePopWindow(MainActivity.this, MainActivity.this);
                    morePopWindow.showChildView(R.id.btn_start_chat);
                    morePopWindow.hideChildView(R.id.btn_create_group);
                    morePopWindow.hideChildView(R.id.btn_scan);
                    morePopWindow.showPopupWindow(v);
                }else {
                    onAddFriendClick();
                }

            }
        });
        int tabIndex = getIntent().getIntExtra(PARAMS_TAB_INDEX, Tab.CHAT.getValue());
        // 初始化底部 tabs
        initTabs();
        // 初始化 fragment 的 viewpager
        initFragmentViewPager();

        // 设置当前的选项为聊天界面
        tabGroupView.setSelected(tabIndex);
        initViewModel();
        clearBadgeStatu();
        showRedCircle(Tab.CHAT.getValue(),false);
    }

    @Subscribe
    public void onEventMainThread(MessageEvent event){
        //接收到发布者发布的事件后，进行相应的处理操作
        if(event.getType() == MessageEvent.EventType.REFRESH_FRIEND_LIST){
            switchTitle(1);
        }
    }

    private void switchTitle(int position){
        if (position == Tab.ME.getValue()) {
            currentIndex = 3;
            titleBar.setTitle("我");
            titleBar.hideBtnRight();
        }else  if(position == Tab.CHAT.getValue()){
            currentIndex = 0;
            titleBar.setTitle("会话");
            titleBar.showBtnRight();
            titleBar.setBtnRightDrawable(R.drawable.seal_ic_main_more);
        }else  if(position == Tab.CONTACTS.getValue()){
            currentIndex = 1;
            titleBar.setTitle("通讯录");
//            titleBar.setBtnRightDrawable(R.drawable.ease_new_friends_icon);
            titleBar.showBtnRight();
        }else  if(position == Tab.FIND.getValue()){
            currentIndex = 2;
            titleBar.setTitle("发现");
            titleBar.hideBtnRight();
        }
    }

    /**
     * 初始化 Tabs
     */
    private void initTabs() {
        // 初始化 tab
        List<TabItem> items = new ArrayList<>();
        String[] stringArray = getResources().getStringArray(R.array.tab_names);

        for (Tab tab : Tab.values()) {
            TabItem tabItem = new TabItem();
            tabItem.id = tab.getValue();
            tabItem.text = stringArray[tab.getValue()];
            tabItem.drawable = tabImageRes[tab.getValue()];
            items.add(tabItem);
        }

        tabGroupView.initView(items, new TabGroupView.OnTabSelectedListener() {
            @Override
            public void onSelected(View view, TabItem item) {
                // 当点击 tab 的后， 也要切换到正确的 fragment 页面
                int currentItem = vpFragmentContainer.getCurrentItem();
                if (currentItem != item.id) {
                    // 切换布局
                    vpFragmentContainer.setCurrentItem(item.id);
                    // 如果是我的页面， 则隐藏红点
                    if (item.id == Tab.ME.getValue()) {
                        showRedCircle(Tab.ME.getValue(),false);
                    }

                    switchTitle(item.id);
                }
            }
        });

        tabGroupView.setOnTabDoubleClickListener(new MainBottomTabGroupView.OnTabDoubleClickListener() {
            @Override
            public void onDoubleClick(TabItem item, View view) {
                // 双击定位到某一个未读消息位置
                if (item.id == Tab.CHAT.getValue()) {
                    MainConversationListFragment fragment = (MainConversationListFragment) fragments.get(Tab.CHAT.getValue());
                    fragment.focusUnreadItem();
                }
            }
        });

        // 未读数拖拽
        ((MainBottomTabItem) tabGroupView.getView(Tab.CHAT.getValue())).setTabUnReadNumDragListener(new DragPointView.OnDragListencer() {

            @Override
            public void onDragOut() {
                ((MainBottomTabItem) tabGroupView.getView(Tab.CHAT.getValue())).setNumVisibility(View.GONE);
                showToast(getString(R.string.seal_main_toast_unread_clear_success));
                clearUnreadStatus();
            }
        });
        ((MainBottomTabItem) tabGroupView.getView(Tab.CHAT.getValue())).setNumVisibility(View.GONE);//跟背景红圈相关联,默认没有
    }

    private void showRedCircle(int id,boolean show){
        MainBottomTabItem bottomTabItem = tabGroupView.getView(id);
        bottomTabItem.setRedVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * 初始化 initFragmentViewPager
     */
    private void initFragmentViewPager() {
        fragments.add(new MainConversationListFragment());
        fragments.add(new MainContactsListFragment());
        fragments.add(new MainDiscoveryFragment());
        fragments.add(new MainMeFragment());

        // ViewPager 的 Adpater
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        vpFragmentContainer.setAdapter(fragmentPagerAdapter);
        vpFragmentContainer.setOffscreenPageLimit(fragments.size());
        // 设置页面切换监听
        vpFragmentContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 当页面切换完成之后， 同时也要把 tab 设置到正确的位置
                if (position == Tab.ME.getValue()) {
                    showRedCircle(Tab.ME.getValue(),false);
                }
                switchTitle(position);
                tabGroupView.setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化ViewModel
     */
    private void initViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getHasNewVersion().observe(this, new Observer<Resource<VersionInfo.AndroidVersion>>() {
            @Override
            public void onChanged(Resource<VersionInfo.AndroidVersion> resource) {
                if (resource.status == Status.SUCCESS && resource.data != null) {
                    if (tabGroupView.getSelectedItemId() != Tab.ME.getValue()) {
                        showRedCircle(Tab.ME.getValue(),true);
                    }
                }
            }
        });

        // 未读消息
        mainViewModel.getUnReadNum().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                MainBottomTabItem chatTab = (MainBottomTabItem) tabGroupView.getView(Tab.CHAT.getValue());
                if (count == 0) {
                    chatTab.setNumVisibility(View.GONE);
                } else if (count > 0 && count < 100) {
                    chatTab.setNumVisibility(View.VISIBLE);
                    chatTab.setNum(String.valueOf(count));
                } else {
                    chatTab.setVisibility(View.VISIBLE);
                    chatTab.setNum(getString(R.string.seal_main_chat_tab_more_read_message));
                }
            }
        });

        // 新朋友数量
        mainViewModel.getNewFriendNum().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                MainBottomTabItem chatTab = tabGroupView.getView(Tab.CONTACTS.getValue());
                showRedCircle(Tab.CONTACTS.getValue(),count > 0);
            }
        });

        mainViewModel.getPrivateChatLiveData().observe(this, new Observer<FriendShipInfo>() {
            @Override
            public void onChanged(FriendShipInfo friendShipInfo) {
                RongIM.getInstance().startPrivateChat(MainActivity.this,
                        friendShipInfo.getUser().getId(),
                        TextUtils.isEmpty(friendShipInfo.getDisplayName()) ?
                                friendShipInfo.getUser().getNickname() : friendShipInfo.getDisplayName());
            }
        });

    }


    /**
     * 清理未读消息状态
     */
    private void clearUnreadStatus() {
        if (mainViewModel != null) {
            mainViewModel.clearMessageUnreadStatus();
        }
    }


    //清除华为的角标
    private void clearBadgeStatu() {
        if (Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
            try {
                String packageName = getPackageName();
                String launchClassName = getPackageManager()
                        .getLaunchIntentForPackage(packageName)
                        .getComponent().getClassName();
                Bundle bundle = new Bundle();//需要存储的数据
                bundle.putString("package", packageName);//包名
                bundle.putString("class", launchClassName);//启动的Activity完整名称
                bundle.putInt("badgenumber", 0);//未读信息条数清空
                getContentResolver().call(
                        Uri.parse("content://com.huawei.android.launcher.settings/badge/"),
                        "change_badge", null, bundle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @SuppressLint("CheckResult")
    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(MainActivity.this);
        rxPermission
                .requestEach(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        Timber.e("%s is granted.", permission.name);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        Timber.d("%s is denied. More info should be provided.", permission.name);
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        Timber.e("%s is denied.", permission.name);
                    }
                });
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
            ArmsUtils.snackbarText("再按一次退出程序");
            clickTime = System.currentTimeMillis();
        } else {
            Log.e("MainActivity", "exit application");
            this.finish();
            System.exit(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_START_CHAT:
                    mainViewModel.startPrivateChat(data.getStringExtra(IntentExtra.STR_TARGET_ID));
                    break;
                case REQUEST_START_GROUP:
//                    ArrayList<String> memberList = data.getStringArrayListExtra(IntentExtra.LIST_STR_ID_LIST);
//                    SLog.i(TAG, "memberList.size = " + memberList.size());
//                    Intent intent = new Intent(this, CreateGroupActivity.class);
//                    intent.putExtra(IntentExtra.LIST_STR_ID_LIST, memberList);
//                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }


    }


    /**
     * 发起单聊
     */
    @Override
    public void onStartChartClick() {
        Intent intent = new Intent(this, SelectSingleFriendActivity.class);
        startActivityForResult(intent, REQUEST_START_CHAT);
    }

    /**
     * 创建群组
     */
    @Override
    public void onCreateGroupClick() {
        Intent intent = new Intent(this, SelectCreateGroupActivity.class);
        startActivityForResult(intent, REQUEST_START_GROUP);
    }

    /**
     * 添加好友
     */
    @Override
    public void onAddFriendClick() {
        Intent intent = new Intent(this, AddFriendActivity.class);
        startActivity(intent);
    }

    /**
     * 扫一扫
     */
    @Override
    public void onScanClick() {
//        Intent intent = new Intent(this, ScanActivity.class);
//        startActivity(intent);
    }

}
