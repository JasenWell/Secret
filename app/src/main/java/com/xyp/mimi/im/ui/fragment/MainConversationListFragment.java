package com.xyp.mimi.im.ui.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.ImmersionOwner;
import com.gyf.barlibrary.ImmersionProxy;
import com.xyp.mimi.MainActivity;
import com.xyp.mimi.R;
import com.xyp.mimi.im.db.model.GroupNoticeInfo;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.model.Status;
import com.xyp.mimi.im.ui.adapter.ConversationListAdapterEx;
import com.xyp.mimi.im.viewmodel.GroupNoticeInfoViewModel;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.model.Conversation;

public class MainConversationListFragment extends ConversationListFragment implements ImmersionOwner {

    private ConversationListAdapterEx conversationListAdapterEx;
    private GroupNoticeInfoViewModel groupNoticeInfoViewModel;
    private MainActivity mainActivity;
    protected ImmersionBar mImmersionBar;

    /**
     * ImmersionBar代理类
     */
    private ImmersionProxy immersionProxy = new ImmersionProxy(this);

    Conversation.ConversationType[] mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
            Conversation.ConversationType.GROUP,
            Conversation.ConversationType.PUBLIC_SERVICE,
            Conversation.ConversationType.APP_PUBLIC_SERVICE,
            Conversation.ConversationType.SYSTEM,
            Conversation.ConversationType.DISCUSSION
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUri();
        initViewModel();
    }

    @Override
    public void onVisible() {

    }

    @Override
    public void onInvisible() {

    }

    @Override
    public boolean immersionBarEnabled() {
        return true;
    }

    @Override
    public void initImmersionBar() {
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    private void setUri() {
        // 此处是因为适配 Androidx 库， 所以才会有红色警告， 但是不影响编译运行。
        // 设置会话列表所要支持的会话类型。 并且会话类型后面的  boolean 值表示此会话类型是否内举。
        Uri uri = Uri.parse("rong://" +
                getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .build();

        setUri(uri);
    }


    @Override
    public ConversationListAdapter onResolveAdapter(Context context) {
        if (conversationListAdapterEx == null) {
            conversationListAdapterEx = new ConversationListAdapterEx(context);
            conversationListAdapterEx.setGroupApplyMessageListener(new ConversationListAdapterEx.GroupApplyMessageListener() {
                @Override
                public void updateGroupUnReadCount(int count) {
                    updateGroupNotifyUnReadCount(count);
                }
            });
        }
        return conversationListAdapterEx;
    }

    /**
     * 更新群通知未读消息的数量
     *
     * @param num
     */
    public void updateGroupNotifyUnReadCount(int num) {
        if (mainActivity != null) {
            mainActivity.mainViewModel.setGroupNotifyUnReadNum(num);
        }
    }

    private void initViewModel() {
        groupNoticeInfoViewModel = ViewModelProviders.of(this).get(GroupNoticeInfoViewModel.class);
        groupNoticeInfoViewModel.getGroupNoticeInfo().observe(this, new Observer<Resource<List<GroupNoticeInfo>>>() {
            @Override
            public void onChanged(Resource<List<GroupNoticeInfo>> listResource) {
                if (listResource.status != Status.LOADING) {
                    if (conversationListAdapterEx != null) {
                        conversationListAdapterEx.updateNoticeInfoData(listResource.data);
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        immersionProxy.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        immersionProxy.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        immersionProxy.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        immersionProxy.onConfigurationChanged(newConfig);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        immersionProxy.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        immersionProxy.setUserVisibleHint(isVisibleToUser);
    }
}
