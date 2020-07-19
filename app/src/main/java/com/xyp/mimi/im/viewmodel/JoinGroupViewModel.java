package com.xyp.mimi.im.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.xyp.mimi.im.bean.ResponseWrapperGroupInfo;
import com.xyp.mimi.im.db.model.GroupEntity;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.task.GroupTask;
import com.xyp.mimi.im.utils.SingleSourceLiveData;

/**
 * 加入群组视图模型
 */
public class JoinGroupViewModel extends AndroidViewModel {
    private String groupId;
    private GroupTask groupTask;

    private SingleSourceLiveData<Resource<ResponseWrapperGroupInfo>> groupInfo = new SingleSourceLiveData<>();
    private SingleSourceLiveData<Resource<Void>> joinGroupResult = new SingleSourceLiveData<>();

    public JoinGroupViewModel(@NonNull Application application) {
        super(application);

        groupTask = new GroupTask(application);
    }

    public JoinGroupViewModel(@NonNull Application application, String groupId) {
        super(application);

        this.groupId = groupId;
        groupTask = new GroupTask(application);
        groupInfo.setSource(groupTask.getGroupInfo(groupId));
    }

    /**
     * 获取群组信息
     *
     * @return
     */
    public LiveData<Resource<ResponseWrapperGroupInfo>> getGroupInfo() {
        return groupInfo;
    }

    /**
     * 获取加入群组结果
     *
     * @return
     */
    public LiveData<Resource<Void>> getJoinGroupInfo() {
        return joinGroupResult;
    }

    /**
     * 请求加入群组
     */
    public void joinToGroup(){
        joinGroupResult.setSource(groupTask.joinGroup(groupId));
    }


    public static class Factory implements ViewModelProvider.Factory {
        private Application application;
        private String groupId;


        public Factory(Application application, String groupId) {
            this.application = application;
            this.groupId = groupId;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            try {
                return modelClass.getConstructor(Application.class, String.class).newInstance(application, groupId);
            } catch (Exception e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            }
        }
    }
}
