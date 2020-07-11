package com.xyp.mimi.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xyp.mimi.mvp.http.entity.TaskData;
import com.xyp.mimi.mvp.http.entity.TaskList;
import com.xyp.mimi.mvp.http.entity.BaseResponse;

import java.util.List;

import io.reactivex.Observable;

//任务
public interface TaskContract {


    //刷新banner还有list
    interface View extends IView {
        void searchTaskSuccess(List<TaskList.ConBean> s);

        void startTaskSuccess();

        void getTaskSuccess(TaskData taskData);
    }

    //获取数据
    interface Model extends IModel {
        Observable<TaskList> searchTask();
        Observable<BaseResponse<Object>> startTask(String useid, String cname);
        Observable<TaskData> getTask(String useid);
    }
}
