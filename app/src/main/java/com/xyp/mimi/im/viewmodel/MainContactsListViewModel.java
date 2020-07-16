package com.xyp.mimi.im.viewmodel;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xyp.mimi.R;
import com.xyp.mimi.im.bean.ResponseAddingFriendInfo;
import com.xyp.mimi.im.bean.ResponseWrapperInfo;
import com.xyp.mimi.im.db.model.FriendDetailInfo;
import com.xyp.mimi.im.db.model.FriendShipInfo;
import com.xyp.mimi.im.db.model.FriendStatus;
import com.xyp.mimi.im.db.model.UserInfo;
import com.xyp.mimi.im.im.IMManager;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.model.Status;
import com.xyp.mimi.im.net.hjh.imp.AsynModelImp;
import com.xyp.mimi.im.sp.UserCache;
import com.xyp.mimi.im.task.FriendTask;
import com.xyp.mimi.im.task.UserTask;
import com.xyp.mimi.im.ui.adapter.models.FunctionInfo;
import com.xyp.mimi.im.ui.adapter.models.ListItemModel;
import com.xyp.mimi.im.utils.SingleSourceLiveData;
import com.xyp.mimi.im.utils.SingleSourceMapLiveData;
import com.xyp.mimi.im.utils.log.SLog;
import io.rong.imlib.model.Message;
import io.rong.message.ContactNotificationMessage;

public class MainContactsListViewModel extends CommonListBaseViewModel {
    private static final String TAG = "MainContactsListFragmentViewModel";
    private final UserTask userTask;
    private FriendTask friendTask;

    private List<ListItemModel> functionList;
    private FriendShipInfo mySelfInfo;
    private List<ResponseAddingFriendInfo> friendList = new ArrayList<>();
    private Map<String, Integer> dotNumMap = new HashMap<>();

    private SingleSourceMapLiveData<Message, Integer> refreshItem;
    private SingleSourceLiveData<Integer> dotNumData = new SingleSourceLiveData<>();
    private SingleSourceLiveData<Resource<ResponseWrapperInfo>> allFriendInfo = new SingleSourceLiveData<>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1092){
                selectAddFriendsRequestlist();
            }
        }
    };

    public MainContactsListViewModel(@NonNull Application application) {
        super(application);
        friendTask = new FriendTask(application);
        userTask = new UserTask(application);

        refreshItem = new SingleSourceMapLiveData<>(new Function<Message, Integer>() {
            @Override
            public Integer apply(Message message) {
                if (message.getContent() instanceof ContactNotificationMessage) {
                    ContactNotificationMessage contactNotificationMessage = (ContactNotificationMessage) message.getContent();
                    if (contactNotificationMessage.getOperation().equals("Request") ||
                            contactNotificationMessage.getOperation().equals("AcceptResponse")) {
                        loadHjhAllFriendInfo();
                        //return setFunctionShowRedDot("1", true);
                        return 0;
                    }
                }
                return 0;
            }
        });

        // 添加好友通知监听
        refreshItem.setSource(IMManager.getInstance().getMessageRouter());
    }

    public void loadHjhAllFriendInfo() {
        allFriendInfo.setSource(friendTask.getHjhAllFriends(UserCache.getInstance().getCurrentUserId()));
    }

    public LiveData<Resource<ResponseWrapperInfo>> getLoadHjhAllFriendInfoResult() {
        return allFriendInfo;
    }


    @Override
    public void loadData() {
        loadFriendShip();
    }


    /**
     * 功能项
     *
     * @return
     */
    private List<ListItemModel> getFunctionList() {
        if (functionList == null) {
            functionList = new ArrayList<>();
            FunctionInfo functionInfo = new FunctionInfo("1", getApplication().getString(R.string.new_friends), R.drawable.default_fmessage);
            functionInfo.setShowArrow(false);
            functionList.add(createFunModel(functionInfo));
            functionInfo = new FunctionInfo("2", getApplication().getString(R.string.group), R.drawable.default_chatroom);
            functionInfo.setShowArrow(false);
            functionList.add(createFunModel(functionInfo));
//            functionInfo = new FunctionInfo("3", getApplication().getString(R.string.public_service), R.drawable.default_servicebrand_contact);
//            functionInfo.setShowArrow(false);
//            functionList.add(createFunModel(functionInfo));
        }
        return functionList;
    }

    private void selectAddFriendsRequestlist(){
        LiveData<Resource<ResponseWrapperInfo>> userInfo = userTask.selectAddFriendsRequestlist(IMManager.getInstance().getCurrentId());
        conversationLiveData.addSource(userInfo, new Observer<Resource<ResponseWrapperInfo>>() {
            @Override
            public void onChanged(Resource<ResponseWrapperInfo> resource) {
                if (resource.status != Status.LOADING) {
                    handler.sendEmptyMessageDelayed(1092,5000);
                    if (resource != null && resource.data != null) {
                        post(getFunctionList(), mySelfInfo, friendList);
                        if(resource.data.getFriendslist()!= null && resource.data.getFriendslist().size() > 0) {
                            setFunRedDotShowStatus("1", true);
                        }else{
                            setFunRedDotShowStatus("1", false);
                        }
                    }else{
                        setFunRedDotShowStatus("1", false);
                    }
                }
            }
        });
    }

    private void loadFriendShip() {
        SLog.i(TAG, "loadFriendShip()");
//        LiveData<Resource<UserInfo>> userInfo = userTask.getUserInfo(IMManager.getInstance().getCurrentId());
//        conversationLiveData.addSource(userInfo, new Observer<Resource<UserInfo>>() {
//            @Override
//            public void onChanged(Resource<UserInfo> resource) {
//
//                if (resource.status != Status.LOADING) {
//                    if (resource != null && resource.data != null) {
//                        UserInfo data = resource.data;
//                        FriendShipInfo info = new FriendShipInfo();
//                        info.setDisplayName(data.getAlias());
//                        info.setDisPlayNameSpelling(data.getAliasSpelling());
//                        FriendDetailInfo friendDetailInfo = new FriendDetailInfo();
//                        friendDetailInfo.setNickname(data.getName());
//                        friendDetailInfo.setId(data.getId());
//                        friendDetailInfo.setPhone(data.getPhoneNumber());
//                        friendDetailInfo.setNameSpelling(data.getNameSpelling());
//                        friendDetailInfo.setOrderSpelling(data.getOrderSpelling());
//                        friendDetailInfo.setPortraitUri(data.getPortraitUri());
//                        friendDetailInfo.setRegion(data.getRegion());
//                        info.setUser(friendDetailInfo);
//                        mySelfInfo = info;
//                        post(getFunctionList(), mySelfInfo, friendList);
//                    }
//                }
//            }
//        });

        selectAddFriendsRequestlist();

        LiveData<Resource<ResponseWrapperInfo>> allFriends = friendTask.getHjhAllFriends(UserCache.getInstance().getCurrentUserId());
        allFriendInfo.setSource(allFriends);
        conversationLiveData.addSource(allFriends, new Observer<Resource<ResponseWrapperInfo>>() {
            @Override
            public void onChanged(Resource<ResponseWrapperInfo> listResource) {
                if (listResource.status != Status.LOADING) {
                    ResponseWrapperInfo wrapperInfo = listResource.data;
                    if (wrapperInfo != null) {
                        friendList.clear();
                        for(ResponseAddingFriendInfo data : wrapperInfo.getFriendslist()){
//                            if(data.getStatus() == FriendStatus.IS_FRIEND.getStatusCode()) {
                                friendList.add(data);
//                            }
                        }
                        post(getFunctionList(), mySelfInfo, friendList);
                    }
                }
            }
        });
    }


    private void post(List<ListItemModel> funs, FriendShipInfo myself, List<ResponseAddingFriendInfo> friendShipInfos) {
        ModelBuilder builder = new ModelBuilder();
        builder.addHjhFriendList(friendShipInfos);
        builder.buildFirstChar();
        if (myself != null) {
            builder.addFriend(0, myself);
        }
        builder.addModelList(0, funs);
        builder.post();
    }


    /**
     * 刷新某一项的监听
     *
     * @return
     */
    public LiveData<Integer> getRefreshItem() {
        return refreshItem;
    }

    /**
     * 刷新红点数量的监听
     *
     * @return
     */
    public LiveData<Integer> getRefreshDotNum() {
        return dotNumData;
    }

    /**
     * 设置功能项的红点状态
     *
     * @param id
     * @param isShow
     */
    public void setFunRedDotShowStatus(String id, boolean isShow) {
        final int i = setFunctionShowRedDot(id, isShow);
        refreshItem.postValue(i);
    }


    /**
     * 设置展示红点
     *
     * @param id
     */
    private int setFunctionShowRedDot(String id, boolean isShow) {
        if (conversationLiveData.getValue() == null || conversationLiveData.getValue() == null) {
            return 0;
        }
        for (int i = 0; i < conversationLiveData.getValue().size(); i++) {
            Object o = conversationLiveData.getValue().get(i).getData();
            if (o instanceof FunctionInfo) {
                FunctionInfo functionInfo = (FunctionInfo) o;
                if (functionInfo.getId().equals(id)) {
                    functionInfo.setShowDot(isShow);
                    return i;
                }
            }
        }
        return 0;
    }

    public int setFunctionShowRedDot(String id, int dotNum, boolean isShow) {
        if (conversationLiveData.getValue() == null || conversationLiveData.getValue() == null) {
            return 0;
        }
        for (int i = 0; i < conversationLiveData.getValue().size(); i++) {
            Object o = conversationLiveData.getValue().get(i).getData();
            if (o instanceof FunctionInfo) {
                FunctionInfo functionInfo = (FunctionInfo) o;
                if (functionInfo.getId().equals(id)) {
                    functionInfo.setShowDot(isShow);
                    functionInfo.setDotNumber(dotNum);
                    return i;
                }
            }
        }
        return 0;
    }

    public LiveData<List<ListItemModel>> getConversationLiveData(){
        return  conversationLiveData;
    }

}
