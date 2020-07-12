package com.xyp.mimi.im.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import com.xyp.mimi.im.common.ThreadManager;
import com.xyp.mimi.im.db.model.GroupEntity;
import com.xyp.mimi.im.db.model.GroupExitedMemberInfo;
import com.xyp.mimi.im.db.model.GroupNoticeInfo;
import com.xyp.mimi.im.db.model.UserInfo;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.task.GroupTask;
import com.xyp.mimi.im.task.UserTask;
import com.xyp.mimi.im.utils.SingleSourceLiveData;

public class GroupExitedInfoViewModel extends AndroidViewModel {

    private GroupTask groupTask;
    private SingleSourceLiveData<Resource<List<GroupExitedMemberInfo>>> groupExitedInfo = new SingleSourceLiveData<>();

    public GroupExitedInfoViewModel(@NonNull Application application) {
        super(application);
        groupTask = new GroupTask(application);
    }

    /**
     * 请求群通知全部信息
     */
    public void requestExitedInfo(String groupId) {
        groupExitedInfo.setSource(groupTask.getGroupExitedMemberInfo(groupId));
    }

    public LiveData<Resource<List<GroupExitedMemberInfo>>> getExitedInfo() {
        return groupExitedInfo;
    }

}
