package com.xyp.mimi.im.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportFragment;
import com.xyp.mimi.im.common.LogTag;
import com.xyp.mimi.im.im.IMManager;
import com.xyp.mimi.im.model.ChatRoomAction;
import com.xyp.mimi.im.model.ChatRoomResult;
import com.xyp.mimi.im.utils.ToastUtils;
import com.xyp.mimi.im.viewmodel.AppViewModel;
import com.xyp.mimi.im.utils.log.SLog;

import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * 主界面子界面-发现界面
 */
public class MainDiscoveryFragment extends BaseSupportFragment {
    private AppViewModel appViewModel;
    private List<ChatRoomResult> latestChatRoomList;



    @Override
    public void onInitViewModel() {
        super.onInitViewModel();

        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);

        // 获取聊天室列表
        appViewModel.getChatRoonList().observe(this, listResource -> {
            List<ChatRoomResult> chatRoomResultList = listResource.data;
            if (chatRoomResultList != null) {
                latestChatRoomList = new ArrayList<>();
                /**
                 * 筛选出结果中 type 为 chatroom 的结果
                 */
                for (ChatRoomResult roomResult : chatRoomResultList) {
                    if ("chatroom".equals(roomResult.getType())) {
                        latestChatRoomList.add(roomResult);
                    }
                }
            }
        });


        /*
         * 以下代码使用 lambda 表达式会崩溃，因为 lambda 特性复用时注册同一个 Observer 时引发崩溃
         */
        // 监听聊天室加入状态
        IMManager.getInstance().getChatRoomAction().observe(this, new Observer<ChatRoomAction>() {
            @Override
            public void onChanged(ChatRoomAction chatRoomAction) {
                if (chatRoomAction.status == ChatRoomAction.Status.ERROR) {
                    ToastUtils.showToast(R.string.discovery_chat_room_join_failure);
                } else {
                    SLog.d(LogTag.IM, "ChatRoom action, status: " + chatRoomAction.status.name() + " - ChatRoom id:" + chatRoomAction.roomId);
                }
            }
        });

    }

    @OnClick({R.id.discovery_ll_chat_room_1, R.id.discovery_ll_chat_room_2,R.id.discovery_ll_chat_room_3,R.id.discovery_ll_chat_room_4,R.id.discovery_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.discovery_ll_chat_room_1:
                enterChatRoom(0, getString(R.string.discovery_chat_room_one));
                break;
            case R.id.discovery_ll_chat_room_2:
                enterChatRoom(1, getString(R.string.discovery_chat_room_two));
                break;
            case R.id.discovery_ll_chat_room_3:
                enterChatRoom(2, getString(R.string.discovery_chat_room_three));
                break;
            case R.id.discovery_ll_chat_room_4:
                enterChatRoom(3, getString(R.string.discovery_chat_room_four));
                break;
            case R.id.discovery_friend:
                ArmsUtils.snackbarText("跳转到朋友圈");
                break;
            default:
                break;
        }
    }

    /**
     * 进入聊天室
     *
     * @param roomIndex
     * @param roomTitle
     */
    private void enterChatRoom(int roomIndex, String roomTitle) {
        if (roomIndex >= (latestChatRoomList != null ? latestChatRoomList.size() : 0)) {
            ToastUtils.showToast(R.string.discovery_join_chat_room_error);
            appViewModel.requestChatRoomList();
            return;
        }

        ChatRoomResult chatRoomResult = latestChatRoomList.get(roomIndex);
        String roomId = chatRoomResult.getId();

        RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.CHATROOM, roomId, roomTitle);
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_discovery, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
