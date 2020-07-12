package com.xyp.mimi.im.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import com.xyp.mimi.im.db.model.FriendDescription;
import com.xyp.mimi.im.db.model.FriendShipInfo;
import com.xyp.mimi.im.db.model.UserInfo;
import com.xyp.mimi.im.model.AddFriendResult;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.model.Status;
import com.xyp.mimi.im.model.UserSimpleInfo;
import com.xyp.mimi.im.task.FriendTask;
import com.xyp.mimi.im.task.UserTask;
import com.xyp.mimi.im.utils.SingleSourceLiveData;
import com.xyp.mimi.im.utils.SingleSourceMapLiveData;

/**
 * 用户详细视图模型
 */
public class EditUserDescribeViewModel extends AndroidViewModel {

    private SingleSourceLiveData<Resource<FriendDescription>> friendDescription = new SingleSourceLiveData<>();
    private SingleSourceLiveData<Resource<Void>> setFriendDescriptionResult = new SingleSourceLiveData<>();
    private FriendTask friendTask;
    private String userId;

    public EditUserDescribeViewModel(@NonNull Application application) {
        super(application);
    }

    public EditUserDescribeViewModel(@NonNull Application application, String userId) {
        super(application);
        this.userId = userId;
        this.friendTask = new FriendTask(application);
        requestFriendDescription();
    }

    /**
     * 获取朋友描述
     */
    public void requestFriendDescription() {
        friendDescription.setSource(friendTask.getFriendDescription(userId));
    }

    public LiveData<Resource<FriendDescription>> getFriendDescription() {
        return friendDescription;
    }

    /**
     * 设置朋友描述
     *
     * @param friendId
     * @param displayName
     * @param region
     * @param phone
     * @param description
     * @param imageUri
     */
    public void setFriendDescription(String friendId, String displayName, String region
            , String phone, String description, String imageUri) {
        setFriendDescriptionResult.setSource(friendTask.setFriendDescription(friendId, displayName
                , region, phone, description, imageUri));
    }

    public LiveData<Resource<Void>> setFriendDescriptionResult() {
        return setFriendDescriptionResult;
    }

    public static class Factory implements ViewModelProvider.Factory {
        private Application application;
        private String userId;

        public Factory(Application application, String userId) {
            this.application = application;
            this.userId = userId;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            try {
                return modelClass.getConstructor(Application.class, String.class).newInstance(application, userId);
            } catch (Exception e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            }
        }
    }
}
