package com.xyp.mimi.im.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import com.xyp.mimi.im.db.model.FriendShipInfo;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.model.Status;
import com.xyp.mimi.im.model.UserSimpleInfo;
import com.xyp.mimi.im.task.FriendTask;
import com.xyp.mimi.im.task.UserTask;
import com.xyp.mimi.im.utils.SingleSourceLiveData;
import com.xyp.mimi.im.utils.SingleSourceMapLiveData;

public class BlackListViewModel extends AndroidViewModel {
    private UserTask userTask;
    private FriendTask friendTask;

    private SingleSourceLiveData<Resource<List<UserSimpleInfo>>> blacklistResult = new SingleSourceLiveData<>();
    private SingleSourceMapLiveData<Resource<Void>, Resource<Void>> removeBlackListResult;

    public BlackListViewModel(@NonNull Application application) {
        super(application);
        userTask = new UserTask(application);
        friendTask = new FriendTask(application);
        getBlackList();


        removeBlackListResult = new SingleSourceMapLiveData<>(new Function<Resource<Void>, Resource<Void>>() {
            @Override
            public Resource<Void> apply(Resource<Void> input) {
                if(input.status == Status.SUCCESS) {
                    // 在移除黑名单成功后刷新好友列表
                    LiveData<Resource<List<FriendShipInfo>>> allFriends = friendTask.getAllFriends();
                    allFriends.observeForever(new Observer<Resource<List<FriendShipInfo>>>() {
                        @Override
                        public void onChanged(Resource<List<FriendShipInfo>> listResource) {
                            if(listResource.status != Status.LOADING) {
                                allFriends.removeObserver(this);
                            }
                        }
                    });
                }
                return input;
            }
        });
    }

    /**
     * 黑名单
     *
     * @return
     */
    public LiveData<Resource<List<UserSimpleInfo>>> getBlackListResult() {
        return blacklistResult;
    }

    /**
     * 获取黑名单
     */
    private void getBlackList() {
        blacklistResult.setSource(userTask.getBlackList());
    }

    /**
     * 移除黑名单
     */
    public void removeFromBlackList(String userId) {
        removeBlackListResult.setSource(userTask.removeFromBlackList(userId));
    }

    /**
     * 获取移除黑名单结果
     *
     * @return
     */
    public LiveData<Resource<Void>> getRemoveBlackListResult() {
        return removeBlackListResult;
    }


}
