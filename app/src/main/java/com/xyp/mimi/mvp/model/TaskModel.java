package com.xyp.mimi.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.TaskContract;
import com.xyp.mimi.mvp.http.api.service.TaskService;
import com.xyp.mimi.mvp.http.entity.TaskData;
import com.xyp.mimi.mvp.http.entity.TaskList;
import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;


public class TaskModel extends BaseModel implements TaskContract.Model {
    public TaskModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<TaskList> searchTask() {
        return mRepositoryManager.obtainRetrofitService(TaskService.class)
                .searchTask();
    }

    @Override
    public Observable<BaseResponse<Object>> startTask(String useid, String cname) {
        return  mRepositoryManager.obtainRetrofitService(TaskService.class)
                .startTask(useid,cname);
    }

    @Override
    public Observable<TaskData> getTask(String useid) {
        return  mRepositoryManager.obtainRetrofitService(TaskService.class)
                .getTask(useid);
    }
}
