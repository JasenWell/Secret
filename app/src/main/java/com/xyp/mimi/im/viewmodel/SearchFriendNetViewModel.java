package com.xyp.mimi.im.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import com.xyp.mimi.im.bean.ResponseSearchFriendInfo;
import com.xyp.mimi.im.bean.ResponseUserInfo;
import com.xyp.mimi.im.db.model.FriendShipInfo;
import com.xyp.mimi.im.db.model.FriendStatus;
import com.xyp.mimi.im.model.AddFriendResult;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.model.SearchFriendInfo;
import com.xyp.mimi.im.model.Status;
import com.xyp.mimi.im.task.FriendTask;
import com.xyp.mimi.im.utils.SingleSourceLiveData;
import com.xyp.mimi.im.utils.SingleSourceMapLiveData;
import com.xyp.mimi.im.utils.log.SLog;
import com.xyp.mimi.mvp.http.entity.login.LoginUserResult;


public class SearchFriendNetViewModel extends AndroidViewModel {
    private static final String TAG = "SearchFriendNetViewModel";
    private FriendTask friendTask;
    private SingleSourceLiveData<Resource<LoginUserResult>> searchFriend;
    private SingleSourceLiveData<Resource<ResponseUserInfo>> searchFriendByphone;
    private SingleSourceMapLiveData<ResponseUserInfo, Boolean> isFriend;
    private SingleSourceMapLiveData<Resource<ResponseSearchFriendInfo>, Resource<ResponseSearchFriendInfo>> addFriend;

    public SearchFriendNetViewModel(@NonNull Application application) {
        super(application);
        friendTask = new FriendTask(application);
        searchFriend = new SingleSourceLiveData<>();
        searchFriendByphone = new SingleSourceLiveData<>();
        addFriend = new SingleSourceMapLiveData<>(new Function<Resource<ResponseSearchFriendInfo>, Resource<ResponseSearchFriendInfo>>() {
            @Override
            public Resource<ResponseSearchFriendInfo> apply(Resource<ResponseSearchFriendInfo> input) {
                if(input.status == Status.SUCCESS){
                    // 邀请后刷新好友列表
                    updateFriendList();
                }

                return input;
            }
        });
        isFriend = new SingleSourceMapLiveData<ResponseUserInfo, Boolean>(new Function<ResponseUserInfo, Boolean>() {
            @Override
            public Boolean apply(ResponseUserInfo input) {
                if(input != null){
                    if(input.getStatus() == null){
                        return false;
                    }else if(input.getStatus().equals("1")){
                        return true;
                    }else{
                        return false;
                    }
                } else {
                    return false;
                }
            }
        });
    }

    public void searchFriendFromServer(String userId) {
        SLog.i(TAG, "searchFriendFromServer userid " + userId);
        searchFriend.setSource(friendTask.searchFriendFromServer(userId));
    }

    public void searchFriendByphone(String phone) {
        SLog.i(TAG, "searchFriendFromServer phone " + phone);
        searchFriendByphone.setSource(friendTask.searchFriendByphone(phone));
    }

    public void addFriendRequest(String userId,String phone) {
        SLog.i(TAG, "searchFriendFromServer userid " + userId);
        addFriend.setSource(friendTask.addFriendRequest(userId,phone));
    }

    public LiveData<Resource<LoginUserResult>> getSearchFriend() {
        return searchFriend;
    }

    public SingleSourceLiveData<Resource<ResponseUserInfo>> getSearchFriendByphone() {
        return searchFriendByphone;
    }

    public LiveData<Boolean> getIsFriend() {
        return isFriend;
    }

    public void isFriend(ResponseUserInfo userInfo) {
        SingleSourceLiveData<ResponseUserInfo> source = new SingleSourceLiveData<ResponseUserInfo>(){

        } ;
        source.setValue(userInfo);
        isFriend.setSource(source);
    }

    public LiveData<Resource<ResponseSearchFriendInfo>> getAddFriend() {
        return addFriend;
    }

    /**
     * 刷新好友列表
     */
    private void updateFriendList() {
        LiveData<Resource<List<FriendShipInfo>>> allFriends = friendTask.getAllFriends();
        allFriends.observeForever(new Observer<Resource<List<FriendShipInfo>>>() {
            @Override
            public void onChanged(Resource<List<FriendShipInfo>> listResource) {
                if (listResource.status != Status.LOADING) {
                    allFriends.removeObserver(this);
                }
            }
        });
    }
}
