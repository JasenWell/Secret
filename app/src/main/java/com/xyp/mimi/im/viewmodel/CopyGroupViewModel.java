package com.xyp.mimi.im.viewmodel;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.xyp.mimi.im.db.model.GroupEntity;
import com.xyp.mimi.im.db.model.UserInfo;
import com.xyp.mimi.im.file.FileManager;
import com.xyp.mimi.im.model.CopyGroupResult;
import com.xyp.mimi.im.model.GroupMember;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.qrcode.QRCodeManager;
import com.xyp.mimi.im.task.GroupTask;
import com.xyp.mimi.im.task.UserTask;
import com.xyp.mimi.im.utils.SingleSourceLiveData;

public class CopyGroupViewModel extends AndroidViewModel {
    private SingleSourceLiveData<Resource<GroupEntity>> groupInfo = new SingleSourceLiveData<>();
    private SingleSourceLiveData<Resource<CopyGroupResult>> copyGroupResult = new SingleSourceLiveData<>();
    private GroupTask groupTask;

    public CopyGroupViewModel(@NonNull Application application) {
        super(application);

        groupTask = new GroupTask(application);
    }

    public void requestGroupInfo(String groupId) {
        groupInfo.setSource(groupTask.getGroupInfo(groupId));
    }

    public LiveData<Resource<GroupEntity>> getGroupInfo() {
        return groupInfo;
    }

    /**
     * 复制群组
     *
     * @param groupId
     * @param name
     * @param portraitUri
     */
    public void copyGroup(String groupId, String name, String portraitUri) {
        copyGroupResult.setSource(groupTask.copyGroup(groupId, name, portraitUri));
    }

    public LiveData<Resource<CopyGroupResult>> getCopyGroupResult() {
        return copyGroupResult;
    }

    public LiveData<List<GroupMember>> getGroupMemberInfoList(String groupId) {
        return groupTask.getGroupMemberInfoListInDB(groupId);
    }
}

